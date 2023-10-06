package com.trais2.neighborvegetablegarden.models.entity.store;

import com.trais2.neighborvegetablegarden.models.entity.Status;
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
    private int store_product_id;

    private int quantity;
    private Date production_date;
    private Date expiration_date;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;
}
