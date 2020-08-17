package com.restful.api.common.util;

import com.restful.api.common.constant.Base;
import com.restful.api.entity.User;
import org.apache.shiro.SecurityUtils;

public class UserUtils {
    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getSession().getAttribute(Base.CURRENT_USER);
    }
}
