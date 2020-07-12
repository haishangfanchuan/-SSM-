package top.theonecyl.graphicSystem.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/article")
public class ArticleAdminController {

    //添加文章
    @RequestMapping(value = "/addArticleB",method = RequestMethod.GET)
    public String addArticle(){
        return "articleadmin/articleoperation";
    }

    //删除文章
    @RequestMapping(value = "/delArticleB",method = RequestMethod.GET)
    public String delArticle(){
        return "useradmin/userinfo";
    }

    //更新文章
    @RequestMapping(value = "/updateArticleB",method = RequestMethod.GET)
    public String updateArticle(){
        return "articleadmin/articleoperation";
    }

    //根据文章id获取文章具体信息
    @RequestMapping(value = "/queryArticleByIdB",method = RequestMethod.GET)
    public String queryArticleById(){
        return "articleadmin/articledetail";
    }

    //获取文章列表
    @RequestMapping(value = "/queryArticleListB",method = RequestMethod.GET)
    public String queryArticleList(){
        return "articleadmin/articlelist";
    }
}
