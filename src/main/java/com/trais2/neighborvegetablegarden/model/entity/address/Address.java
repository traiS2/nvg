package com.trais2.neighborvegetablegarden.model.entity.address;

import com.trais2.neighborvegetablegarden.model.entity.store.Store;
import com.trais2.neighborvegetablegarden.model.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    private int id;

    @Column(name = "detail_address",columnDefinition = "nvarchar(255)")
    private String detailAddress;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;

    @ManyToOne
    @JoinColumn(name = "commune_id", referencedColumnName = "id")
    private Commune commune;

    @OneToOne(mappedBy = "address")
    private User user;

    @OneToOne(mappedBy = "address")
    private Store store;
}
