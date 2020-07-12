package top.theonecyl.graphicSystem.web.useradmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.theonecyl.graphicSystem.dto.ImageExecution;
import top.theonecyl.graphicSystem.entity.Article;
import top.theonecyl.graphicSystem.entity.Image;
import top.theonecyl.graphicSystem.entity.User;
import top.theonecyl.graphicSystem.service.IImageService;
import top.theonecyl.graphicSystem.utils.HttpServletRequestUtil;
import top.theonecyl.graphicSystem.utils.ImageHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/image")
public class ImageManagementController {

    @Autowired
    private IImageService imageService;

    //获取图片列表
    @RequestMapping(value = "/getImageList")
    @ResponseBody
    public Map<String,Object> getImageList(@RequestBody Image image, HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        //得到存入登录用户信息的image
        Image image1 = getImage(request, image);
        //获取rowIndex
        String rowIndex = HttpServletRequestUtil.getString(request,"rowIndex");
        if(image!=null&&rowIndex!=null&&!rowIndex.equals("")){
            ImageExecution imageExecution = imageService.queryImageList(image1, Integer.parseInt(rowIndex));
            Map<String, Object> imageReturnMap = getImageReturnMap(imageExecution, map);

//            System.out.println("imageList-->"+imageExecution.getImageList());

            imageReturnMap.put("imageList",imageExecution.getImageList());
            return imageReturnMap;
        }
        map.put("success",false);
        map.put("errMsg","获取图片信息失败！");
        return map;
    }

    //根据图片id查询图片
    @RequestMapping(value = "/getImageById",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getImageById(@RequestBody Image image,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        Image image1 = getImage(request, image);
        if(image1!=null&&image1.getImageId()!=null&&!image1.getImageId().equals("")){
            ImageExecution imageExecution = imageService.queryImageById(image.getImageId());
            Map<String, Object> imageReturnMap = getImageReturnMap(imageExecution, map);

            System.out.println("imageList-->"+imageExecution.getImageList());
            imageReturnMap.put("imageList",imageExecution.getImageList());
            imageReturnMap.put("imageListList",imageExecution.getImageList().get(0).getImageList());
            imageReturnMap.put("loginUser",image1.getUser());
            return imageReturnMap;
        }
        map.put("success",false);
        map.put("errMsg","获取图片信息失败！");
        return map;
    }

    //添加图片
    @RequestMapping(value = "/addImage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addImage(@RequestBody List<Object> list,HttpServletRequest request){

        System.out.println("addImage-->"+list);

        Map<String,Object> map = new HashMap<String,Object>();
        Image imageFromJson = (Image) list.get(0);
        Image image = getImage(request, imageFromJson);
        List imageAddressList = (List) list.get(1);
        List<ImageHolder> imageHolders = new ArrayList<>();
        if(imageAddressList!=null&&imageAddressList.size()>0) {
            for (Object imageAddress : imageAddressList) {
                File file = new File((String) imageAddress);
                try {
                    imageHolders.add(new ImageHolder(file.getName(), new FileInputStream(file)));
                } catch (FileNotFoundException e) {
                    map.put("success", false);
                    map.put("errMsg", "上传图片失败！");
                    return map;
                }

            }
            if (image != null && image.getImageName() != null && !image.getImageName().equals("")
                    && image.getImageAddress() != null && !image.getImageAddress().equals("")) {
                ImageExecution imageExecution = imageService.addImage(image, imageHolders);
                return getImageReturnMap(imageExecution, map);
            } else {
                map.put("success", false);
                map.put("errMsg", "图片标题不能为空");
                return map;
            }

        }

        map.put("success",false);
        map.put("errMsg","获取图片信息失败！");
        return map;
    }
    //删除图片
    @RequestMapping(value = "/delImage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delImage(@RequestBody Image image){
        Map<String,Object> map = new HashMap<String,Object>();

//        System.out.println("delImage-->"+image);

        if(image!=null&&image.getImageId()!=null){
            int i = imageService.delImage(image.getImageId());
            if(i==1){
                map.put("success",true);
                map.put("errMsg","删除图片成功");
                return map;
            }else{
                map.put("success",false);
                map.put("errMsg","删除图片失败");
                return map;
            }
        }
        map.put("success",false);
        map.put("errMsg","获取图片信息失败！");
        return map;
    }
    //更新图片
    @RequestMapping(value = "/updateImage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateImage(@RequestBody Image image){
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("success",false);
        map.put("errMsg","获取图片信息失败！");
        return map;
    }

    //获取登录用户信息,并且存入图片信息中去
    private static Image getImage(HttpServletRequest request, Image image){
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if(loginUser!=null) {
            image.setUser(loginUser);
            image.setUserName(loginUser.getUsername());
        }
        return image;
    }


    private static Map<String,Object> getImageReturnMap(ImageExecution imageExecution,Map<String,Object> map){
        if(imageExecution.getState()==1){
            map.put("success",true);
            map.put("errMsg",imageExecution.getStateInfo());
            return map;
        }else{
            map.put("success",false);
            map.put("errMsg",imageExecution.getStateInfo());
            return map;
        }
    }


}

