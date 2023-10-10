package com.trais2.neighborvegetablegarden.controller;

import com.trais2.neighborvegetablegarden.config.GlobalExceptionHandler;
import com.trais2.neighborvegetablegarden.service.CategoryService;
import dto.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import payload.request.category.CreateCategoryRequest;
import payload.request.category.UpdateCategoryRequest;
import payload.request.category.UpdateCategoryStatusRequest;
import payload.response.MessageResponse;
import payload.response.category.GetAllCategoryResponse;

import java.util.List;

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
    public ResponseEntity<MessageResponse> createCategory(@Valid @RequestBody CreateCategoryRequest category, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            String createCategoryMessage = categoryService.addCategory(category);
            return createCategoryMessage == null
                    ? ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Category created successfully"))
                    : ResponseEntity.badRequest().body(new MessageResponse(createCategoryMessage));
        }
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GetAllCategoryResponse> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok().body(new GetAllCategoryResponse(categories));
    }

    @PutMapping("/update-category-name")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> updateCategory(@Valid @RequestBody UpdateCategoryRequest categoryRequest, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            String updateCategoryMessage = categoryService.updateCategory(categoryRequest);
            return updateCategoryMessage == null
                    ? ResponseEntity.ok().body(new MessageResponse("Category updated successfully"))
                    : ResponseEntity.badRequest().body(new MessageResponse(updateCategoryMessage));
        }
    }

    @PutMapping("/update-category-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> updateCategoryStatus(@Valid @RequestBody UpdateCategoryStatusRequest categoryRequest, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            String updateCategoryStatusMessage = categoryService.updateCategoryStatus(categoryRequest);
            return updateCategoryStatusMessage == null
                    ? ResponseEntity.ok().body(new MessageResponse("Category status updated successfully"))
                    : ResponseEntity.badRequest().body(new MessageResponse(updateCategoryStatusMessage));
        }
    }
}
