package com.slimgears.util.autovalue.expressions.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.slimgears.util.autovalue.expressions.ObjectExpression;
import com.slimgears.util.autovalue.expressions.StringExpression;
import com.slimgears.util.autovalue.expressions.UnaryOperationExpression;

@AutoValue
public abstract class StringUnaryOperationExpression<T> implements UnaryOperationExpression<T, String>, StringExpression {
    @JsonCreator
    public static <T> StringUnaryOperationExpression<T> create(
            @JsonProperty("type") Type type,
            @JsonProperty("operand") ObjectExpression<T> operand) {
        return new AutoValue_StringUnaryOperationExpression<>(type, operand);
    }
}
