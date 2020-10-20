package com.anvna.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName BlogInputController
 * @Description
 * @Author an_vna
 * @Date 2020/10/20 20:38
 * @Version V1.0
 **/
@Controller
@RequestMapping("/admin")
public class BlogInputController {

    @GetMapping("/blogs-input")
    public String blogs(){
        return "admin/blogs-input";
    }
    @GetMapping("/blogs")
    public String admin(){
        return "admin/blogs";
    }
}
