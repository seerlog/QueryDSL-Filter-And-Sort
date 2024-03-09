package org.example.querydslfilterandsort.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.querydslfilterandsort.request.store.StoreSearchDto;
import org.example.querydslfilterandsort.response.store.StoreResponseDto;
import org.example.querydslfilterandsort.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/search")
    public ResponseEntity<List<StoreResponseDto>> searchStores(@RequestBody @Valid StoreSearchDto storeSearchDto) {
        return ResponseEntity.ok(storeService.getStores(storeSearchDto).getContent());
    }
}
