package com.trais2.neighborvegetablegarden.model.entity.store;


import com.trais2.neighborvegetablegarden.model.entity.Status;
import com.trais2.neighborvegetablegarden.model.entity.address.Address;
import com.trais2.neighborvegetablegarden.model.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "store")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Store {
    @Id
    private int store_id;

    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    private String image;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "store_category",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @OneToMany(mappedBy = "store")
    private Set<StoreProduct> stores_products;

}
