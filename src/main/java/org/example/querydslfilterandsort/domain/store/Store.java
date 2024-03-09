package org.example.querydslfilterandsort.domain.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Store {

    @Id
    @Column(name = "NO", nullable = false)
    private Long no;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS_BASE", nullable = false)
    private String addressBase;

    @Column(name = "ADDRESS_DETAIL", nullable = false)
    private String addressDetail;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "OPENING_DATE", nullable = false)
    private LocalDate openingDate;

    @Column(name = "CLOSING_DATE")
    private LocalDate closingDate;

    @Column(name = "CREATOR", nullable = false)
    private String creator;

    @Column(name = "MODIFIER")
    private String modifier;

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @Column(name = "IS_DELETED", nullable = false)
    private String isDeleted;

    public static Store of(long no, String state, LocalDate openingDate) {
        return Store.builder()
                .no(no)
                .name("Store" + no)
                .addressBase("AddressBase" + no)
                .addressDetail("AddressDetail" + no)
                .phoneNumber("010-1234-567" + no)
                .state(state)
                .openingDate(openingDate)
                .closingDate(LocalDate.of(2999, 12, 31))
                .creator("Admin")
                .isDeleted("N")
                .build();
    }
}
