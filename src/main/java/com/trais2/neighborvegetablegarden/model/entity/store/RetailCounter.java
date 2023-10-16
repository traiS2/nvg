package com.trais2.neighborvegetablegarden.model.entity.store;

import com.trais2.neighborvegetablegarden.model.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "retail_counter")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RetailCounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @OneToMany(mappedBy = "retailCounter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Category> categories;
}
