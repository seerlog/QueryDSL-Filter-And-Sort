package org.example.querydslfilterandsort.request.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreSearchDto extends SearchDto {
    @Min(value = 0, message = "pageSize 는 0 이하일 수 없습니다.")
    private int pageSize = 20;

    @Min(value = 0, message = "pageNumber 는 0 이하일 수 없습니다.")
    private int pageNumber = 0;

    @JsonIgnore
    public long getOffset() {
        return (long) pageNumber * pageSize;
    }

    @JsonIgnore
    public long getLimit() {
        return pageSize;
    }
}
