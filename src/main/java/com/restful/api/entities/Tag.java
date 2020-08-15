package com.restful.api.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    private Long id;
    private String name;
    private Set<Article> link;
    private Integer count=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "tag")
    public Set<Article> getLink() {
        return link;
    }

    public void setLink(Set<Article> link) {
        this.link = link;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, length = 10)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
