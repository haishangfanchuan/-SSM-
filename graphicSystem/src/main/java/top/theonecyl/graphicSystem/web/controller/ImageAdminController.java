package top.theonecyl.graphicSystem.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/image")
public class ImageAdminController {

    //获取图片列表
    @RequestMapping(value = "/getImageListB",method = RequestMethod.GET)
    public String getImageList(){
        return "imageadmin/imagelist";
    }

    //根据图片id查询图片
    @RequestMapping(value = "/getImageByIdB",method = RequestMethod.GET)
    public String getImageById(){
        return "imageadmin/imagedetail";
    }

    //添加图片
    @RequestMapping(value = "/addImageB",method = RequestMethod.GET)
    public String addImage(){
        return "imageadmin/imageoperation";
    }
    //删除图片
    @RequestMapping(value = "/delImageB",method = RequestMethod.GET)
    public String delImage(){
        return "imageadmin/imagedetail";
    }
    //更新图片
    @RequestMapping(value = "/updateImageB",method = RequestMethod.GET)
    public String updateImage(){
        return "imageadmin/imageoperation";
    }
}
