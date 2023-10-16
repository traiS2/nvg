package com.trais2.neighborvegetablegarden.model.entity.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trais2.neighborvegetablegarden.model.entity.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Product name is required")
    @Column(unique = true, name = "name", columnDefinition = "nvarchar(255)")
    private String name;

    @NotEmpty(message = "Product description is required")
    private String image;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @Column(name = "stores_products")
    private Set<StoreProduct> storesProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public Product(String name, String image, Status status, Category category) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.category = category;
    }
}
