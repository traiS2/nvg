package com.trais2.neighborvegetablegarden.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "email", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long email_id;

    @NotBlank
    private String email;

    @NotBlank
    private String access_token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
