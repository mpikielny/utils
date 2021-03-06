package com.slimgears.util.generic;

import java.util.concurrent.Callable;

public class ScopedInstance<T> {
    private final ThreadLocal<T> instance = new ThreadLocal<>();

    public interface Closeable extends AutoCloseable {
        void close();

        default Closeable merge(Closeable another) {
            return () -> { this.close(); another.close(); };
        }
    }

    public static <T> ScopedInstance<T> create() {
        return new ScopedInstance<>();
    }

    public static <T> ScopedInstance<T> create(T instance) {
        return new ScopedInstance<>(instance);
    }

    private ScopedInstance(T instance) {
        this.instance.set(instance);
    }

    private ScopedInstance() {
    }

    public T current() {
        return instance.get();
    }

    public Closeable scope(T instance) {
        T previous = this.instance.get();
        this.instance.set(instance);
        return () -> this.instance.set(previous);
    }

    public <R> R withScope(T instance, Callable<? extends R> callable) {
        try (Closeable scope = scope(instance)) {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void withScope(T instance, Runnable runnable) {
        this.<Void>withScope(instance, () -> { runnable.run(); return null; });
    }
}
