package com.anvna.blog.po;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "分类不能为空")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", typeName='" + name + '\'' +
                '}';
    }
}
