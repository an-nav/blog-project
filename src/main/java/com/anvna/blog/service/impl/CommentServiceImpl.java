package com.anvna.blog.service.impl;

import com.anvna.blog.dao.CommentRepository;
import com.anvna.blog.po.Comment;
import com.anvna.blog.service.CommentService;
import com.sun.crypto.provider.DESCipher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Description
 * @Author an_vna
 * @Date 2020/10/25 20:16
 * @Version V1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;



    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentNull(blogId, sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentId = comment.getParent().getId();
        if( parentId != -1 ){
            // 建立父子级关系
            comment.setParent(commentRepository.getOne(parentId));
        }else{
            comment.setParent(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }


    /**
     * get collections of all child nodes
     */
    private List<Comment> tempReplies = new ArrayList<>();

    /**
     * iterate through each parent comment
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        // combine all child comments to parent comment group
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replies = comment.getReplyComment();
            for(Comment reply : replies) {
                // find and save child comments in replies
                recursively(reply);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComment(tempReplies);
            //清除临时存放区
            tempReplies = new ArrayList<>();
        }
    }


    /**
     * 递归所有子节点
     * @param comment
     * @return
     */
    private void recursively(Comment comment) {
        //tempReplies is a collection of all parent nodes
        tempReplies.add(comment);
        if (comment.getReplyComment().size() > 0) {
            List<Comment> replies = comment.getReplyComment();
            for (Comment reply : replies) {
                recursively(reply);
            }
        }
    }
}
