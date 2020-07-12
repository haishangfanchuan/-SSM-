package top.theonecyl.graphicSystem.web.useradmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.theonecyl.graphicSystem.dto.ArticleExecution;
import top.theonecyl.graphicSystem.entity.Article;
import top.theonecyl.graphicSystem.entity.User;
import top.theonecyl.graphicSystem.service.IArticleService;
import top.theonecyl.graphicSystem.service.ICommentService;
import top.theonecyl.graphicSystem.utils.HttpServletRequestUtil;
import top.theonecyl.graphicSystem.utils.ImageHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/article")
public class ArticleManagementController {

    @Autowired
    private IArticleService articleService;


    //添加文章
    @RequestMapping(value = "/addArticle",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addArticle(HttpServletRequest request,@RequestBody Article article) throws FileNotFoundException {
        Map<String, Object> map = new HashMap<>();

//        System.out.println("add前端传递的article--->"+article);

        if(article!=null){
            if(article.getHeadline()!=null&&!article.getHeadline().equals("")
                    &&article.getContent()!=null&&!article.getContent().equals("")){
                //获取登录用户信息
                Article article1 = getArticle(request, article);
                User loginUser = (User) request.getSession().getAttribute("loginUser");
                article1.setUserId(loginUser.getUserId());
                //经行添加文章操作
                ArticleExecution articleExecution = articleService.addArticle(article1);
                return getArticleState(articleExecution,map,0);
            }else{
                //操作失败
                map.put("success",true);
                map.put("errMsg","文章标题或者内容不可以为空！");
                return map;
            }
        }
        map.put("success",false);
        map.put("errMsg","获取文章信息失败！");
        return map;
    }

    //删除文章
    @RequestMapping(value = "/delArticle",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delArticle(@RequestBody Article article){
        Map<String, Object> map = new HashMap<>();
        //获取删除文章id
        if(article!=null&&article.getArticleId()!=null){
            Integer articleId = article.getArticleId();
            ArticleExecution articleExecution = articleService.delArticle(articleId);
            return getArticleState(articleExecution,map,1);
        }
        map.put("success",false);
        map.put("errMsg","获取文章失败，请重试！");
        return map;
    }

    //更新文章
    @RequestMapping(value = "/updateArticle",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateArticle(@RequestBody Article article){
//        System.out.println("update前端传递的article--->" + article);
        Map<String, Object> map = new HashMap<>();
        if(article!=null){
            if(article.getCoverImgAddress()!=null&&!article.getCoverImgAddress().equals("")) {

                File file = new File(article.getCoverImgAddress());
                try {
                    ArticleExecution articleExecution = articleService.updateArticle(article, new ImageHolder(file.getName(), new FileInputStream(file)));
                    return getArticleState(articleExecution, map, 1);
                } catch (FileNotFoundException e) {
                    map.put("success", false);
                    map.put("errMsg", "文章封面更新失败！");
                    return map;
                }
            }else{
                //不选择更新封面图
                ImageHolder imageHolder=null;
                article.setCoverImgAddress(articleService.queryArticleById(article.getArticleId()).getCoverImgAddress());
                ArticleExecution articleExecution = articleService.updateArticle(article, imageHolder);
                return getArticleState(articleExecution,map,1);
            }
        }
        map.put("success",false);
        map.put("errMsg","获取文章信息失败！");
        return map;
    }

    //根据文章id获取文章具体信息
    @RequestMapping(value = "/queryArticleById",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryArticleById(HttpServletRequest request,@RequestBody Article article){
        Map<String, Object> map = new HashMap<>();
        if(article!=null&&article.getArticleId()!=null&&!article.getArticleId().equals("")){
            Article articleById = articleService.queryArticleById(article.getArticleId());
            if(articleById!=null) {
                //查询成功
                Article article1 = getArticle(request, articleById);
                if(article1!=null){
                    User loginUser = (User) request.getSession().getAttribute("loginUser");
                    //获取评论列表

//                    System.out.println("queryArticleById-->"+article1);

                    map.put("loginUser",loginUser);
                    map.put("success",true);
                    map.put("article",article1);
                    map.put("errMsg","获取文章列表信息成功！");
                    return map;
                }
            }else{
                //查询失败
                map.put("success",false);
                map.put("errMsg","查询文章信息失败！");
                return map;
            }

        }
        map.put("success",false);
        map.put("errMsg","获取文章信息失败！");
        return map;

    }


    //获取文章列表
    @RequestMapping(value = "/queryArticleList")
    @ResponseBody
    public Map<String,Object> queryArticleList(@RequestBody Article article, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

//        System.out.println("原生传递的json-->"+article);

        Article article1 = getArticle(request, article);
        String rowIndex = HttpServletRequestUtil.getString(request, "rowIndex");
        if(article1!=null&&rowIndex!=null){
            //查询文章总数
            int articleCount = articleService.queryArticleCount(article1);

//            article1.getUser().setUserId(null);
//            article1.setUserId(null);

//            System.out.println("queryArticle-->"+article1);

//            System.out.println("article1-->"+article1);

            ArticleExecution articleExecution = articleService.queryArticleList(article1,Integer.parseInt(rowIndex));
//            System.out.println("ArticleList-->"+articleExecution.getArticleList().size());

            return getArticleState(articleExecution, map,articleCount);
        }
        map.put("success",false);
        map.put("errMsg","获取文章列表信息失败！");
        return map;
    }



    //获取登录用户信息,并且存入文章信息中去
    private static Article getArticle(HttpServletRequest request,Article article){
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if(loginUser!=null) {
            article.setUser(loginUser);
//            article.setUserId(loginUser.getUserId());
            article.setUsername(loginUser.getUsername());
        }
        return article;
    }

    private static Map<String,Object> getArticleState(ArticleExecution articleExecution,Map<String,Object> map,int articleCount){
        if(articleExecution.getState()==1){
            //操作成功
            map.put("success",true);
            map.put("errMsg",articleExecution.getStateInfo());
            map.put("articleCount",articleCount);
            map.put("articleList",articleExecution.getArticleList());
            return map;
        }else {
            map.put("success",true);
            map.put("errMsg",articleExecution.getStateInfo());
            return map;
        }
    }
}
