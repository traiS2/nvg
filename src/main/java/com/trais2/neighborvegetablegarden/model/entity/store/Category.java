package com.trais2.neighborvegetablegarden.model.entity.store;

import com.trais2.neighborvegetablegarden.model.entity.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retail_counter_id", referencedColumnName = "id")
    private RetailCounter retailCounter;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Store> stores;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

}
