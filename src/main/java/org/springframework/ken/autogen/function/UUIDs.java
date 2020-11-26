package org.springframework.ken.autogen.function;

import java.util.UUID;
import java.util.function.Supplier;

public interface UUIDs extends Supplier<UUID> {

    static UUIDs random() {
        return UUID::randomUUID;
    }

    static UUIDs fromString(String name) {
        return () -> UUID.fromString(name);
    }

    static UUIDs nameFromBytes(byte[] name) {
        return () -> UUID.nameUUIDFromBytes(name);
    }
}
