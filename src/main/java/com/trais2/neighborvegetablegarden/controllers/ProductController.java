package com.trais2.neighborvegetablegarden.controllers;

import com.trais2.neighborvegetablegarden.config.GlobalExceptionHandler;
import com.trais2.neighborvegetablegarden.models.entity.store.Product;
import com.trais2.neighborvegetablegarden.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    private final GlobalExceptionHandler validationExceptionHandler;

    @Autowired
    public ProductController(ProductService productService, GlobalExceptionHandler validationExceptionHandler) {
        this.productService = productService;
        this.validationExceptionHandler = validationExceptionHandler;
    }

    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
                if(errorMessage != null) {
                    return ResponseEntity.badRequest().body(errorMessage);
                } else {
                    Product productCreated = productService.addProduct(product);
                    return ResponseEntity.ok().body(productCreated);
                }
    }
}
