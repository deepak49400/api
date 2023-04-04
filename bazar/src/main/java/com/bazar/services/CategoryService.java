package com.bazar.services;

import com.bazar.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public void addCategory(Category category);

    public List<Category> getAllCategory();

   public void deleteCategoryById(int id);

   public Optional<Category> getCategoryById(int id);


}
