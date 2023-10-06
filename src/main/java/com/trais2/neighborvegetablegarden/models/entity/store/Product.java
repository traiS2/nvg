package com.trais2.neighborvegetablegarden.models.entity.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    @NotEmpty(message = "Product name is required")
    @Column(unique = true)
    private String name;

    @NotEmpty(message = "Product description is required")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @NotEmpty(message = "Product category is required")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<StoreProduct> stores_products;
}
