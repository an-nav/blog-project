package com.anvna.blog.web;

import com.anvna.blog.po.Blog;
import com.anvna.blog.service.BlogService;
import com.anvna.blog.service.TagService;
import com.anvna.blog.service.TypeService;
import com.anvna.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName IndexController
 * @Description
 * @Author an_vna
 * @Date 2020/10/18 21:04
 * @Version V1.0
 **/
@Controller
@RequestMapping("/")
public class IndexController {
    private static final int TYPE_NUMBER = 6;
    private static final int TAG_NUMBER = 10;
    private static final int RECOMMEND_BLOG_NUMBER = 8;
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;



    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listType(TYPE_NUMBER));
        model.addAttribute("tags", tagService.listTag(TAG_NUMBER));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlog(RECOMMEND_BLOG_NUMBER));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model){

        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/footer/newBlogs")
    public String newBlogs(Model model){
        model.addAttribute("newBlogs", blogService.listRecommendBlog(3));
        return "_fragment :: newBlogList";
    }

}






