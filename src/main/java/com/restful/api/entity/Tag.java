package com.restful.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.restful.api.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "me_tag")
public class Tag extends BaseEntity<Integer> {

    private static final long serialVersionUID = 5025313969040054182L;

    @Column(nullable = false)
    private String tagName;

    @Column(nullable = false)
    private String avatar;

}
