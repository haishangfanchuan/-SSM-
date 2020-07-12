package top.theonecyl.graphicSystem.web.useradmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.theonecyl.graphicSystem.dto.CommentExecution;
import top.theonecyl.graphicSystem.entity.Comment;
import top.theonecyl.graphicSystem.service.ICommentService;
import top.theonecyl.graphicSystem.utils.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/comment")

public class CommentManagementController {

    @Autowired
    private ICommentService commentService;

    //添加评论
    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addComment(@RequestBody Comment comment){
        Map<String, Object> map = new HashMap<>();

//        System.out.println("addComment-->"+comment);

        if(comment!=null){
            if(comment.getCommentContent()!=null&&!comment.getCommentContent().equals("")) {
                int commentCount = commentService.queryCommentCount(comment);
                CommentExecution commentExecution = commentService.addComment(comment);
                Map<String, Object> returnCommentMap = getReturnCommentMap(commentExecution, map, comment);
                returnCommentMap.put("commentCount",commentCount);
                return returnCommentMap;
            }else{
                map.put("success",false);
                map.put("errMsg","评论内容不能为空！");
                return map;
            }
        }
        map.put("success",false);
        map.put("errMsg","获取评论信息失败！");
        return map;
    }

    //获取评论列表
    @RequestMapping(value = "/getCommentList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getCommentList(@RequestBody Comment comment, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

//        System.out.println("comment-->"+comment);

        //获取评论列表的rowIndex
        String rowIndex = HttpServletRequestUtil.getString(request, "rowIndex");
        if(comment!=null&&rowIndex!=null&&!rowIndex.equals("")){
            CommentExecution commentExecution = commentService.queryCommentList(comment, Integer.parseInt(rowIndex));
            Map<String, Object> returnCommentMap = getReturnCommentMap(commentExecution, map, comment);
            returnCommentMap.put("commentList",commentExecution.getCommentList());

//            System.out.println("commentList-->"+commentExecution.getCommentList().size());
//            System.out.println("returnCommentMap-->"+returnCommentMap);

            return returnCommentMap;
        }

        map.put("success",false);
        map.put("errMsg","获取评论列表信息失败！");
        return map;

    }

    private static Map<String,Object> getReturnCommentMap(CommentExecution commentExecution,Map<String,Object> map,Comment comment){
        if(commentExecution.getState()==1){
            map.put("success",true);
            map.put("errMsg",commentExecution.getStateInfo());
            map.put("comment",comment);
            return map;
        }else{
            map.put("success",false);
            map.put("errMsg",commentExecution.getStateInfo());
            return map;
        }
    }
}
