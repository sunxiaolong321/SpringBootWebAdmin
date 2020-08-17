package com.restful.api.respository.impl;

import com.restful.api.respository.wapper.TagWrapper;
import com.restful.api.vo.ArticleVo;
import com.restful.api.vo.TagVO;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TagWrapperImpl implements TagWrapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TagVO> findAllDetail() {
        String sql = "select t.*,count(at.tag_id ) as articles from me_article_tag at "
                + "right join me_tag t on t.id = at.tag_id group by t.id ";

        NativeQuery query = getSession().createSQLQuery(sql);
        query.addScalar("id");
        query.addScalar("tagName");
        query.addScalar("avatar");
        query.addScalar("articles", IntegerType.INSTANCE);

        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(ArticleVo.class));
        return query.getResultList();
    }

    @Override
    public TagVO getTagDetail(Integer tagId) {
        String sql = "select t.*,count(at.tag_id ) as articles from me_article_tag at "
                + "right join me_tag t on t.id = at.tag_id where t.id = :tagId ";

        NativeQuery query = getSession().createSQLQuery(sql);

        query.setParameter("tagId", tagId);
        query.addScalar("id");
        query.addScalar("tagName");
        query.addScalar("avatar");
        query.addScalar("articles", IntegerType.INSTANCE);

        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(ArticleVo.class));
        return (TagVO) query.uniqueResult();
    }

    private Session getSession() {
        return em.unwrap(Session.class);
    }
}