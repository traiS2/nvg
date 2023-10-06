package com.trais2.neighborvegetablegarden.models.entity.store;

import com.trais2.neighborvegetablegarden.models.entity.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.util.Set;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @Column(name = "name", columnDefinition = "nvarchar(255)")
    @Nationalized
    @NotEmpty(message = "Category name must not be empty")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")

    private Status status;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Store> stores;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products;

}
