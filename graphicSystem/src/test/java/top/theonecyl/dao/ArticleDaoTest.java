package top.theonecyl.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.theonecyl.graphicSystem.dao.IArticleDao;
import top.theonecyl.graphicSystem.entity.Article;
import top.theonecyl.graphicSystem.entity.User;

import java.util.Date;
import java.util.List;

//Junit和Spring整合，Junit会加载SpringIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//spring配置文件所在路径
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ArticleDaoTest {
    @Autowired
    private IArticleDao articleDao;

    @Test
    public void TestQueryArticleList(){
        Article article = new Article();
        User user = new User();
        article.setUser(user);
        user.setUserId(1);
        System.out.println(articleDao.queryArticleList(article,0,1));
    }
    @Test
    public void TestFindAllArticle(){
        Article article = new Article();
        article.setArticleId(1);
        int allArticle = articleDao.findAllArticle(article);
        System.out.println(allArticle);
    }

    @Test
    public void TestFindArticleById(){
        Article articleById = articleDao.findArticleById(3);
        System.out.println(articleById);
    }

    @Test
    public void TestAddArticle(){
        Article article = new Article();
        article.setHeadline("Hello");
        article.setContent("你好");
        article.setCoverImgAddress("test");
        article.setUserId(1);
        article.setUsername("test01");
        article.setLastTime(new Date());
        article.setCreateTime(new Date());
        articleDao.addArticle(article);
    }

    @Test
    public void TestDelArticle(){
        articleDao.delArticle(2);
    }

    @Test
    public void TestUpdateArticle(){
        Article article = new Article();
        article.setArticleId(1);
        article.setLastTime(new Date());
        articleDao.updateArticle(article);
    }
}
