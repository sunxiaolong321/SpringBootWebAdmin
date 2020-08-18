package com.restful.api.controller;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.restful.api.common.annotation.LogAnnotation;
import com.restful.api.common.constant.Base;
import com.restful.api.common.constant.ResultCode;
import com.restful.api.common.result.Result;
import com.restful.api.entity.Article;
import com.restful.api.entity.ArticleBody;
import com.restful.api.entity.Tag;
import com.restful.api.entity.User;
import com.restful.api.service.ArticleService;
import com.restful.api.service.TagService;
import com.restful.api.vo.ArticleVo;
import com.restful.api.vo.PageVo;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    @GetMapping("/new")
    @FastJsonView(include = {@FastJsonFilter(clazz = Article.class, props = {"id", "title"})})
    @LogAnnotation(module = "文章", operation = "获取最新的文章")
    public Result listNewArticles() {
        int limit = 6;
        List<Article> articles = articleService.listNewArticles(6);
        return Result.success(articles);
    }

    @GetMapping("/{id}")
    @FastJsonView(exclude = {@FastJsonFilter(clazz = Article.class, props = {"comments"}),
            @FastJsonFilter(clazz = ArticleBody.class, props = {"contentHtml"})})
    @LogAnnotation(module = "文章", operation = "根据id获取文章")
    public Result getArticleById(@PathVariable("id") Integer id) {
        Result r = new Result();
        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }
        Article article = articleService.getArticleById(id);
        r.setResultCode(ResultCode.SUCCESS);
        r.setData(article);
        return r;
    }

    @GetMapping("/view/{id}")
    @FastJsonView(
            exclude = {
                    @FastJsonFilter(clazz = Article.class, props = {"comments"}),
                    @FastJsonFilter(clazz = ArticleBody.class, props = {"contentHtml"}),
                    @FastJsonFilter(clazz = Tag.class, props = {"avatar"})},
            include = {@FastJsonFilter(clazz = User.class, props = {"id", "nickname", "avatar"})})
    @LogAnnotation(module = "文章", operation = "根据id获取文章，添加阅读数")
    public Result getArticleAndAddViews(@PathVariable("id") Integer id) {

        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        Article article = articleService.getArticleAndAddViews(id);

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(article);
        return r;
    }

    @GetMapping("/tag/{id}")
    @FastJsonView(
            exclude = {
                    @FastJsonFilter(clazz = Article.class, props = {"body", "category", "comments"}),
                    @FastJsonFilter(clazz = Tag.class, props = {"id", "avatar"})},
            include = {@FastJsonFilter(clazz = User.class, props = {"nickname"})})
    @LogAnnotation(module = "文章", operation = "根据标签获取文章")
    public Result listArticlesByTag(@PathVariable Integer id) {
        List<Article> articles = articleService.listArticlesByTag(id);
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

    @PostMapping("/update")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "文章", operation = "修改文章")
    public Result updateArticle(@RequestBody Article article) {
        Result r = new Result();

        if (null == article.getId()) {
            r.setResultCode(ResultCode.USER_NOT_EXIST);
            return r;
        }

        Integer articleId = articleService.updateArticle(article);

        r.setResultCode(ResultCode.SUCCESS);
        r.simple().put("articleId", articleId);
        return r;
    }

    @GetMapping("/delete/{id}")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "文章", operation = "删除文章")
    public Result deleteArticleById(@PathVariable("id") Integer id) {
        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        articleService.deleteArticleById(id);

        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }

    @GetMapping("/listArchives")
    @LogAnnotation(module = "文章", operation = "获取文章归档日期")
    public Result listArchives() {
        return Result.success(articleService.listArchives());
    }
}
