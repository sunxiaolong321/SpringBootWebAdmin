package com.restful.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.restful.api.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_user")
public class User extends BaseEntity<Long> {
    private static final long serialVersionUID = -4454737765850239378L;

    @Column(name = "account", unique = true, length = 10)
    private String account;

    /**
     * 使用md5加密存储
     */
    @Column(name = "password", length = 64)
    private String password;

    private String avatar;

    @Column(name = "email", unique = true, length = 20)
    private String email;

    @Column(name = "nickname", length = 10)
    private String nickname;

    @Column(name = "mobile_phone_number", length = 20)
    private String mobilePhoneNumber;

    /**
     * 加密使用的种子
     */
    private String salt;

    @CreatedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @LastModifiedDate
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.normal;

    private Boolean admin = false;

    private Boolean deleted = Boolean.FALSE;
}
