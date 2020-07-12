package top.theonecyl.graphicSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import top.theonecyl.graphicSystem.dao.ICommentDao;
import top.theonecyl.graphicSystem.dto.CommentExecution;
import top.theonecyl.graphicSystem.entity.Comment;
import top.theonecyl.graphicSystem.enums.CommentStateEnum;
import top.theonecyl.graphicSystem.service.ICommentService;

import java.util.Date;
import java.util.List;

@Service("commentServiceImpl")
@EnableTransactionManagement
public class CommentServiceImpl implements ICommentService {

    private final static  int pageCount = 8;

    @Autowired
    private ICommentDao commentDao;




    @Override
    @Transactional
    public CommentExecution addComment(Comment comment) {
        if(comment!=null){
            try {
                comment.setCreateTime(new Date());
                comment.setCommentNum(this.queryCommentCount(comment)+1);
                int effectNum = commentDao.addComment(comment);
                if (effectNum <= 0) {
                    //添加失败
                    return new CommentExecution(CommentStateEnum.ERROR);
                } else {
                    return new CommentExecution(CommentStateEnum.SUCCESS,comment);
                }
            }catch (Exception e){
                throw new RuntimeException("AddComment Error:"+e.getMessage());
            }
        }
        return new CommentExecution(CommentStateEnum.NOT_COMMENT);
    }

    @Override
    public int delComment(int commentId) {
        return commentDao.delComment(commentId);
    }

    @Override
    @Transactional
    public CommentExecution queryCommentList(Comment comment, int rowIndex) {
        if(comment!=null & rowIndex>=0){
            comment.setCreateTime(new Date());
            try {
                List<Comment> comments = commentDao.queryCommentList(comment, (rowIndex - 1) * pageCount, pageCount);
                if (comments.size() >= 0) {
                    //查询成功
                    return new CommentExecution(CommentStateEnum.SUCCESS,comments);
                } else {
                    return new CommentExecution(CommentStateEnum.ERROR);
                }
            }catch (Exception e){
                throw new RuntimeException("QueryCommentList Error:"+e.getMessage());
            }
        }
        return  new CommentExecution(CommentStateEnum.NOT_COMMENT);
    }

    @Override
    public int queryCommentCount(Comment comment) {
        return commentDao.getTotalCommentNum(comment);
    }
}
