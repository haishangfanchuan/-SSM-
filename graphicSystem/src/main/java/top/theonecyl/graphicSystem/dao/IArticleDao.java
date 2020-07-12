package top.theonecyl.graphicSystem.dao;

import org.apache.ibatis.annotations.Param;
import top.theonecyl.graphicSystem.entity.Article;

import java.util.List;

public interface IArticleDao {

    
    /**
     * 文章分页查询
     * @param startArticle    起始文章
     * @param pageCount       每一页文章数
     *
     * @return
     */
    List<Article> queryArticleList(@Param("articleCondition") Article articleCondition
            ,@Param("startArticle")int startArticle
            , @Param("pageCount")int pageCount);


    /**
     * 获取文章总数
     * @return
     */
    int findAllArticle(@Param("articleCondition") Article articleCondition);

    /**
     * 根据文章id查询文章
     * @param articleId
     * @return
     */
    Article findArticleById(@Param("articleId")int articleId);

    /**
     * 添加文章
     * @param article
     * @return
     */
    int addArticle(Article article);

    /**
     * 根据文章id删除文章
     * @param articleId
     * @return
     */
    int delArticle(@Param("articleId")int articleId);

    /**
     * 更新文章
     * @param article
     * @return
     */
    int updateArticle(Article article);
}
