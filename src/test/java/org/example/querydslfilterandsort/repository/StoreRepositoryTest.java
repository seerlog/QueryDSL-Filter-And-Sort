package org.example.querydslfilterandsort.repository;

import org.example.querydslfilterandsort.domain.store.repository.CustomStoreRepo;
import org.example.querydslfilterandsort.domain.store.repository.StoreRepository;
import org.example.querydslfilterandsort.request.store.StoreSearchDto;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestQueryDslConfig.class)
public class StoreRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(StoreRepositoryTest.class);

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CustomStoreRepo customStoreRepo;

    @Test
    public void jpaRepositoryTest() {
        storeRepository.findAll().forEach(store -> {
            logger.info(store::toString);
        });
    }

    @Test
    public void queryDslTest() {
        StoreSearchDto storeSearchDto = new StoreSearchDto();
        customStoreRepo.getStores(storeSearchDto);
    }
}
