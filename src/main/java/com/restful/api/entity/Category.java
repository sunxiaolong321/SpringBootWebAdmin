package com.restful.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.restful.api.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "me_category")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Category extends BaseEntity<Integer> {
    private static final long serialVersionUID = 5025313969040054182L;

    @Column(nullable = false)
    private String name;

    @JSONField
    private String description;

    private Integer amount = 0;
}
