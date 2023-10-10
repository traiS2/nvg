package com.trais2.neighborvegetablegarden.service;

import com.trais2.neighborvegetablegarden.model.entity.store.Category;
import dto.CategoryDTO;
import org.springframework.stereotype.Service;
import payload.request.category.UpdateCategoryRequest;
import payload.request.category.UpdateCategoryStatusRequest;
import payload.request.category.CreateCategoryRequest;

import java.util.List;

@Service
public interface CategoryService {
    public String addCategory(CreateCategoryRequest category);
    public String updateCategory(UpdateCategoryRequest category);
    public String updateCategoryStatus(UpdateCategoryStatusRequest category);
    public List<CategoryDTO> getAllCategories();
}
