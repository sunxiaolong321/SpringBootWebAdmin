package com.restful.api.service.impl;

import com.restful.api.common.util.UserUtils;
import com.restful.api.entity.Article;
import com.restful.api.entity.Comment;
import com.restful.api.respository.CommentRepository;
import com.restful.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElseGet(Comment::new);
    }

    @Override
    @Transactional
    public Integer saveComment(Comment comment) {
        return commentRepository.save(comment).getId();
    }

    @Override
    @Transactional
    public void deleteCommentById(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> listCommentsByArticle(Iterable<Integer> id) {
        return commentRepository.findAllById(id);
    }

    @Override
    @Transactional
    public Comment saveCommentAndChangeCounts(Comment comment) {
        Optional<Comment> NewComment = commentRepository.findById(comment.getId());


        comment.setAuthor(UserUtils.getCurrentUser());
        if (null == comment.getParent()) {
            comment.setLevel("0");
        } else {
            if (null == comment.getToUser()) {
                comment.setLevel("1");
            } else {
                comment.setLevel("2");
            }
        }
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteCommentByIdAndChangeCounts(Integer id) {
        Optional<Comment> c = commentRepository.findById(id);
        if (c.isPresent()) {
            Article a = c.get().getArticle();
            a.setCommentCounts(a.getViewCounts() - 1);
            commentRepository.deleteById(id);
        }
    }
}
