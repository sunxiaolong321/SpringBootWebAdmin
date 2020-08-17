package com.restful.api.entity;

import com.restful.api.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "me_article_body")
public class ArticleBody extends BaseEntity<Long> {
    private static final long serialVersionUID = -234234234233434L;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    private String content;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    private String contentHtml;
}
