package com.restful.api.vo;

import com.restful.api.entity.Article;
import lombok.Data;

@Data
public class ArticleVo extends Article {
    private Integer year;
    private Integer month;
    private Integer tagId;
    private Integer categoryId;
    private Integer count;
}
