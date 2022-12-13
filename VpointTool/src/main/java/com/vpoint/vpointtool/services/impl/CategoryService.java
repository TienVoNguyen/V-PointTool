package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.models.entity.Category;
import com.vpoint.vpointtool.repositories.CategoryRepository;
import com.vpoint.vpointtool.services.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.PrimitiveIterator;
@Service
public class CategoryService implements ICategoriesService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }
}
