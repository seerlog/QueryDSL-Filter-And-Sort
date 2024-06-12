package org.example.querydslfilterandsort.domain.store.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.querydslfilterandsort.request.store.StoreSearchDto;
import org.example.querydslfilterandsort.response.store.StoreResponseDto;
import org.example.querydslfilterandsort.utils.QueryDslUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

import static org.example.querydslfilterandsort.domain.store.QStore.store;

@Repository
public class CustomStoreRepoImpl implements CustomStoreRepo {
    private final JPAQueryFactory jpaQueryFactory;
    private final HashMap<String, SimpleExpression> expressionMap = new HashMap<>();

    public CustomStoreRepoImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
        expressionMap.put("no", store.no);
        expressionMap.put("name", store.name);
        expressionMap.put("addressBase", store.addressBase);
        expressionMap.put("addressDetail", store.addressDetail);
        expressionMap.put("phoneNumber", store.phoneNumber);
        expressionMap.put("state", store.state);
        expressionMap.put("openingDate", store.openingDate);
        expressionMap.put("closingDate", store.closingDate);
    }

    @Override
    public Page<StoreResponseDto> getStores(StoreSearchDto dto) {
        List<StoreResponseDto> storeResponseDtoList = jpaQueryFactory
                .select(Projections.bean(StoreResponseDto.class,
                        store.no,
                        store.name,
                        store.addressBase,
                        store.addressDetail,
                        store.phoneNumber,
                        store.state,
                        store.openingDate,
                        store.closingDate
                ))
                .from(store)
                .where(getWhereClause(dto))
                .orderBy(QueryDslUtil.createOrderSpecifiers(expressionMap, dto.getSorts()))
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize());

        Long totalCount = jpaQueryFactory
                .select(store.count())
                .from(store)
                .where(getWhereClause(dto))
                .fetchOne();

        return new PageImpl<>(storeResponseDtoList, pageable, totalCount);
    }

    private BooleanBuilder getWhereClause(StoreSearchDto dto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // other conditions...

        booleanBuilder.and(QueryDslUtil.createFilterConditions(expressionMap, dto.getFilters()));
        return booleanBuilder;
    }
}
