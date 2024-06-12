package org.example.querydslfilterandsort.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.querydslfilterandsort.domain.store.Store;
import org.example.querydslfilterandsort.domain.store.repository.CustomStoreRepo;
import org.example.querydslfilterandsort.domain.store.repository.StoreRepository;
import org.example.querydslfilterandsort.request.store.StoreSearchDto;
import org.example.querydslfilterandsort.response.store.StoreResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final CustomStoreRepo customStoreRepo;

    @PostConstruct
    public void init() {
        List<Store> storeList = new ArrayList<>();
        storeList.add(Store.of(1, "운영중", LocalDate.of(2024, 1, 8)));
        storeList.add(Store.of(2, "운영중", LocalDate.of(2024, 1, 17)));
        storeList.add(Store.of(3, "휴업", LocalDate.of(2024, 2, 3)));
        storeList.add(Store.of(4, "폐업", LocalDate.of(2024, 2, 22)));
        storeList.add(Store.of(5, "운영중", LocalDate.of(2024, 3, 1)));
        storeRepository.saveAll(storeList);
    }

    public Page<StoreResponseDto> getStores(StoreSearchDto dto) {
        return customStoreRepo.getStores(dto);
    }

}
