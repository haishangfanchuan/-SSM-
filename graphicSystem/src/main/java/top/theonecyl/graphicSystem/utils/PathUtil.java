package top.theonecyl.graphicSystem.utils;

import top.theonecyl.graphicSystem.entity.Article;

import java.io.File;

/**
 * 选择图片根路径存储
 */
public class PathUtil {
    //获取文件路径分隔符
    private static String separator = System.getProperty(File.separator);

    public static String getImgBasePath(){
        //获取系统名称
        String os= System.getProperty("os.name");
        //设置系统路径变量
        String basePath="";
        //对系统进行判断
        if(os.toLowerCase().startsWith("win")){
            basePath = "E:/IdeaProjects/graphic/images";
        }else{
            basePath = "User/work/images";
        }
        //修改路径分隔符，不同系统对应不同的的斜杠
        /*System.out.println(basePath);
        System.out.println(seperator);
        System.out.println(File.separator);*/
        basePath = basePath.replace("/",File.separator);
        return basePath;
    }

    //用户头像封面图
    public static String getUserImagePath(int userId){
        String userImagePath = "/upload/images/item/ImageText/user/"+userId+"/";
        return userImagePath.replace("/",File.separator);
    }

    //文章封面图
    public static String getArticleImagePath(int articleId){
        String articleImagePath = "/upload/images/item/ImageText/article/"+articleId+"/";
        return articleImagePath.replace("/",File.separator);
    }
    //图片存储
    public static String getImagePath(int imageId,int userId){
        String imagePath="/upload/images/item/ImageText/user/"+userId+"/images/"+imageId+"/";
        return imagePath.replace("/",File.separator);
    }
}
