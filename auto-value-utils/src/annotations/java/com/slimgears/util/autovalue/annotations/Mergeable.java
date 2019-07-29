package com.slimgears.util.autovalue.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings("WeakerAccess")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mergeable {
}
