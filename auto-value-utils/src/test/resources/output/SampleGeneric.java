package com.slimgears.sample;

import com.google.auto.value.AutoValue;
import com.google.common.reflect.TypeToken;
import com.slimgears.util.autovalue.annotations.BuilderPrototype;
import com.slimgears.util.autovalue.annotations.HasMetaClass;
import com.slimgears.util.autovalue.annotations.MetaBuilder;
import com.slimgears.util.autovalue.annotations.MetaClass;
import com.slimgears.util.autovalue.annotations.PropertyMeta;
import com.slimgears.util.autovalue.annotations.PropertyType;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Generated;

@Generated("com.slimgears.util.autovalue.apt.AutoValuePrototypeAnnotationProcessor")
@AutoValue
public abstract class SampleGeneric<T> implements SampleGenericPrototype<T>, HasMetaClass<SampleGeneric<T>> {

    @Override
    
    public MetaClass<SampleGeneric<T>> metaClass() {
        return (MetaClass<SampleGeneric<T>>)metaClass;
    }

    public static final Meta metaClass = new Meta();
    public static class Meta<T> implements MetaClass<SampleGeneric<T>> {
        private final TypeToken<SampleGeneric<T>> objectType = new TypeToken<SampleGeneric<T>>(){};
        private final TypeToken<Builder<T>> builderClass = new TypeToken<Builder<T>>(){};
        private final Map<String, PropertyMeta<SampleGeneric<T>, ?>> propertyMap = new LinkedHashMap<>();

        public final PropertyMeta<SampleGeneric<T>, String> value = PropertyMeta.<SampleGeneric<T>, String, Builder<T>>create(this, "value", new PropertyType<String>(){}, obj -> obj.value(), Builder::value);
        public final PropertyMeta<SampleGeneric<T>, T> tValue = PropertyMeta.<SampleGeneric<T>, T, Builder<T>>create(this, "tValue", new PropertyType<T>(){}, obj -> obj.tValue(), Builder::tValue);

        Meta() {
            propertyMap.put("value", value);
            propertyMap.put("tValue", tValue);
        }

        @Override
        public TypeToken<Builder<T>> builderClass() {
            return this.builderClass;
        }

        @Override
        public TypeToken<SampleGeneric<T>> asType() {
            return this.objectType;
        }

        @Override
        public Iterable<PropertyMeta<SampleGeneric<T>, ?>> properties() {
            return propertyMap.values();
        }

        @Override
        @SuppressWarnings("unchecked")
        public <__V> PropertyMeta<SampleGeneric<T>, __V> getProperty(String name) {
            return (PropertyMeta<SampleGeneric<T>, __V>)propertyMap.get(name);
        }

        @Override
        public <B extends MetaBuilder<SampleGeneric<T>>> B createBuilder() {
            return (B)(BuilderPrototype)SampleGeneric.builder();
        }

        @Override
        public int hashCode() {
            return Objects.hash(objectType, builderClass);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Meta
            && Objects.equals(((Meta)obj).asType(), asType())
            && Objects.equals(((Meta)obj).builderClass(), builderClass());
        }
    }

    public static <T> SampleGeneric<T> create(
         String value,
         T tValue) {
        return SampleGeneric.<T>builder()
            .value(value)
            .tValue(tValue)
            .build();
    }

    public abstract Builder<T> toBuilder();

    public static <T> Builder<T> builder() {
        return Builder.create();
    }

    @AutoValue.Builder
    public interface Builder<T> extends BuilderPrototype<SampleGeneric<T>, Builder<T>>, SampleGenericPrototypeBuilder<T, Builder<T>>{
        public static <T> Builder<T> create() {
            return new AutoValue_SampleGeneric.Builder<>();
        }

        @Override
        Builder<T> value(String value);

        @Override
        Builder<T> tValue(T tValue);
    }

    @Override public abstract String value();
    @Override public abstract T tValue();

    public static <T> SampleGeneric<T> createDefault(
        T value) {
        return (SampleGeneric<T>) SampleGenericPrototype.createDefault(
            value);
    }
}
