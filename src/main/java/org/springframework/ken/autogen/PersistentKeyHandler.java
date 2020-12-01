package org.springframework.ken.autogen;

import org.springframework.ken.autogen.jpa.PersistentKey;
import org.springframework.ken.autogen.jpa.PersistentKeyRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class PersistentKeyHandler<K extends PersistentKey<T>,T> implements KeyHandler<T> {

    private final Supplier<K> initKeySupplier;
    private final PersistentKeyRepository<K> keyRepo;

    private Function<T,T> nextValueFunction;

    public PersistentKeyHandler(Supplier<K> initKeySupplier, PersistentKeyRepository<K> keyRepo,
                             Function<T,T> nextValueFunction) {
        this.initKeySupplier = initKeySupplier;
        this.keyRepo = keyRepo;
        this.nextValueFunction = nextValueFunction;
    }

    public Function<T, T> getNextValueFunction() {
        return nextValueFunction;
    }

    public void setNextValueFunction(Function<T, T> f) {
        this.nextValueFunction = f;
    }

    @Override
    @Transactional
    public T next() {
        Optional<K> optional = keyRepo.findById(PersistentKey.ONE_ID);
        K nextKey;

        if (optional.isPresent()) {
            optional.get().setKeyValue(nextValueFunction.apply(optional.get().getKeyValue()));
            nextKey = keyRepo.save(optional.get());

        } else {
            nextKey = keyRepo.save(initKeySupplier.get());
        }
        return nextKey.getKeyValue();
    }

    public <P> KeyHandler<P> postProcess(Function<T,P> postFunction) {
        return new KeyHandler<P>() {
            @Override
            @Transactional
            public P next() {
                return postFunction.apply(PersistentKeyHandler.this.next());
            }
        };
    }
}
