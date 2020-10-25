package com.anvna.blog.service;

import com.anvna.blog.po.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @InterfaceName CommentService
 * @Description 评论
 * @Author an_vna
 * @Date 2020/10/25 20:15
 * @Version V1.0
 **/
@Service
public interface CommentService {

    /**
     * 获取博客全部评论
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlogId(Long blogId   );

    /**
     * 保存评论
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);
}
