package com.trais2.neighborvegetablegarden.models.entity.user;

import com.trais2.neighborvegetablegarden.models.entity.Status;
import com.trais2.neighborvegetablegarden.models.entity.store.Store;
import com.trais2.neighborvegetablegarden.models.entity.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "`user`")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String first_name;

    private String last_name;

    private Date yob;

    private String image;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Email email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Phone phone;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private Store store;

    @Column(name = "created_at")
    private String createdAt;

    public User(Account account, Set<Role> roles) {
        this.account = account;
        this.roles = roles;
    }

}
