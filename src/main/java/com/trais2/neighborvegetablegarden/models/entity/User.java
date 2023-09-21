package com.trais2.neighborvegetablegarden.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Email email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Phone phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Column(name = "created_at")
    private String createdAt;

    public User(Account account, Set<Role> roles) {
        this.account = account;
        this.roles = roles;
    }
}
