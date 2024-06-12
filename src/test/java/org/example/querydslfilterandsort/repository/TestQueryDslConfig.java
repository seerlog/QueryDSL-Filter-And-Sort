package org.example.querydslfilterandsort.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.querydslfilterandsort.domain.store.repository.CustomStoreRepo;
import org.example.querydslfilterandsort.domain.store.repository.CustomStoreRepoImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@TestConfiguration
public class TestQueryDslConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public CustomStoreRepo customStoreRepo(JPAQueryFactory jpaQueryFactory) {
        return new CustomStoreRepoImpl(jpaQueryFactory);
    }
}
