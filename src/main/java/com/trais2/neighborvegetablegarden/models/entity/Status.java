package com.trais2.neighborvegetablegarden.models.entity;

import com.trais2.neighborvegetablegarden.models.entity.store.Store;
import com.trais2.neighborvegetablegarden.models.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "status", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Status implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int status_id;

    private String name;

    @OneToMany(mappedBy = "status")
    private Set<User> users;

    @OneToMany(mappedBy = "status")
    private Set<Store> stores;
}
