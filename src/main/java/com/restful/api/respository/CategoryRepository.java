package com.restful.api.respository;

import com.restful.api.entity.Category;
import com.restful.api.respository.wapper.CategoryWrapper;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer>, CategoryWrapper {
}
