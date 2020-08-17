package com.restful.api.respository.wapper;

import com.restful.api.vo.TagVO;

import java.util.List;

public interface TagWrapper {
    List<TagVO> findAllDetail();

    TagVO getTagDetail(Integer tagId);
}
