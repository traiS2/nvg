package com.trais2.neighborvegetablegarden.model.entity.store;

import com.trais2.neighborvegetablegarden.model.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "store_product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @Column(name = "production_date")
    private Date productionDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;
}
