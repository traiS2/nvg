package com.trais2.neighborvegetablegarden.serviceImpl;

import com.trais2.neighborvegetablegarden.model.entity.Status;
import com.trais2.neighborvegetablegarden.model.entity.store.Category;
import com.trais2.neighborvegetablegarden.model.entity.store.Product;
import com.trais2.neighborvegetablegarden.model.enums.EStatus;
import com.trais2.neighborvegetablegarden.repository.CategoryRepository;
import com.trais2.neighborvegetablegarden.repository.ProductRepository;
import com.trais2.neighborvegetablegarden.repository.StatusRepository;
import com.trais2.neighborvegetablegarden.service.ProductService;
import dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import payload.request.product.CreateProductRequest;
import payload.request.product.UpdateProductRequest;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StatusRepository statusRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StatusRepository statusRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.statusRepository = statusRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDTO> getAllProductsByCategory(int CategoryId) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<Product> products = productRepository.findAllByCategory_Id(CategoryId);
            return products.stream().map(product ->
                    modelMapper.map(product, ProductDTO.class)).
                    toList();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String createProduct(CreateProductRequest productRequest) {
        Optional<Category> categoryOptional = categoryRepository.findById(productRequest.getCategory_id());
        Optional<Status> statusOptional = statusRepository.findByName(EStatus.INACTIVE.toString());
        if (categoryOptional.isEmpty()) {
            return "Category not found";
        } else if (statusOptional.isEmpty()) {
            return "Status not found";
        } else {
            Product productCreate = new Product();
            productCreate.setName(productRequest.getName());
            productCreate.setImage(productRequest.getImage());
            productCreate.setStatus(statusOptional.get());
            productCreate.setCategory(categoryOptional.get());
            try {
                productRepository.save(productCreate);
            } catch (Exception e) {
                return "Error when save product";
            }
        }
        return null;

    }

    @Override
    public String updateProduct(UpdateProductRequest productRequest) {
        try {
            Optional<Status> statusOptional = statusRepository.findById(productRequest.getStatus_id());
            Optional<Category> categoryOptional = categoryRepository.findById(productRequest.getCategory_id());
            Optional<Product> productOptional = productRepository.findById(productRequest.getId());
            if (statusOptional.isEmpty()) {
                return "Status not found";
            } else if (categoryOptional.isEmpty()) {
                return "Category not found";
            } else if (productOptional.isEmpty()) {
                return "Product not found";
            } else {
                Product productUpdate = productOptional.get();
                productUpdate.setImage(productRequest.getImage());
                productUpdate.setName(productRequest.getName());
                productUpdate.setStatus(statusOptional.get());
                productUpdate.setCategory(categoryOptional.get());
                productRepository.save(productUpdate);
            }
        } catch (Exception e) {
            return "Error when update product";
        }
        return null;
    }

    @Override
    public String updateProductStatus() {
        return null;
    }
}
