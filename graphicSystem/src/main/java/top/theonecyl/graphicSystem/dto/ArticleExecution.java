package top.theonecyl.graphicSystem.dto;

import top.theonecyl.graphicSystem.entity.Article;
import top.theonecyl.graphicSystem.enums.ArticleStateEnum;

import java.util.List;

public class ArticleExecution {
    //返回类型吗
    private int state;
    //返回值说明
    private String stateInfo;
    //文章总数
    private int articleCount;
    //文章类
    private Article article;
    //文章列表
    private List<Article> articleList;

    public ArticleExecution() {}

    public ArticleExecution(ArticleStateEnum articleStateEnum) {
        state = articleStateEnum.getState();
        stateInfo = articleStateEnum.getStateInfo();

    }
    public ArticleExecution(ArticleStateEnum articleStateEnum, List<Article> articleList){
        state = articleStateEnum.getState();
        stateInfo = articleStateEnum.getStateInfo();
        this.articleList = articleList;
    }
    public ArticleExecution(ArticleStateEnum articleStateEnum, Article article){
        state = articleStateEnum.getState();
        stateInfo = articleStateEnum.getStateInfo();

        this.article = article;

    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "ArticleExecution{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", articleCount=" + articleCount +
                ", article=" + article +
                ", articleList=" + articleList +
                '}';
    }
}
