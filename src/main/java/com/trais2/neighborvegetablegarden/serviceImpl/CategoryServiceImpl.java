package com.trais2.neighborvegetablegarden.serviceImpl;

import com.trais2.neighborvegetablegarden.model.entity.Status;
import com.trais2.neighborvegetablegarden.model.entity.store.Category;
import com.trais2.neighborvegetablegarden.model.entity.store.RetailCounter;
import com.trais2.neighborvegetablegarden.model.enums.EStatus;
import com.trais2.neighborvegetablegarden.repository.CategoryRepository;
import com.trais2.neighborvegetablegarden.repository.RetailCounterRepository;
import com.trais2.neighborvegetablegarden.repository.StatusRepository;
import com.trais2.neighborvegetablegarden.service.CategoryService;
import dto.CategoryDTO;
import dto.CategoryProductsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import payload.request.category.CreateCategoryRequest;
import payload.request.category.UpdateCategoryRequest;
import payload.request.category.UpdateCategoryStatusRequest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;

    private final RetailCounterRepository retailCounterRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, StatusRepository statusRepository, RetailCounterRepository retailCounterRepository) {
        this.categoryRepository = categoryRepository;
        this.statusRepository = statusRepository;
        this.retailCounterRepository = retailCounterRepository;
    }


    @Override
    public List<?> getAllCategoriesWithProducts() {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<?> categories = categoryRepository.getAllCategoriesWithProducts();
            return categories.stream().map(
                    category -> modelMapper.map(category, CategoryProductsDTO.class)
            ).toList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String addCategory(CreateCategoryRequest categoryRequest) {
        Optional<Status> status = statusRepository.findByName(EStatus.INACTIVE.toString());
        Optional<RetailCounter> retailCounter = retailCounterRepository.findById(categoryRequest.getRetailCounterId());
        Category category = new Category();
        if (status.isPresent()) {
            if (retailCounter.isPresent()) {
                category.setName(categoryRequest.getName());
                category.setRetailCounter(retailCounter.get());
            } else {
                return "Retail counter not found";
            }
            category.setStatus(status.get());
        } else {
            return "Status not found";
        }
        Category categoryCreated = categoryRepository.save(category);
        ModelMapper modelMapper = new ModelMapper();
        return null;
    }

    @Override
    public String updateCategory(UpdateCategoryRequest categoryRequest) {
        try {

            Optional<Category> categoryOptional = categoryRepository.findById(categoryRequest.getId());
            Optional<RetailCounter> retailCounterOptional = retailCounterRepository.findById(categoryRequest.getRetailCounterId());
            Optional<Status> statusOptional = statusRepository.findById(categoryRequest.getStatusId());
            if (categoryOptional.isPresent()) {
                if (retailCounterOptional.isPresent()) {
                    if (statusOptional.isPresent()) {
                        categoryOptional.get().setName(categoryRequest.getName());
                        categoryOptional.get().setRetailCounter(retailCounterOptional.get());
                        categoryOptional.get().setStatus(statusOptional.get());
                        categoryRepository.save(categoryOptional.get());
                    } else {
                        return "Status not found";
                    }
                } else {
                    return "Retail counter not found";
                }
            } else {
                return "Category not found";
            }
        } catch (Exception e) {
            return "Error when update category";
        }
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream()
                    .map(category -> modelMapper.map(category, CategoryDTO.class))
                    .sorted(Comparator.comparing(CategoryDTO::getId))
                    .toList();
        } catch (Exception e) {
            return null;
        }
    }
}