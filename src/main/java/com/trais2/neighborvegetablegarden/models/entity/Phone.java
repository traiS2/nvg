package com.trais2.neighborvegetablegarden.models.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "phone", uniqueConstraints = {
        @UniqueConstraint(columnNames = "phone_number")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phone_id;

    @NotBlank
    private String phone_number;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
