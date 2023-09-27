package com.trais2.neighborvegetablegarden.models.entity.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "province")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Province {
    @Id
    private int province_id;

    private String name;

    @OneToMany(mappedBy = "province")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "province")
    private Set<District> districts;
}
