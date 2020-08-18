package com.restful.api.service;

import com.restful.api.entity.Category;
import com.restful.api.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    Iterable<Category> findAll();

    Category getCategoryById(Integer id);

    Integer saveCategory(Category category);

    Integer updateCategory(Category category);

    void deleteCategoryById(Integer id);

    List<CategoryVO> findAllDetail();

    CategoryVO getCategoryDetail(Integer categoryId);
}
