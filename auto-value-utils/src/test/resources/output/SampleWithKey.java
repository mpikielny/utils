package com.slimgears.sample;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.slimgears.util.autovalue.annotations.BuilderPrototype;
import com.slimgears.util.autovalue.annotations.HasMetaClassWithKey;
import com.slimgears.util.autovalue.annotations.Key;
import com.slimgears.util.autovalue.annotations.MetaClassWithKey;
import com.slimgears.util.autovalue.annotations.PropertyMeta;
import com.slimgears.util.reflect.TypeToken;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Generated;

@Generated("com.slimgears.util.autovalue.apt.AutoValuePrototypeAnnotationProcessor")
@AutoValue
public abstract class SampleWithKey implements SampleWithKeyPrototype, HasMetaClassWithKey<String, com.slimgears.sample.SampleWithKey, SampleWithKey.Builder> {

    public MetaClassWithKey<String, SampleWithKey, SampleWithKey.Builder> metaClass() {
        return metaClass;
    }

    public static final Meta metaClass = new Meta();
    public static class Meta implements MetaClassWithKey<String, SampleWithKey, SampleWithKey.Builder> {
        private final TypeToken<SampleWithKey> objectClass = new TypeToken<SampleWithKey>(){};
        private final TypeToken<Builder> builderClass = new TypeToken<Builder>(){};
        private final Map<String, PropertyMeta<SampleWithKey, Builder, ?>> propertyMap = new LinkedHashMap<>();

        public final PropertyMeta<SampleWithKey, Builder, String> id = PropertyMeta.create(this, "id", new TypeToken<String>(){}, SampleWithKey::id, Builder::id);
        public final PropertyMeta<SampleWithKey, Builder, Integer> number = PropertyMeta.create(this, "number", new TypeToken<Integer>(){}, SampleWithKey::number, Builder::number);
        public final PropertyMeta<SampleWithKey, Builder, String> text = PropertyMeta.create(this, "text", new TypeToken<String>(){}, SampleWithKey::text, Builder::text);

        @Override
        public PropertyMeta<SampleWithKey, Builder, String> keyProperty() {
            return id;
        }

        Meta() {
            propertyMap.put("id", id);
            propertyMap.put("number", number);
            propertyMap.put("text", text);
        }

        @Override
        public TypeToken<Builder> builderClass() {
            return this.builderClass;
        }

        @Override
        public TypeToken<SampleWithKey> objectClass() {
            return this.objectClass;
        }

        @Override
        public Iterable<PropertyMeta<SampleWithKey, Builder, ?>> properties() {
            return propertyMap.values();
        }

        @Override
        public <__V> PropertyMeta<SampleWithKey, Builder, __V> getProperty(String name) {
            //noinspection unchecked
            return (PropertyMeta<SampleWithKey, Builder, __V>)propertyMap.get(name);
        }

        @Override
        public Builder createBuilder() {
            return SampleWithKey.builder();
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

    public static Builder builder() {
        return Builder.create();
    }

    @JsonCreator
    public static SampleWithKey create(
            @JsonProperty("id") String id,
            @JsonProperty("number") int number,
            @JsonProperty("text") String text) {
        return SampleWithKey.builder()
                .id(id)
                .number(number)
                .text(text)
                .build();
    }

    @Override
    @Key
    public abstract String id();

    @Override
    public abstract int number();

    @Override
    public abstract String text();

    @AutoValue.Builder
    public interface Builder extends BuilderPrototype<SampleWithKey, Builder>, SampleWithKeyPrototypeBuilder<Builder> {
        public static Builder create() {
            return new AutoValue_SampleWithKey.Builder();
        }

        @Override
        @Key
        Builder id(String id);

        @Override
        Builder number(int number);

        @Override
        Builder text(String text);
    }
}
