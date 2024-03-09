package org.example.querydslfilterandsort.utils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import org.example.querydslfilterandsort.request.store.SearchDto;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class QueryDslUtil {

    public static BooleanExpression isEqual(StringExpression expression, String value) {
        if(ObjectUtils.isEmpty(value)) {
            return null;
        }
        return expression.eq(value);
    }

    public static BooleanExpression isIn(StringExpression expression, List<String> idList) {
        if(ObjectUtils.isEmpty(idList)) {
            return null;
        }
        return expression.in(idList);
    }

    public static BooleanBuilder createFilterConditions(HashMap<String, SimpleExpression> expressionMap,
                                                        List<SearchDto.Filter> filters) {
        if(ObjectUtils.isEmpty(filters)) {
            return null;
        }

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for(SearchDto.Filter filter : filters) {
            if(!filter.isAll()) {
                booleanBuilder.and(expressionMap.get(filter.getField()).in(filter.getFilter()));
            }
        }

        return booleanBuilder;
    }

    public static OrderSpecifier[] createOrderSpecifiers(HashMap<String, SimpleExpression> expressionMap,
                                                         LinkedList<SearchDto.Sort> sorts) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

        if(ObjectUtils.isEmpty(sorts)) {
            return orderSpecifiers.toArray(OrderSpecifier[]::new);
        }

        for(SearchDto.Sort sort : sorts) {
            orderSpecifiers.add(new OrderSpecifier(sort.getDirection(), expressionMap.get(sort.getField())));
        }

        return orderSpecifiers.toArray(OrderSpecifier[]::new);
    }
}
