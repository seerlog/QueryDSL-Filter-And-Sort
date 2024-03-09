package org.example.querydslfilterandsort.domain.store.repository;

import org.example.querydslfilterandsort.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
