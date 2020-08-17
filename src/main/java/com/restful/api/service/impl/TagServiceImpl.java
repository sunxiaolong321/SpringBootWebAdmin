package com.restful.api.service.impl;

import com.restful.api.entity.Tag;
import com.restful.api.respository.TagRepository;
import com.restful.api.service.TagService;
import com.restful.api.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Iterable<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> getTagById(Integer id) {
        return tagRepository.findById(id);
    }

    @Override
    @Transactional
    public Integer saveTag(Tag tag) {
        return tagRepository.save(tag).getId();
    }

    @Override
    @Transactional
    public Integer updateTag(Tag tag) {
        Optional<Tag> oldTag = tagRepository.findById(tag.getId());
        if (oldTag.isPresent()) {
            oldTag.get().setTagName(tag.getTagName());
            oldTag.get().setAvatar(tag.getAvatar());
            return oldTag.get().getId();
        }
        return -1;
    }

    @Override
    @Transactional
    public void deleteTagById(Integer id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> listHotTags(int limit) {
        return tagRepository.listHotTagsByArticleUse(limit);
    }

    @Override
    public List<TagVO> findAllDetail() {
        return tagRepository.findAllDetail();
    }

    @Override
    public TagVO getTagDetail(Integer tagId) {
        return tagRepository.getTagDetail(tagId);
    }
}
