package com.restful.api.respository;

import com.restful.api.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByAccount(String account);
}
