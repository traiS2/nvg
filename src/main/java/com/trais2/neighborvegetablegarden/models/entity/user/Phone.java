package com.trais2.neighborvegetablegarden.models.entity.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
