package com.restful.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.restful.api.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author sxl
 * 2020年8月16日
 */

@Getter
@Setter
@Entity
@Table(name = "me_comment")
public class Comment extends BaseEntity<Integer> {
    private static final long serialVersionUID = 7346271954336613146L;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    /**
     * 类型 0 文章评论 1 评论的评论 2 评论的回复 3
     */
    @Column(length = 1)
    private String level;

    @JSONField(format = "yyyy.MM.dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @OneToMany
    @JoinColumn(name = "parent_id", nullable = true)
    private List<Comment> childrens;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Comment parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_uid")
    private User toUser;

}
