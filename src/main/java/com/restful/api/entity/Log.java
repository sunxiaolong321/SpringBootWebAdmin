package com.restful.api.entity;

import com.restful.api.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "sys_log")
public class Log extends BaseEntity<Integer> {
    private Long userId;
    private String nickname;
    private String module;
    private String operation;
    private String method;
    private String params;
    private Long time;
    private String ip;

    @CreatedDate
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Log() {
        super();
    }
}
