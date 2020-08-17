package com.restful.api.entity;

import lombok.Getter;

@Getter
public enum UserStatus {
    normal("状态正常"), blocked("封禁状态");

    private final String info;

    UserStatus(String info) {
        this.info = info;
    }
}
