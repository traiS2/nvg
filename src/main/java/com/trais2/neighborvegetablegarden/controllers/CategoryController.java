package com.trais2.neighborvegetablegarden.controllers;

import com.trais2.neighborvegetablegarden.config.GlobalExceptionHandler;
import com.trais2.neighborvegetablegarden.models.entity.store.Category;
import com.trais2.neighborvegetablegarden.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final GlobalExceptionHandler validationExceptionHandler;

    @Autowired
    public CategoryController(CategoryService categoryService, GlobalExceptionHandler validationExceptionHandler) {
        this.categoryService = categoryService;
        this.validationExceptionHandler = validationExceptionHandler;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        return errorMessage != null
                ? ResponseEntity.badRequest().body(errorMessage)
                : ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody Category category, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        } else {
            return categoryService.updateCategory(category) != null
                    ? ResponseEntity.ok().body("Category updated successfully")
                    : ResponseEntity.badRequest().body("Category not found");
        }
    }
}
