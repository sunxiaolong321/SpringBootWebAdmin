package com.restful.api.vo;

import lombok.Data;

@Data
public class PageVo {
    private Integer pageNumber;
    private Integer pageSize;
    private String sort;
}
