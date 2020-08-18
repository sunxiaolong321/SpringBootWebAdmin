package com.restful.api.service;

import com.restful.api.entity.User;

public interface UserService {
    Iterable<User> findAll();

    User getUserByAccount(String account);

    User getUserById(Long id);

    Long saveUser(User user);

    Long updateUser(User user);

    void deleteUserById(Long id);
}
