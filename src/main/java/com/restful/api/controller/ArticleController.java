package com.restful.api.controller;

import com.restful.api.service.ArticleService;
import com.restful.api.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {

    private ArticleService articleService;
    private TagService tagService;

}
