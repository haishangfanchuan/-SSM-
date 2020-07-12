package top.theonecyl.graphicSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import top.theonecyl.graphicSystem.dao.IArticleDao;
import top.theonecyl.graphicSystem.dto.ArticleExecution;
import top.theonecyl.graphicSystem.entity.Article;
import top.theonecyl.graphicSystem.enums.ArticleStateEnum;
import top.theonecyl.graphicSystem.service.IArticleService;
import top.theonecyl.graphicSystem.utils.ImageHolder;
import top.theonecyl.graphicSystem.utils.ImageUtil;
import top.theonecyl.graphicSystem.utils.PathUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

@Service("articleService")
@EnableTransactionManagement
public class ArticleServiceImpl implements IArticleService {

    private final static int pageCount = 8;
    @Autowired
    private   IArticleDao articleDao;


    @Override
    @Transactional
    public ArticleExecution queryArticleList(Article article,int rowIndex) {
        if(article!=null){
            try{
                int startArticle = (rowIndex-1)*pageCount;
                List<Article> articles = articleDao.queryArticleList(article, startArticle, pageCount);
                if(articles.size()>0){
                    //查询文章成功
                    return new ArticleExecution(ArticleStateEnum.SUCCESS,articles);
                }else{
                    return new ArticleExecution(ArticleStateEnum.ERROR);
                }
            }catch (Exception e){
                throw new RuntimeException("QueryArticleList Error:" + e.getMessage());
            }
        }
        return new ArticleExecution(ArticleStateEnum.NOT_ARTICLE);
    }

    @Override
    public int queryArticleCount(Article article) {
        return articleDao.findAllArticle(article);
    }

    @Override
    public Article queryArticleById(int articleId) {
        return articleDao.findArticleById(articleId);
    }

    @Override
    @Transactional
    public ArticleExecution addArticle(Article article) throws FileNotFoundException {
        if(article!=null){
            //创建时间和修改时间
            article.setCreateTime(new Date());
            article.setLastTime(new Date());
            try{
                int effectNum1 = articleDao.addArticle(article);
                if(effectNum1>0) {
                    //判断是否有文章封面图片地址
                    if (article.getCoverImgAddress() != null) {
                        //给文章封面图添加水印
                        File file = new File(article.getCoverImgAddress());
                        ImageHolder imageHolder = new ImageHolder(file.getName(), new FileInputStream(file));
                        article.setCoverImgAddress(ImageUtil.generateNormalImg(imageHolder, PathUtil.getArticleImagePath(article.getArticleId())));
//                        System.out.println(article.getCoverImgAddress());
                    }
//                        System.out.println(article);
                    }else{
                    return new ArticleExecution(ArticleStateEnum.ERROR);
                }
                int effectNum = articleDao.updateArticle(article);
                if (effectNum <= 0) {
                    //添加文章失败
                    return new ArticleExecution(ArticleStateEnum.ERROR);
                } else {
                    return new ArticleExecution(ArticleStateEnum.SUCCESS,article);
                }
            }catch (Exception e){
                throw new RuntimeException("AddArticle Error:"+e.getMessage());
            }
        }
        return new ArticleExecution(ArticleStateEnum.NOT_ARTICLE);
    }

    @Override
    public ArticleExecution delArticle(int articleId) {
        int effectNum = articleDao.delArticle(articleId);
        if(effectNum<=0){
            return new ArticleExecution(ArticleStateEnum.ERROR);
        }
        return new ArticleExecution(ArticleStateEnum.SUCCESS);
    }

    @Override
    @Transactional
    public ArticleExecution updateArticle(Article article, ImageHolder imageHolder) {
        System.out.println(article);
        if(article!=null){
            article.setLastTime(new Date());
            //更新封面图
            if(imageHolder!=null){
                if(article.getCoverImgAddress()!=null){
                    //删除原封面图
//                    ImageUtil.deleteFileOrPath(PathUtil.getArticleImagePath(article.getArticleId()));
//                    System.out.println("要删除图片路径--->"+article.getCoverImgAddress());
                    ImageUtil.deleteFileOrPath(article.getCoverImgAddress());

                }
//                System.out.println("要删除图片路径--->"+article.getCoverImgAddress());
//                System.out.println("要删除图片路径--->"+PathUtil.getArticleImagePath(article.getArticleId()));
                article.setCoverImgAddress(ImageUtil.generateNormalImg(imageHolder,PathUtil.getArticleImagePath(article.getArticleId())));
//                System.out.println("last-->"+article.getCoverImgAddress());
            }
            try{
                int effectNum= articleDao.updateArticle(article);
//                System.out.println(article);
                if(effectNum<=0){
                    //更新文章失败
                    return new ArticleExecution(ArticleStateEnum.ERROR);
                }else{
                    return new ArticleExecution(ArticleStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw new RuntimeException("UpdateArticle Error:"+e.getMessage());
            }

        }
        return new ArticleExecution(ArticleStateEnum.NOT_ARTICLE);
    }


}
