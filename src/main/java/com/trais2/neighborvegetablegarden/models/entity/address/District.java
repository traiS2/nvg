package com.trais2.neighborvegetablegarden.models.entity.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "district")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class District {
    @Id
    private int district_id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "province_id")
    private Province province;

    @OneToMany(mappedBy = "district")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "district")
    private Set<Commune> communes;
}
