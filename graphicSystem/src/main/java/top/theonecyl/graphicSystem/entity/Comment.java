package top.theonecyl.graphicSystem.entity;

import java.util.Date;

/**
 * 评论实体类
 */
public class Comment {
    private Integer commentId;
    private String commentContent;
    //评论楼层
    private int commentNum;
    private Date createTime;
    private Article article;
    private Integer articleId;
    private User user;
    private Integer userId;
    private String userName;
    private String userImgAddress;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImgAddress() {
        return userImgAddress;
    }

    public void setUserImgAddress(String userImgAddress) {
        this.userImgAddress = userImgAddress;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", commentNum=" + commentNum +
                ", createTime=" + createTime +
                ", article=" + article +
                ", articleId=" + articleId +
                ", user=" + user +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userImgAddress='" + userImgAddress + '\'' +
                '}';
    }
}
