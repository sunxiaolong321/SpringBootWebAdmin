package com.restful.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.restful.api.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "me_article")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Article extends BaseEntity<Integer> {
    public static final int Article_TOP = 1;
    public static final int Article_Common = 0;

    private static final long serialVersionUID = -4470366380115322213L;

    @Column(name = "title", length = 40, nullable = false)
    private String title;
    @Column(name = "summary", length = 100, nullable = false)
    private String summary;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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
    @CreationTimestamp
    @Column(updatable = false)
    private Date createDate;

    @UpdateTimestamp
    private Date modifiedDate;

}
