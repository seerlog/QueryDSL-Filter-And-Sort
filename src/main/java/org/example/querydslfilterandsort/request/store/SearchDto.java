package org.example.querydslfilterandsort.request.store;

import com.querydsl.core.types.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    private LinkedList<Sort> sorts;
    private List<Filter> filters;

    @Getter
    @Setter
    public static class Sort {
        String field;
        String direction;

        public Order getDirection() {
            if("asc".equalsIgnoreCase(direction)) {
                return Order.ASC;
            }
            return Order.DESC;
        }
    }

    @Getter
    @Setter
    public static class Filter {
        String field;
        List<String> filter;
        boolean all;
    }
}
