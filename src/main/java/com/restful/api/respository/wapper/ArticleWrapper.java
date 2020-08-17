package com.restful.api.respository.wapper;

import com.restful.api.entity.Article;
import com.restful.api.vo.ArticleVo;
import com.restful.api.vo.PageVo;

import java.util.List;

public interface ArticleWrapper {
    List<Article> listArticles(PageVo page);

    List<Article> listArticles(ArticleVo article, PageVo page);

    List<ArticleVo> listArchives();
}
