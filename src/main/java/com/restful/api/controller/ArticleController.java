package com.restful.api.controller;

import com.restful.api.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class ArticleController {
    private ArticleServe articleServe;

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
}
