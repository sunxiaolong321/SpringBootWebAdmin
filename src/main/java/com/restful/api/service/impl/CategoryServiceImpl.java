package com.restful.api.service.impl;

import com.restful.api.entity.Category;
import com.restful.api.respository.CategoryRepository;
import com.restful.api.service.CategoryService;
import com.restful.api.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseGet(Category::new);
    }

    @Override
    @Transactional
    public Integer saveCategory(Category category) {
        return categoryRepository.save(category).getId();
    }

    @Override
    public Integer updateCategory(Category category) {
        Optional<Category> oldCategory = categoryRepository.findById(category.getId());
        if (oldCategory.isPresent()) {
            oldCategory.get().setName(category.getName());
            oldCategory.get().setDescription(category.getDescription());
        }
        return -1;
    }

    @Override
    @Transactional
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryVO> findAllDetail() {
//        return categoryRepository.findAllDetail();
        return null;
    }

    @Override
    public CategoryVO getCategoryDetail(Integer categoryId) {
//        return categoryRepository.getCategoryDetail(categoryId);
        return null;
    }
}
