package com.restful.api.respository;

import com.restful.api.entity.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Integer> {
}
