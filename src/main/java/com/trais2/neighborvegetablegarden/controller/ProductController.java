package com.trais2.neighborvegetablegarden.controller;

import com.trais2.neighborvegetablegarden.config.GlobalExceptionHandler;
import com.trais2.neighborvegetablegarden.serviceImpl.ProductServiceImpl;
import dto.ObjectReturn;
import dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import payload.request.product.CreateProductRequest;

@Controller
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServiceImpl productService;

    private final GlobalExceptionHandler validationExceptionHandler;

    @Autowired
    public ProductController(ProductServiceImpl productService, GlobalExceptionHandler validationExceptionHandler) {
        this.productService = productService;
        this.validationExceptionHandler = validationExceptionHandler;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct( @RequestBody CreateProductRequest product, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        } else {
            ObjectReturn<ProductDTO> objectReturn = productService.addProduct(product);
            if (objectReturn.getMessage() != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(objectReturn.getObject());
            } else {
                return ResponseEntity.badRequest().body(objectReturn.getMessage());
            }
        }
    }
}
