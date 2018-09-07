package com.slimgears.util.generic;

import com.slimgears.util.reflect.TypeToken;

public interface ServiceResolver extends AutoCloseable {
    <T> T resolve(TypeToken<T> token);
    boolean canResolve(TypeToken<?> token);

    default <T> T resolve(Class<T> cls) {
        return resolve(TypeToken.of(cls));
    }

    default boolean canResolve(Class<?> cls) {
        return canResolve(TypeToken.of(cls));
    }

    default void close() {}
}
