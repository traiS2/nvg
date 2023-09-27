package com.trais2.neighborvegetablegarden.models.entity.store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int store_product_id;
}
