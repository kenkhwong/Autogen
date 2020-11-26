package org.springframework.ken.autogen.function;

import java.util.function.Function;

public interface Increments<T> extends Function<T,T> {

    static Increments<Long> forLong(int increment) {
        return t -> t + increment;
    }

    static Increments<Double> forDouble(float increment) {
        return t -> t + increment;
    }
}
