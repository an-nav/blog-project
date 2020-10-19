package com.anvna.blog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Type
 * @Description 分类实体类
 * @Author an_vna
 * @Date 2020/10/19 21:49
 * @Version V1.0
 **/
@Entity(name = "t_type")
@Table()
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    private String typeName;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();


    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }


    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
