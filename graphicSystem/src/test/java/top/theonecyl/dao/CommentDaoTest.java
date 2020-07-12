package top.theonecyl.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.theonecyl.graphicSystem.dao.ICommentDao;
import top.theonecyl.graphicSystem.entity.Article;
import top.theonecyl.graphicSystem.entity.Comment;
import top.theonecyl.graphicSystem.entity.User;

import java.util.Date;
import java.util.List;

//Junit和Spring整合，JUnit会加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class CommentDaoTest {

    @Autowired
    private ICommentDao commentDao;

    @Test
    public void TestQueryCommentTest(){
        List<Comment> comments = commentDao.queryCommentList(new Comment(), 0, 10);
        for (Comment comment : comments){
            System.out.println(comment);
        }
    }

    @Test
    public void TestGetTotalCommentNum(){
        System.out.println(commentDao.getTotalCommentNum(new Comment()));

    }

    @Test
    public void TestAddComment(){
        Comment comment = new Comment();
        Article article = new Article();
        article.setArticleId(1);
        comment.setArticle(article);
        User user = new User();
        user.setUserId(1);
        comment.setUserName("JackZhang");
        comment.setUserImgAddress("test01");
        comment.setUser(user);
        comment.setCommentContent("这是一条测试评论test01");
        comment.setCommentNum(1);
//        comment.setCommentId(1);
        comment.setCreateTime(new Date());
        commentDao.addComment(comment);
    }

    @Test
    public void TestDelComment(){
        System.out.println(commentDao.delComment(3));
    }
}
