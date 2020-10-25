package com.anvna.blog.web;

import com.anvna.blog.po.Type;
import com.anvna.blog.service.BlogService;
import com.anvna.blog.service.TypeService;
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
 * @ClassName TypeShowController
 * @Description
 * @Author an_vna
 * @Date 2020/10/25 22:49
 * @Version V1.0
 **/
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){
        // 用大数来查询全表
        List<Type> types = typeService.listType(10000);
        if( id == -1 ){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        // 上方分类查询
        model.addAttribute("types", types);
        // 博客列表展示
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
