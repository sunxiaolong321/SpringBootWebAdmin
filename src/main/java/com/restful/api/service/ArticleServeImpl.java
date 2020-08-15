package com.restful.api.service;

import com.restful.api.entities.Article;
import com.restful.api.resposities.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ArticleServeImpl implements ArticleServe {

    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Iterable<Article> getAllArticle(){
        return articleRepository.findAll();
    }

    @Override
    public Iterable<Article> getListByCreatedTime(long createdTime) {
        return articleRepository.findByCreatedTime(createdTime);
    }

    @Override
    public Article findArticleByTitle(String title) {
        return articleRepository.findArticleByTitle(title);
    }

    @Override
    public String addNewArticle(Article article) {
        articleRepository.save(article);
        return "success";
    }

    @Override
    public String deleteArticleByTitle(String title) {
        articleRepository.deleteArticleByTitle(title);
        return "success";
    }

    @Override
    public String updateArticleByTitle(long id, String title) {
        Article article = articleRepository.findById(id).get();
        article.setTitle(title);
        articleRepository.save(article);
        return null;
    }
}
