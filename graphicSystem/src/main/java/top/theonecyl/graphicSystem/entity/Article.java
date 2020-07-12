package top.theonecyl.graphicSystem.entity;

import java.util.Date;

/**
 * 文章实体类
 */
public class Article {

    private Integer articleId;
    private String headline;
    private String content;
    private String coverImgAddress;
    private int pageview;
    private User user;
    private Integer userId;
    private String username;
    private Date lastTime;
    private Date createTime;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverImgAddress() {
        return coverImgAddress;
    }

    public void setCoverImgAddress(String coverImgAddress) {
        this.coverImgAddress = coverImgAddress;
    }

    public int getPageview() {
        return pageview;
    }

    public void setPageview(Integer pageview) {
        this.pageview = pageview;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", headline='" + headline + '\'' +
                ", content='" + content + '\'' +
                ", coverImgAddress='" + coverImgAddress + '\'' +
                ", pageview=" + pageview +
                ", user=" + user +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", lastTime=" + lastTime +
                ", createTime=" + createTime +
                '}';
    }
}
