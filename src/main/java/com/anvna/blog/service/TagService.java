package com.anvna.blog.service;

import com.anvna.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    /**
     * 新增分类
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * 查询 tupe
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * 更新 tag
     * @param id 主键 id
     * @param tag
     * @return
     */
    Tag updateTag(Long id, Tag tag);

    /**
     * 删除 tag
     * @param id
     */
    void deleteTag(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();

    List<Tag> listTag(String ids);
}
