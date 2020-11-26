package org.springframework.ken.autogen.jpa;

import javax.persistence.*;

@MappedSuperclass
public class PersistentKey<T> {

    public static final int ONE_ID = 1;

    @Id
    private final int id = ONE_ID;

    @Column(nullable = false)
    private T keyValue;

    public PersistentKey() {
    }

    public PersistentKey(T initValue) {
        this.keyValue = initValue;
    }

    public T getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(T keyValue) {
        this.keyValue = keyValue;
    }
}
