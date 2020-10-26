package com.anvna.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName AboutShowController
 * @Description
 * @Author an_vna
 * @Date 2020/10/26 21:32
 * @Version V1.0
 **/
@Controller
public class AboutShowController {

    @GetMapping("/aboutme")
    public String about(){
        return "aboutme";
    }

}
