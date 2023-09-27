package com.trais2.neighborvegetablegarden.models.entity.store;

import com.trais2.neighborvegetablegarden.models.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Store> stores;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
