package com.restful.api.service;

import com.restful.api.entity.Comment;

public interface CommentService {
    Iterable<Comment> findAll();

    Comment getCommentById(Integer id);

    Integer saveComment(Comment comment);

    void deleteCommentById(Integer id);

    public Iterable<Comment> listCommentsByArticle(Iterable<Integer> id);

    Comment saveCommentAndChangeCounts(Comment comment);

    void deleteCommentByIdAndChangeCounts(Integer id);
}
