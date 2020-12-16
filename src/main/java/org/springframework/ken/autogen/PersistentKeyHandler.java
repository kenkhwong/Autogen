package org.springframework.ken.autogen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;
import java.util.function.Supplier;

public class PersistentKeyHandler<K,T> implements KeyHandler<T> {

    private final JpaRepository<K,?> keyRepo;
    private final Supplier<K> newKeySupplier;

    private Function<K,T> process;

    public PersistentKeyHandler(JpaRepository<K, ?> keyRepo, Supplier<K> newKeySupplier, Function<K, T> process) {
        this.keyRepo = keyRepo;
        this.newKeySupplier = newKeySupplier;
        this.process = process;
    }

    public Function<K, T> getProcess() {
        return process;
    }

    public void setProcess(Function<K, T> process) {
        this.process = process;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public T next() {
        K key = keyRepo.save(newKeySupplier.get());
        keyRepo.delete(key);

        return process.apply(key);
    }
}
