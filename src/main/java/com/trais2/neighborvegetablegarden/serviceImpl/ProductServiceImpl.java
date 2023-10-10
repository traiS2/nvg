package com.trais2.neighborvegetablegarden.serviceImpl;

import com.trais2.neighborvegetablegarden.model.entity.Status;
import com.trais2.neighborvegetablegarden.model.entity.store.Category;
import com.trais2.neighborvegetablegarden.model.entity.store.Product;
import com.trais2.neighborvegetablegarden.model.enums.EStatus;
import com.trais2.neighborvegetablegarden.repository.CategoryRepository;
import com.trais2.neighborvegetablegarden.repository.ProductRepository;
import com.trais2.neighborvegetablegarden.repository.StatusRepository;
import dto.ObjectReturn;
import dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payload.request.product.CreateProductRequest;

import java.util.Optional;

@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final StatusRepository statusRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StatusRepository statusRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.statusRepository = statusRepository;
        this.categoryRepository = categoryRepository;
    }

    public ObjectReturn<ProductDTO> addProduct(CreateProductRequest product) {
        ObjectReturn<ProductDTO> objectReturn = new ObjectReturn<>();
        Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory_id());
        Optional<Status> statusOptional = statusRepository.findByName(EStatus.INACTIVE.toString());
        if(categoryOptional.isEmpty()) {
            objectReturn.setMessage("Category not found");
        } else if (statusOptional.isEmpty()) {
            objectReturn.setMessage("Status not found");
        } else {
            Product productCreate = new Product();
            productCreate.setName(product.getName());
            productCreate.setImage(product.getImage());
            productCreate.setStatus(statusOptional.get());
            productCreate.setCategory(categoryOptional.get());
            Product productSaved = productRepository.save(productCreate);

        }
        return objectReturn;

    }
}
