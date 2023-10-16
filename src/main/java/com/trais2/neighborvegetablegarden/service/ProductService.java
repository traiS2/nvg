package com.trais2.neighborvegetablegarden.service;

import com.trais2.neighborvegetablegarden.model.entity.store.Product;
import dto.ProductDTO;
import payload.request.product.CreateProductRequest;
import payload.request.product.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProductsByCategory(int CategoryId);
    public String createProduct(CreateProductRequest productRequest);
    public String updateProduct(UpdateProductRequest productRequest);
    public String updateProductStatus();
}
