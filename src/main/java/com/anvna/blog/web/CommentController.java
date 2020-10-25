package com.anvna.blog.web;

import com.anvna.blog.po.Comment;
import com.anvna.blog.po.User;
import com.anvna.blog.service.BlogService;
import com.anvna.blog.service.CommentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CommentControl
 * @Description
 * @Author an_vna
 * @Date 2020/10/25 20:10
 * @Version V1.0
 **/
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    /**
     * 获取博客对应评论
     * @param blogId
     * @param model
     * @return
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        if( user != null ){
            comment.setAvatar(user.getAvatar());
            comment.setAdmin(true);
            // comment.setNickname(user.getNickname());
        }else{
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

}
