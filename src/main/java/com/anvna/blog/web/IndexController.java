package com.anvna.blog.web;

import com.anvna.blog.BlogNotFoundException;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName IndexController
 * @Description
 * @Author an_vna
 * @Date 2020/10/18 21:04
 * @Version V1.0
 **/
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){

        return "index";
    }
    @GetMapping("/blog")
    public String blog(){

        return "blog";
    }

}
