package com.restful.api.service;

import com.restful.api.entity.Tag;
import com.restful.api.vo.TagVO;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Iterable<Tag> findAll();
    Optional<Tag> getTagById(Integer id);
    Integer saveTag(Tag tag);
    Integer updateTag(Tag tag);
    void deleteTagById(Integer id);
    List<Tag> listHotTags(int limit);
    List<TagVO> findAllDetail();
    TagVO getTagDetail(Integer tagId);
}
