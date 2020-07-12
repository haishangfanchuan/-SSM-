package top.theonecyl.sercice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.theonecyl.BaseTest;
import top.theonecyl.graphicSystem.dto.ArticleExecution;
import top.theonecyl.graphicSystem.entity.Article;
import top.theonecyl.graphicSystem.entity.User;
import top.theonecyl.graphicSystem.service.IArticleService;
import top.theonecyl.graphicSystem.utils.ImageHolder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ArticleServiceTest extends BaseTest {
    @Autowired
    private IArticleService articleService;

    @Test
    public void testAddArticle() throws FileNotFoundException {
        Article article = new Article();
        article.setCoverImgAddress("E:/test/11.jpg");
        article.setHeadline("testAddArticle");
        article.setContent("testAddArticle");
        User user = new User();
        user.setUserId(1);
        article.setUsername("JackZhang");
        article.setUserId(1);
        article.setUser(user);
        ArticleExecution articleExecution = articleService.addArticle(article);
        System.out.println(articleExecution);
    }

    @Test
    public void testQueryArticleById(){
        Article article = articleService.queryArticleById(6);
        System.out.println(article);
    }

    @Test
    public void testUpdateArticle() throws FileNotFoundException {
        Article article = articleService.queryArticleById(18);
        File file = new File("E:/test/8.jpg");
        ArticleExecution articleExecution = articleService.updateArticle(article, new ImageHolder(file.getName(), new FileInputStream(file)));
        System.out.println(articleExecution);
    }

    @Test
    public void testQueryImageList(){
        Article article = new Article();
        article.setHeadline("test");
        article.setContent("test");
        User user = new User();
        user.setUserId(1);
        article.setUserId(1);
        article.setUser(user);
        System.out.println(articleService.queryArticleList(article,1).getArticleList().size());

    }

    @Test
    public void testDelArticle(){
        for(int i =28;i<=29;i++){
            articleService.delArticle(i);
        }

    }
}
