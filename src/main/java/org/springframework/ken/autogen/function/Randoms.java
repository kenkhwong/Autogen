package org.springframework.ken.autogen.function;

import java.util.Random;
import java.util.function.Supplier;

public interface Randoms<T> extends Supplier<T> {

    static Randoms<Integer> ofInt(Random generator) {
        return generator::nextInt;
    }

    static Randoms<Integer> ofInt(Random generator, int bound) {
        return () -> generator.nextInt(bound);
    }

    static Randoms<Integer> ofPositiveInt(Random generator, int max) {
        return () -> 1 + generator.nextInt(max);
    }

    static Randoms<Long> ofLong(Random generator) {
        return generator::nextLong;
    }
}
