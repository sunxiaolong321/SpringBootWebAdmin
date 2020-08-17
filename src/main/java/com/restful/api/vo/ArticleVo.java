package com.restful.api.vo;

import com.restful.api.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleVo extends Article {
    private Integer year;
    private Integer month;
    private Integer tagId;
    private Integer categoryId;
    private Integer count;
}
