package com.anvna.blog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Tags
 * @Description 标签实体类
 * @Author an_vna
 * @Date 2020/10/19 21:53
 * @Version V1.0
 **/
@Table()
@Entity(name = "t_tag")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
