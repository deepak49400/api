package com.bazar.services;

import com.bazar.model.Category;
import com.bazar.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
//    public CategoryServiceImpl(){
//        this.categoryRepository=categoryRepository;
//    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> category = categoryRepository.findAll();
        return category;
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        Optional<Category> catId = categoryRepository.findById(id);
        return catId;
    }
}
