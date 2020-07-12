package top.theonecyl.graphicSystem.service;

import org.springframework.stereotype.Service;
import top.theonecyl.graphicSystem.dto.ArticleExecution;
import top.theonecyl.graphicSystem.entity.Article;
import top.theonecyl.graphicSystem.utils.ImageHolder;

import java.io.FileNotFoundException;

/**
 * 文章了服务层接口类
 */

public interface IArticleService {

    //查询文章列表
    ArticleExecution queryArticleList(Article article,int rowIndex);
    //查询文章总数
    int queryArticleCount(Article article);
    //根据文章id查询文章
    Article queryArticleById(int articleId);
    //添加文章
    ArticleExecution addArticle(Article article) throws FileNotFoundException;
    //删除文章
    ArticleExecution delArticle(int articleId);
    //更新文章
    ArticleExecution updateArticle(Article article,ImageHolder imageHolder);
}
