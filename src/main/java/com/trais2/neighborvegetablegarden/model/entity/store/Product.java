package com.trais2.neighborvegetablegarden.model.entity.store;

import com.trais2.neighborvegetablegarden.model.entity.Status;
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
    @Column(unique = true, name = "name", columnDefinition = "nvarchar(255)")
    private String name;

    @NotEmpty(message = "Product description is required")
    private String image;

    @OneToMany(mappedBy = "product")
    private Set<StoreProduct> stores_products;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    public Product(String name, String image, Status status, Category category) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.category = category;
    }
}
