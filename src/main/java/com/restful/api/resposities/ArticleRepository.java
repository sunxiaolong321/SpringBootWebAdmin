package com.restful.api.resposities;

import com.restful.api.entities.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Query(value = "select distinct * from article where article_title=:createdTime",nativeQuery = true)
    Iterable<Article> findByCreatedTime(long createdTime);
    @Query(value = "select distinct * from article where article_title=:title limit=1",nativeQuery = true)
    Article findArticleByTitle(String title);
    @Query(value = "delete from article where article_title=:title",nativeQuery = true)
    void deleteArticleByTitle(String title);

}
