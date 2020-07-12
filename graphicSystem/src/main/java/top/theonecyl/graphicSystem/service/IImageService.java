package top.theonecyl.graphicSystem.service;

import org.springframework.stereotype.Service;
import top.theonecyl.graphicSystem.dto.ImageExecution;
import top.theonecyl.graphicSystem.entity.Image;
import top.theonecyl.graphicSystem.utils.ImageHolder;

import java.util.List;

/**
 * 图片服务层接口类
 */
public interface IImageService {
    //获取图片列表
    ImageExecution queryImageList(Image image , int rowIndex);
    //获取图片总数
    int queryImageCount(Image image);
    //根据id查询图片
    ImageExecution queryImageById(int imageId);
    //增加图片
    ImageExecution addImage(Image image,List<ImageHolder> imageHolders);
    //删除图片
    int delImage(int imageId);
    //更新图片
    ImageExecution updateImage(Image image,ImageHolder imageHolder,List<ImageHolder> imageHolders);
}
