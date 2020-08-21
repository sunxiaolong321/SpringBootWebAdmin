package com.restful.api.controller;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.restful.api.common.result.Result;
import com.restful.api.entity.Tag;
import com.restful.api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tags")
public class TagController {
    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    @FastJsonView(include = {@FastJsonFilter(clazz = Tag.class, props = {"id", "tagName"})})
    public Result getTags() {
        return Result.success(tagService.findAll());
    }
}
