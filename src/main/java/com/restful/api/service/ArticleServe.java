package com.restful.api.service;

import com.restful.api.entities.Article;

public interface ArticleServe {
    //通过创建时间查找
    Iterable<Article> getListByCreatedTime(long createdTime);
    //通过创建标题查找
    Article findArticleByTitle(String title);
    Iterable<Article> getAllArticle();
    String addNewArticle(Article article);
    String deleteArticleByTitle(String title);
    String updateArticleByTitle(long id, String title);
}
