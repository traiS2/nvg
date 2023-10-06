package com.trais2.neighborvegetablegarden.services;

import com.trais2.neighborvegetablegarden.models.entity.Status;
import com.trais2.neighborvegetablegarden.models.entity.store.Category;
import com.trais2.neighborvegetablegarden.models.enums.EStatus;
import com.trais2.neighborvegetablegarden.repository.CategoryRepository;
import com.trais2.neighborvegetablegarden.repository.StatusRepository;
import dto.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, StatusRepository statusRepository) {
        this.categoryRepository = categoryRepository;
        this.statusRepository = statusRepository;
    }

    public CategoryDTO createCategory(Category category) {
        Optional<Status> status = statusRepository.findByName(EStatus.INACTIVE.toString());
        if (status.isPresent()) {
            category.setStatus(status.get());
        } else {
            throw new RuntimeException("Status not found");
        }
        Category categoryCreated = categoryRepository.save(category);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(categoryCreated, CategoryDTO.class);
    }

    public List<CategoryDTO> getAllCategories() {
        ModelMapper modelMapper = new ModelMapper();
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .sorted(Comparator.comparing(CategoryDTO::getCategory_id))
                .toList();
    }

    public CategoryDTO updateCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(category.getCategory_id());
        if (categoryOptional.isPresent()) {
            Category categoryUpdated = categoryRepository.save(category);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(categoryUpdated, CategoryDTO.class);
        } else {
            throw new RuntimeException("Category not found");
        }
    }
}
