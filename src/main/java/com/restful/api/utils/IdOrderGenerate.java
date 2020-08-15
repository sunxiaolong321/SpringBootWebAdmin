package com.restful.api.utils;

import com.restful.api.resposities.ArticleRepository;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 实现主键 id
 * 时间+序号
 */
@Component
public class IdOrderGenerate extends IdentityGenerator{
    public static IdOrderGenerate idOrderGenerate;
    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PostConstruct
    public void init() {
        idOrderGenerate = this;
        idOrderGenerate.articleRepository = this.articleRepository;
    }
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws MappingException {
        return getSerializable(articleRepository);
    }

    public static Serializable getSerializable(ArticleRepository articleRepository) {
        LocalDate date = LocalDate.now();
        long localTime = Long.parseLong(date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        long order = 1;
        while (true) {
            long returnValue = localTime * 100 + order;
            if (!idOrderGenerate.articleRepository.existsById(returnValue)) {
                return returnValue;
            }
            order++;
        }
    }
}