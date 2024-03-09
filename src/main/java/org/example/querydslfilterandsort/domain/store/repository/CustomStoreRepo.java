package org.example.querydslfilterandsort.domain.store.repository;

import org.example.querydslfilterandsort.request.store.StoreSearchDto;
import org.example.querydslfilterandsort.response.store.StoreResponseDto;
import org.springframework.data.domain.Page;

public interface CustomStoreRepo {
    Page<StoreResponseDto> getStores(StoreSearchDto storeSearchDto);
}
