package com.restful.api.respository;

import com.restful.api.entity.Comment;
import com.restful.api.respository.wapper.CategoryWrapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer>, CategoryWrapper {

}
