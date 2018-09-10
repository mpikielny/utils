/**
 *
 */
package com.slimgears.apt.util;

import com.google.common.base.Preconditions;
import com.slimgears.apt.data.Environment;
import com.slimgears.apt.data.TypeInfo;

import javax.lang.model.element.*;
import javax.lang.model.type.*;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.slimgears.util.stream.Streams.ofType;
import static com.slimgears.util.stream.Streams.self;

public class ElementUtils {
    public static boolean isKnownType(TypeElement typeElement) {
        return Environment.instance().isIgnoredType(TypeInfo.of(typeElement.getQualifiedName().toString()));
    }

    public static boolean isUnknownType(TypeElement typeElement) {
        return !isKnownType(typeElement);
    }

    public static boolean isPublic(Element element) {
        return modifiersContainAll(element, Modifier.PUBLIC);
    }

    public static boolean isNotStatic(Element element) {
        return modifiersContainNone(element, Modifier.STATIC);
    }

    public static boolean isAbstract(Element element) {
        return modifiersContainNone(element, Modifier.DEFAULT) && modifiersContainAll(element, Modifier.ABSTRACT);
    }

    public static boolean isInterface(Element element) {
        return isOfKind(element, ElementKind.INTERFACE);
    }

    public static boolean isOfKind(Element element, ElementKind kind) {
        return element.getKind() == kind;
    }

    public static boolean modifiersContainAll(Element element, Modifier... modifiers) {
        Set<Modifier> elementModifiers = element.getModifiers();
        return elementModifiers.containsAll(Arrays.asList(modifiers));
    }

    public static boolean modifiersContainNone(Element element, Modifier... modifiers) {
        Set<Modifier> elementModifiers = element.getModifiers();
        return Stream.of(modifiers).noneMatch(elementModifiers::contains);
    }

    public static Predicate<? super Element> ofKind(ElementKind kind) {
        return el -> el.getKind() == kind;
    }

    public static boolean isEnum(TypeElement typeElement) {
        return ofKind(ElementKind.ENUM).test(typeElement);
    }

    public static boolean isEnumConstant(Element element) {
        return ofKind(ElementKind.ENUM_CONSTANT).test(element);
    }

    public static boolean hasAnnotation(Element elemenet, Class<? extends Annotation> annotationCls) {
        return elemenet.getAnnotation(annotationCls) != null;
    }

    public static Stream<TypeElement> getReferencedTypes(TypeElement typeElement) {
        Stream<TypeElement> enclosedElements = typeElement
                .getEnclosedElements()
                .stream()
                .filter(ElementUtils::isPublic)
                .filter(ElementUtils::isNotStatic)
                .flatMap(element -> Stream.concat(
                        Stream.of(element)
                                .flatMap(ofType(ExecutableElement.class))
                                .flatMap(ElementUtils::getReferencedTypes),
                        Stream.of(element)
                                .flatMap(ofType(VariableElement.class))
                                .flatMap(v -> getReferencedTypeParams(v.asType()))
                                .flatMap(ElementUtils::toTypeElement)))
                .filter(ElementUtils::isUnknownType)
                .distinct();
        return Stream.concat(getHierarchy(typeElement), enclosedElements);
    }

    public static Stream<TypeElement> getReferencedTypes(ExecutableElement executableElement) {
        return Stream.concat(
                Stream.of(executableElement.getReturnType()),
                executableElement.getParameters().stream()
                        .map(VariableElement::asType))
                        .flatMap(ElementUtils::getReferencedTypeParams)
                .flatMap(ElementUtils::toTypeElement)
                .distinct();
    }

    public static Stream<TypeMirror> getReferencedTypeParams(TypeMirror type) {
        return Stream.of(
                Stream.of(type)
                        .flatMap(ofType(DeclaredType.class)),
                Stream.of(type)
                        .flatMap(ofType(WildcardType.class))
                        .flatMap(wt -> Stream.of(wt.getExtendsBound(), wt.getSuperBound()))
                        .flatMap(ofType(DeclaredType.class))
                        .flatMap(t -> t.getTypeArguments().stream())
                        .flatMap(ElementUtils::getReferencedTypeParams),
                Stream.of(type)
                        .flatMap(ofType(DeclaredType.class))
                        .flatMap(t -> t.getTypeArguments().stream())
                        .flatMap(ElementUtils::getReferencedTypeParams),
                Stream.of(type)
                        .flatMap(ofType(ArrayType.class))
                        .map(ArrayType::getComponentType))
                .flatMap(self())
                .filter(DeclaredType.class::isInstance)
                .distinct();
    }

    public static Stream<DeclaredType> getHierarchy(DeclaredType declaredType) {
        return Stream.of(
                Stream.of(declaredType),
                getSuperClass(declaredType),
                getInterfaces(declaredType))
                .flatMap(self())
                .distinct();
    }

    public static Stream<ExecutableElement> getMethods(DeclaredType declaredType) {
        return toTypeElement(declaredType)
                .map(TypeElement::getEnclosedElements)
                .flatMap(Collection::stream)
                .flatMap(ofType(ExecutableElement.class))
                .filter(ElementUtils::isPublic)
                .filter(ElementUtils::isNotStatic);
    }

    public static Stream<TypeElement> getHierarchy(TypeElement typeElement) {
        return Stream.of(
                Stream.of(typeElement),
                Stream.of(typeElement)
                        .map(TypeElement::getSuperclass)
                        .flatMap(ElementUtils::toTypeElement)
                        .flatMap(ElementUtils::getHierarchy),
                Stream.of(typeElement)
                        .flatMap(t -> t.getInterfaces().stream())
                        .flatMap(ElementUtils::toTypeElement)
                        .flatMap(ElementUtils::getHierarchy))
                .flatMap(self())
                .distinct();
    }

    public static Stream<DeclaredType> toDeclaredTypeStream(TypeElement typeElement) {
        return Stream.of(typeElement)
                .map(TypeElement::asType)
                .flatMap(ofType(DeclaredType.class));
    }

    public static DeclaredType toDeclaredType(TypeElement typeElement) {
        return toDeclaredTypeStream(typeElement)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot convert " + typeElement.getQualifiedName() + " to DeclaredType"));
    }

    public static Stream<TypeElement> toTypeElement(TypeMirror type) {
        return Stream.of(type)
                .flatMap(ofType(DeclaredType.class))
                .map(DeclaredType::asElement)
                .flatMap(ofType(TypeElement.class));
    }

    public static <A extends Annotation> TypeInfo[] typesFromAnnotation(A annotation, Function<A, Class[]> classRetriever) {
        return Stream
                .of(typeMirrorsFromAnnotation(annotation, classRetriever))
                .map(TypeInfo::of)
                .toArray(TypeInfo[]::new);
    }

    public static <A extends Annotation> TypeMirror[] typeMirrorsFromAnnotation(A annotation, Function<A, Class[]> classRetriever) {
        try {
            return Stream.of(classRetriever.apply(annotation)).toArray(TypeMirror[]::new);
        } catch (MirroredTypesException e) {
            return e.getTypeMirrors().toArray(new TypeMirror[0]);
        }
    }

    public static <A extends Annotation> TypeInfo typeFromAnnotation(A annotation, Function<A, Class> classRetriever) {
        return TypeInfo.of(Preconditions.checkNotNull(typeMirrorFromAnnotation(annotation, classRetriever)));
    }

    public static <A extends Annotation> TypeMirror typeMirrorFromAnnotation(A annotation, Function<A, Class> classRetriever) {
        try {
            classRetriever.apply(annotation);
            return null;
        } catch (MirroredTypeException e) {
            return e.getTypeMirror();
        }
    }

    private static Stream<DeclaredType> getSuperClass(DeclaredType type) {
        return toTypeElement(type)
                .map(TypeElement::getSuperclass)
                .flatMap(ofType(DeclaredType.class));
    }

    private static Stream<DeclaredType> getInterfaces(DeclaredType type) {
        return toTypeElement(type)
                .flatMap(e -> e.getInterfaces().stream())
                .flatMap(ofType(DeclaredType.class));
    }
}