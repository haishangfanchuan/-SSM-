package top.theonecyl.sercice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.theonecyl.BaseTest;
import top.theonecyl.graphicSystem.dto.CommentExecution;
import top.theonecyl.graphicSystem.entity.Article;
import top.theonecyl.graphicSystem.entity.Comment;
import top.theonecyl.graphicSystem.entity.User;
import top.theonecyl.graphicSystem.service.ICommentService;
import top.theonecyl.graphicSystem.service.IUserService;

import java.util.Date;

public class CommentServiceTest extends BaseTest {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;

    @Test
    public void testAddComment(){
        Comment comment = new Comment();
        User user = userService.queryUserById(1);
        comment.setUserImgAddress(user.getUserImgAddress());
//        comment.setCommentNum(1);
        comment.setUserName(user.getUsername());
        comment.setUserId(user.getUserId());
        comment.setUser(user);
        Article article = new Article();
        article.setArticleId(1);
        comment.setArticle(article);
        comment.setArticleId(1);
        comment.setCommentContent("testAddComment");
        CommentExecution commentExecution = commentService.addComment(comment);
        System.out.println(commentExecution);
    }
    @Test
    public void testDelComment(){
        System.out.println(commentService.delComment(9));
    }
    @Test
    public void testQueryCommentList(){
        CommentExecution commentExecution = commentService.queryCommentList(new Comment(), 1);
        System.out.println(commentExecution.getCommentList().size());
    }
    @Test
    public void testQueryCommentCount(){
        System.out.println(commentService.queryCommentCount(new Comment()));
    }

}
