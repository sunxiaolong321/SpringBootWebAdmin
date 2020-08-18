package com.restful.api.service.impl;

import com.restful.api.entity.Log;
import com.restful.api.respository.LogRepository;
import com.restful.api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    private LogRepository logRepository;

    @Autowired
    public LogRepository getLogRepository() {
        return logRepository;
    }

    @Override
    public Integer saveLog(Log log) {
        return logRepository.save(log).getId();
    }
}
