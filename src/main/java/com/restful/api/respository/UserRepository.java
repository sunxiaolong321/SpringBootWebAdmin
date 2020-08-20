package com.restful.api.respository;

import com.restful.api.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "select * from sys_user where sys_user.account=:account", nativeQuery = true)
    User findByAccount(@Param("account") String account);
}
