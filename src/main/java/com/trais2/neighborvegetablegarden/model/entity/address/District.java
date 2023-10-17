package com.trais2.neighborvegetablegarden.model.entity.address;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;

    @OneToMany(mappedBy = "district")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "district")
    private Set<Commune> communes;
}
