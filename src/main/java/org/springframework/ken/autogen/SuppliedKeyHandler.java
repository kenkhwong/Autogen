package org.springframework.ken.autogen;

import java.util.function.Supplier;

public class SuppliedKeyHandler<T> implements KeyHandler<T> {

    private Supplier<T> supplier;

    public SuppliedKeyHandler(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public Supplier<T> getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T next() {
        return supplier.get();
    }
}
