package com.restful.api.respository;

import com.restful.api.entity.Article;
import com.restful.api.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository {

    List<Comment> findByArticleAndLevelOrderByCreateDateDesc(Article a, String level);
}
