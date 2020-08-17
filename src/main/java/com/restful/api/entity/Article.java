package com.restful.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.restful.api.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 文章类
 * 2020年8月16日
 */
@Getter
@Setter
@Entity
@Table(name = "me_article")
public class Article extends BaseEntity<Integer> {
    public static final int Article_TOP = 1;
    public static final int Article_Common = 0;

    private static final long serialVersionUID = -4470366380115322213L;

    @Column(name = "title", length = 40, nullable = false)
    private String title;
    @Column(name = "summary", length = 100, nullable = false)
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "body_id")
    private ArticleBody body;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "me_article_tag",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", orphanRemoval = true)
    private List<Comment> comments;

    private int commentCounts;

    private int viewCounts;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy.MM.dd HH:mm")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @JSONField(format = "yyyy.MM.dd HH:mm")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

}
