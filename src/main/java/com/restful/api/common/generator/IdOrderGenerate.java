package com.restful.api.common.generator;

import com.restful.api.respository.ArticleRepository;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class IdOrderGenerate extends IdentityGenerator {
    private static IdOrderGenerate idOrderGenerate;
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
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        int now = Integer.parseInt(dateFormat.format(date));
        int generateId = now * 100 + 1;
        while (true) {
            if (!idOrderGenerate.articleRepository.existsById(generateId)) {
                return generateId;
            }
            generateId++;
        }
    }
}
