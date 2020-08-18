package com.restful.api.respository.wapper;

import com.restful.api.vo.CategoryVO;

import java.util.List;

public interface CategoryWrapper {
    List<CategoryVO> findAllDetail();

    CategoryVO getCategoryDetail(Integer categoryId);
}
