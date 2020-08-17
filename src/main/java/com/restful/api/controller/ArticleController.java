package com.restful.api.controller;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.restful.api.common.annotation.LogAnnotation;
import com.restful.api.common.constant.ResultCode;
import com.restful.api.common.result.Result;
import com.restful.api.entity.Article;
import com.restful.api.entity.Tag;
import com.restful.api.entity.User;
import com.restful.api.service.ArticleService;
import com.restful.api.service.TagService;
import com.restful.api.vo.ArticleVo;
import com.restful.api.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {

    private ArticleService articleService;
    private TagService tagService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    @FastJsonView(
            exclude = {
                    @FastJsonFilter(clazz = Article.class, props = {"body", "category", "comments"}),
                    @FastJsonFilter(clazz = Tag.class, props = {"id", "avatar"})},
            include = {@FastJsonFilter(clazz = User.class, props = {"nickname"})})
    @LogAnnotation(module = "文章", operation = "获取所有文章")
    public Result listArticles(ArticleVo article, PageVo page) {
        System.out.println(article);
        System.out.println(page);
        List<Article> articles = articleService.listArticles(article, page);
        return Result.success(articles);
    }

    @GetMapping("/hot")
    @FastJsonView(include = {@FastJsonFilter(clazz = Article.class, props = {"id", "title"})})
    @LogAnnotation(module = "文章", operation = "获取最热文章")
    public Result listHotArticles() {
        int limit = 6;
        List<Article> articles = articleService.listHotArticles(limit);
        return Result.success(articles);
    }

    @PostMapping("/publish")
    @LogAnnotation(module = "文章", operation = "发布文章")
    public Result saveArticle(@RequestBody Article article) {

        Integer articleId = articleService.publishArticle(article);
        if (articleId != -1) {
            Result r = Result.success();
            r.simple().put("articleId", articleId);
            return r;
        }
        return Result.error(ResultCode.ERROR);
    }
}
