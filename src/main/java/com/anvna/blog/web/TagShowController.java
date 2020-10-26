package com.anvna.blog.web;

import com.anvna.blog.po.Tag;
import com.anvna.blog.service.BlogService;
import com.anvna.blog.service.TagService;
import com.anvna.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @ClassName TagShowController
 * @Description
 * @Author an_vna
 * @Date 2020/10/25 22:49
 * @Version V1.0
 **/
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){
        // 用大数来查询全表
        List<Tag> tags = tagService.listTag(10000);
        if( id == -1 ){
            id = tags.get(0).getId();
        }
        // 上方标签查询
        model.addAttribute("tags", tags);
        // 博客列表展示
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
