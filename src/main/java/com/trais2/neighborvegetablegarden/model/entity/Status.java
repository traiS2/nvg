package com.trais2.neighborvegetablegarden.model.entity;

import com.trais2.neighborvegetablegarden.model.entity.store.Category;
import com.trais2.neighborvegetablegarden.model.entity.store.Product;
import com.trais2.neighborvegetablegarden.model.entity.store.Store;
import com.trais2.neighborvegetablegarden.model.entity.store.StoreProduct;
import com.trais2.neighborvegetablegarden.model.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "status", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Store> stores;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "stores_products")
    private List<StoreProduct> storesProducts;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> categories;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

}
