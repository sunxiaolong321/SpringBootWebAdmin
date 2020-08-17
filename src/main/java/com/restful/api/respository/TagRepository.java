package com.restful.api.respository;

import com.restful.api.entity.Tag;
import com.restful.api.respository.wapper.TagWrapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer>, TagWrapper {
    @Query(value = "select t.*,count(at.tag_id) as count from me_article_tag at "
            + "right join me_tag t on t.id = at.tag_id "
            + "group by t.id order by count(at.tag_id) desc limit :limit", nativeQuery = true)
    List<Tag> listHotTagsByArticleUse(@Param("limit") int limit);
}
