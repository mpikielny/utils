package com.slimgears.sample;

import com.slimgears.util.autovalue.annotations.PropertyMeta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.slimgears.util.autovalue.annotations.BuilderPrototype;
import com.slimgears.util.autovalue.annotations.HasMetaClass;
import com.slimgears.util.autovalue.annotations.MetaClass;
import com.slimgears.util.reflect.TypeToken;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Generated;
import javax.annotation.Nullable;

@Generated("com.slimgears.util.autovalue.apt.AutoValuePrototypeAnnotationProcessor")
@AutoValue
public abstract class SampleGuavaValue implements SampleGuavaValuePrototype, HasMetaClass<SampleGuavaValue, SampleGuavaValue.Builder> {
    public static final Meta metaClass = new Meta();

    public static class Meta implements MetaClass<SampleGuavaValue, SampleGuavaValue.Builder> {

        private final TypeToken<SampleGuavaValue> objectClass = new TypeToken<SampleGuavaValue>(){};
        private final TypeToken<Builder> builderClass = new TypeToken<Builder>(){};
        private final Map<String, PropertyMeta<SampleGuavaValue, Builder, ?>> propertyMap = new LinkedHashMap<>();
        public final PropertyMeta<SampleGuavaValue, Builder, ImmutableList<Integer>> intList = PropertyMeta.create(objectClass, "intList", new TypeToken<ImmutableList<Integer>>(){}, SampleGuavaValue::intList, Builder::intList);
        public final PropertyMeta<SampleGuavaValue, Builder, ImmutableSet<String>> stringSet = PropertyMeta.create(objectClass, "stringSet", new TypeToken<ImmutableSet<String>>(){}, SampleGuavaValue::stringSet, Builder::stringSet);
        public final PropertyMeta<SampleGuavaValue, Builder, ImmutableMap<Integer, String>> intToStringMap = PropertyMeta.create(objectClass, "intToStringMap", new TypeToken<ImmutableMap<Integer, String>>(){}, SampleGuavaValue::intToStringMap, Builder::intToStringMap);
        public final PropertyMeta<SampleGuavaValue, Builder, ImmutableBiMap<Integer, String>> intToStringBiMap = PropertyMeta.create(objectClass, "intToStringBiMap", new TypeToken<ImmutableBiMap<Integer, String>>(){}, SampleGuavaValue::intToStringBiMap, Builder::intToStringBiMap);
        public final PropertyMeta<SampleGuavaValue, Builder, ImmutableList<String>> optionalList = PropertyMeta.create(objectClass, "optionalList", new TypeToken<ImmutableList<String>>(){}, SampleGuavaValue::optionalList, Builder::optionalList);

        Meta() {
            propertyMap.put("intList", intList);
            propertyMap.put("stringSet", stringSet);
            propertyMap.put("intToStringMap", intToStringMap);
            propertyMap.put("intToStringBiMap", intToStringBiMap);
            propertyMap.put("optionalList", optionalList);
        }

        @Override
        public TypeToken<Builder> builderClass() {
            return this.builderClass;
        }

        @Override
        public TypeToken<SampleGuavaValue> objectClass() {
            return this.objectClass;
        }

        @Override
        public Iterable<PropertyMeta<SampleGuavaValue, Builder, ?>> properties() {
            return propertyMap.values();
        }

        @Override
        public <__V> PropertyMeta<SampleGuavaValue, Builder, __V> getProperty(String name) {
            //noinspection unchecked
            return (PropertyMeta<SampleGuavaValue, Builder, __V>)propertyMap.get(name);
        }

        @Override
        public Builder createBuilder() {
            return SampleGuavaValue.builder();
        }

        @Override
        public int hashCode() {
            return Objects.hash(objectClass, builderClass);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Meta
                    && Objects.equals(((Meta)obj).objectClass(), objectClass())
                    && Objects.equals(((Meta)obj).builderClass(), builderClass());
        }
    }

    @JsonIgnore
    public abstract Builder toBuilder();

    @JsonIgnore
    @Override
    public MetaClass<SampleGuavaValue, SampleGuavaValue.Builder> metaClass() {
        return metaClass;
    }

    public static Builder builder() {
        return Builder.create();
    }

    @JsonCreator
    public static SampleGuavaValue create(
            @JsonProperty("intList") ImmutableList<Integer> intList,
            @JsonProperty("stringSet") ImmutableSet<String> stringSet,
            @JsonProperty("intToStringMap") ImmutableMap<Integer, String> intToStringMap,
            @JsonProperty("intToStringBiMap") ImmutableBiMap<Integer, String> intToStringBiMap,
            @JsonProperty("optionalList") ImmutableList<String> optionalList) {
        return SampleGuavaValue.builder()
                .intList(intList)
                .stringSet(stringSet)
                .intToStringMap(intToStringMap)
                .intToStringBiMap(intToStringBiMap)
                .optionalList(optionalList)
                .build();
    }

    @Override
    public abstract ImmutableList<Integer> intList();

    @Override
    public abstract ImmutableSet<String> stringSet();

    @Override
    public abstract ImmutableMap<Integer, String> intToStringMap();

    @Override
    public abstract ImmutableBiMap<Integer, String> intToStringBiMap();

    @Override
    @Nullable
    public abstract ImmutableList<String> optionalList();

    @AutoValue.Builder
    public interface Builder extends BuilderPrototype<SampleGuavaValue, Builder>, SampleGuavaValuePrototypeBuilder<Builder> {
        public static Builder create() {
            return new AutoValue_SampleGuavaValue.Builder();
        }

        @Override
        Builder intList(ImmutableList<Integer> intList);
        ImmutableList.Builder<Integer> intListBuilder();

        @Override
        Builder stringSet(ImmutableSet<String> stringSet);
        ImmutableSet.Builder<String> stringSetBuilder();

        @Override
        Builder intToStringMap(ImmutableMap<Integer, String> intToStringMap);
        ImmutableMap.Builder<Integer, String> intToStringMapBuilder();

        @Override
        Builder intToStringBiMap(ImmutableBiMap<Integer, String> intToStringBiMap);
        ImmutableBiMap.Builder<Integer, String> intToStringBiMapBuilder();

        @Override
        Builder optionalList(ImmutableList<String> optionalList);
    }
}
