package com.restful.api.service.impl;

import com.restful.api.entity.Article;
import com.restful.api.entity.Category;
import com.restful.api.entity.Tag;
import com.restful.api.respository.ArticleRepository;
import com.restful.api.service.ArticleService;
import com.restful.api.vo.ArticleVo;
import com.restful.api.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> listArticles(PageVo page) {

        return articleRepository.listArticles(page);
    }

    @Override
    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Integer id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.orElseGet(Article::new);
    }

    @Override
    public Integer publishArticle(Article article) {
        return null != article.getId() ? this.updateArticle(article) : this.saveArticle(article);
    }

    @Override
    @Transactional
    public Integer saveArticle(Article article) {

//        User currentUser = UserUtils.getCurrentUser();
//
//        if (null != currentUser) article.setAuthor(currentUser);
//        article.setWeight(Article.Article_Common);
        return articleRepository.save(article).getId();
    }

    @Override
    @Transactional
    public Integer updateArticle(Article article) {
        Optional<Article> oldArticle = articleRepository.findById(article.getId());
        if (oldArticle.isPresent()) {
            oldArticle.get().setTitle(article.getTitle());
            oldArticle.get().setSummary(article.getSummary());
            oldArticle.get().setBody(article.getBody());
            oldArticle.get().setCategory(article.getCategory());
            oldArticle.get().setTags(article.getTags());
            return oldArticle.get().getId();
        }
        return -1;
    }

    @Override
    @Transactional
    public void deleteArticleById(Integer id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> listArticlesByTag(Integer id) {
        Tag t = new Tag();
        t.setId(id);
        return articleRepository.findByTags(t);
    }

    @Override
    public List<Article> listArticlesByCategory(Integer id) {
        Category c = new Category();
        c.setId(id);
        return articleRepository.findByCategory(c);
    }

    @Override
    @Transactional
    public Article getArticleAndAddViews(Integer id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            article.get().setViewCounts(article.get().getViewCounts() + 1);
            return article.get();
        }
        return new Article();
    }

    @Override
    public List<Article> listHotArticles(int limit) {
        return articleRepository.findOrderByViewsAndLimit(limit);
    }

    @Override
    public List<Article> listNewArticles(int limit) {
        return articleRepository.findOrderByCreateDateAndLimit(limit);
    }

    @Override
    public List<ArticleVo> listArchives() {
        return null;
    }
}
