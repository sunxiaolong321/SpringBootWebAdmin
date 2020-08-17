package com.restful.api.entity;

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
public class Category extends BaseEntity<Integer> {
    private static final long serialVersionUID = 5025313969040054182L;

    @Column(nullable = false)
    private String categoryName;

    private String description;

    @Column(nullable = false)
    private String avatar;
}
