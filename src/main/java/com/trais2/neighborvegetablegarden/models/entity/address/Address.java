package com.trais2.neighborvegetablegarden.models.entity.address;

import com.trais2.neighborvegetablegarden.models.entity.store.Store;
import com.trais2.neighborvegetablegarden.models.entity.user.User;
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
    private int address_id;

    private String detail_address;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "district_id")
    private District district;

    @ManyToOne
    @JoinColumn(name = "commune_id", referencedColumnName = "commune_id")
    private Commune commune;

    @OneToOne(mappedBy = "address")
    private User user;

    @OneToOne(mappedBy = "address")
    private Store store;
}
