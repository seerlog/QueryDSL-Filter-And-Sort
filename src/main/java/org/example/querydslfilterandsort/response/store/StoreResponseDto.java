package org.example.querydslfilterandsort.response.store;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {
    private long no;
    private String name;
    private String addressBase;
    private String addressDetail;
    private String phoneNumber;
    private String state;
    private LocalDate openingDate;
    private LocalDate closingDate;
}
