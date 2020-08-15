package com.restful.api.controller;

import com.restful.api.entities.Article;
import com.restful.api.service.ArticleServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/users")
public class ArticleController {
    private ArticleServe articleServe;
    public ArticleServe getArticleServe() {
        return articleServe;
    }
    @Autowired
    public void setArticleServe(ArticleServe articleServe) {
        this.articleServe = articleServe;
    }

    @RequestMapping(value="/post",method= RequestMethod.POST)
    public Article addNewArticle(@RequestBody Article article) {
        System.out.println(article);
        articleServe.addNewArticle(article);
        return article;
    }

    @RequestMapping(value="/get",method = RequestMethod.GET)
    public Iterable<Article> getAllArticle() {
        return articleServe.getAllArticle();
    }
}
