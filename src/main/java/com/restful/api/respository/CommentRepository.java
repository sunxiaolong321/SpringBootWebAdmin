package com.restful.api.respository;

import com.restful.api.entity.Article;
import com.restful.api.entity.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> findByArticleAndLevelOrderByCreateDateDesc(Article a, String level);
}
