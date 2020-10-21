package com.anvna.blog.dao;

import com.anvna.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    // 通过标签名查询标签
    Tag findByName(String name);
}
