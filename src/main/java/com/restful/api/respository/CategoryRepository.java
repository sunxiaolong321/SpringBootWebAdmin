package com.restful.api.respository;

import com.restful.api.entity.Category;
import com.restful.api.respository.wapper.CategoryWrapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>, CategoryWrapper {
}
