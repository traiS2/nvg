package com.trais2.neighborvegetablegarden.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "account_id"),
        @UniqueConstraint(columnNames = "username")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
