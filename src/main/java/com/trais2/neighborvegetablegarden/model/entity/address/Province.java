package com.trais2.neighborvegetablegarden.model.entity.address;

import jakarta.persistence.*;
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
    private int id;

    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    @OneToMany(mappedBy = "province")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "province")
    private Set<District> districts;
}
