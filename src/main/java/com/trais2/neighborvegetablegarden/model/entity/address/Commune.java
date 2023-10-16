package com.trais2.neighborvegetablegarden.model.entity.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "commune")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Commune {
    @Id
    private int id;

    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    @OneToMany(mappedBy = "commune")
    private Set<Address> addresses;

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;
}
