package com.trais2.neighborvegetablegarden.controller;

import com.trais2.neighborvegetablegarden.config.GlobalExceptionHandler;
import com.trais2.neighborvegetablegarden.service.ProductService;
import com.trais2.neighborvegetablegarden.serviceImpl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import payload.request.product.CreateProductRequest;
import payload.request.product.UpdateProductRequest;
import payload.response.MessageResponse;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    private final GlobalExceptionHandler validationExceptionHandler;

    @Autowired
    public ProductController(ProductServiceImpl productService, GlobalExceptionHandler validationExceptionHandler) {
        this.productService = productService;
        this.validationExceptionHandler = validationExceptionHandler;
    }

    @GetMapping("/get-product-by-category/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllProducts(@PathVariable int categoryId) {
        List<?> products = productService.getAllProductsByCategory(categoryId);
        if (products != null) {
            return ResponseEntity.ok().body(products);
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Get products failed"));
        }
    }

    @PostMapping("/create-product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest product, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        } else {
            String createProductMessage = productService.createProduct(product);
            if (createProductMessage != null) {
                return ResponseEntity.badRequest().body(new MessageResponse(createProductMessage));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Product created successfully"));
            }
        }
    }

    @PutMapping("/update-product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody UpdateProductRequest productRequest, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        } else {
            String updateProductMessage = productService.updateProduct(productRequest);
            if (updateProductMessage != null) {
                return ResponseEntity.badRequest().body(new MessageResponse(updateProductMessage));
            } else {
                return ResponseEntity.ok().body(new MessageResponse("Product updated successfully"));
            }
        }
    }
}
