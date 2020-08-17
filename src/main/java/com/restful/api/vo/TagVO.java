package com.restful.api.vo;

import com.restful.api.entity.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagVO extends Tag {
    private static final long serialVersionUID = 5059212992497947120L;
    private int articles;
}
