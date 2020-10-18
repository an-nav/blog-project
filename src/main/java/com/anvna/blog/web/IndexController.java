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
    @GetMapping("/{id}/{name}")
    public String index(@PathVariable String id, @PathVariable String name){
        // String blog = null;
        // if( null == blog ){
        //     throw new BlogNotFoundException("博客不存在!");
        // }
        System.out.println("------------------index------------------");
        return "index";
    }
}
