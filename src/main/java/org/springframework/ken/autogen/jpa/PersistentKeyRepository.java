package org.springframework.ken.autogen.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersistentKeyRepository<K extends PersistentKey<?>> extends JpaRepository<K,Integer> {
}
