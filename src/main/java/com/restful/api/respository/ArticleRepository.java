package com.restful.api.respository;

import com.restful.api.entity.Article;
import com.restful.api.entity.Category;
import com.restful.api.entity.Tag;
import com.restful.api.vo.PageVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
    @Query(value = "select * from me_article ma limit  :#{#page.pageSize}*(:#{#page.pageNumber}-1) :#{#page.pageSize}*:#{#page.pageNumber} order by id :#{#page.sort}", nativeQuery = true)
    List<Article> listArticles(@Param("page") PageVo page);

    @Query(value = "select ma.* from me_article_tag mat left join me_article ma on ma.id=mat.id left join me_tag mt on mt.id= mat.id where tag.id=:#{#tag.id}", nativeQuery = true)
    List<Article> findByTags(@Param("tag") Tag tag);

    List<Article> findByCategory(@Param("category") Category category);

    @Query(value = "select * from me_article order by view_counts desc limit :limit", nativeQuery = true)
    List<Article> findOrderByViewsAndLimit(@Param("limit") int limit);

    @Query(value = "select * from me_article order by create_date desc limit :limit", nativeQuery = true)
    Iterable<Article> findOrderByCreateDateAndLimit(@Param("limit") int limit);
}
