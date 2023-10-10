package com.trais2.neighborvegetablegarden.serviceImpl;

import com.trais2.neighborvegetablegarden.model.entity.Status;
import com.trais2.neighborvegetablegarden.model.entity.store.Category;
import com.trais2.neighborvegetablegarden.model.enums.EStatus;
import com.trais2.neighborvegetablegarden.repository.CategoryRepository;
import com.trais2.neighborvegetablegarden.repository.StatusRepository;
import com.trais2.neighborvegetablegarden.service.CategoryService;
import dto.CategoryDTO;
import dto.ObjectReturn;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payload.request.category.CreateCategoryRequest;
import payload.request.category.UpdateCategoryRequest;
import payload.request.category.UpdateCategoryStatusRequest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, StatusRepository statusRepository) {
        this.categoryRepository = categoryRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public String addCategory(CreateCategoryRequest categoryRequest) {
        Optional<Status> status = statusRepository.findByName(EStatus.INACTIVE.toString());
        Category category = new Category();
        if (status.isPresent()) {
            category.setStatus(status.get());
            category.setName(categoryRequest.getName());
        } else {
            return "Status not found";
        }
        Category categoryCreated = categoryRepository.save(category);
        ModelMapper modelMapper = new ModelMapper();
        return null;
    }

    @Override
    public String updateCategory(UpdateCategoryRequest categoryRequest) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryRequest.getCategory_id());
        if (categoryOptional.isPresent()) {
            categoryOptional.get().setName(categoryRequest.getName());
        } else {
            return "Category not found";
        }
        return null;
    }

    @Override
    public String updateCategoryStatus(UpdateCategoryStatusRequest categoryRequest) {
        ObjectReturn<CategoryDTO> objectReturn = new ObjectReturn<>();
        Optional<Category> categoryOptional = categoryRepository.findById(categoryRequest.getCategory_id());
        Optional<Status> statusOptional = statusRepository.findById(categoryRequest.getStatus_id());
        if (categoryOptional.isEmpty()) {
            return "Category not found";
        } else if (statusOptional.isEmpty()) {
            return "Status not found";
        } else {
            categoryOptional.get().setStatus(statusOptional.get());
            Category categoryNew = categoryRepository.save(categoryOptional.get());
            ModelMapper modelMapper = new ModelMapper();
            objectReturn.setObject(modelMapper.map(categoryNew, CategoryDTO.class));
        }
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        ModelMapper modelMapper = new ModelMapper();
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .sorted(Comparator.comparing(CategoryDTO::getCategory_id))
                .toList();
    }
}
