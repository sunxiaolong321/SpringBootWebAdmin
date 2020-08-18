package com.restful.api.service.impl;

import com.restful.api.common.util.PasswordHelper;
import com.restful.api.entity.User;
import com.restful.api.respository.UserRepository;
import com.restful.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseGet(User::new);
    }

    @Override
    @Transactional
    public Long saveUser(User user) {
        PasswordHelper.encryptPassword(user);
        int index = new Random().nextInt(6) + 1;
        String avatar = "/static/user/user_" + index + ".png";
        user.setAvatar(avatar);
        return userRepository.save(user).getId();
    }

    @Override
    public Long updateUser(User user) {
        Optional<User> oldUser = userRepository.findById(user.getId());
        if (oldUser.isPresent()) {
            oldUser.get().setNickname(user.getNickname());
            return oldUser.get().getId();
        }
        return (long) -1;
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
