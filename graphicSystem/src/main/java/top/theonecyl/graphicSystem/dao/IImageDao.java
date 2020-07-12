package top.theonecyl.graphicSystem.dao;

import org.apache.ibatis.annotations.Param;
import top.theonecyl.graphicSystem.entity.Image;
import top.theonecyl.graphicSystem.entity.ImageList;

import java.util.List;

public interface IImageDao {


    /**
     * 获取所有图片列表
     * @param imageCondition
     * @param startImage
     * @param PageCount
     * @return
     */
    List<Image> queryImageList(@Param("imageCondition")Image imageCondition,
                                   @Param("startImage") int startImage,
                                   @Param("pageCount") int PageCount);


    /**
     * 查找图片数量
     * @param imageCondition
     * @return
     */
    int queryImageCount(@Param("imageCondition")Image imageCondition);

    /**
     * 根据图片id获取图片
     * @param imageId
     * @return
     */
    List<Image> queryImageById(@Param("imageId")int imageId);

    /**
     * 添加图片
     * @param image
     * @return
     */
    int addImage(Image image);

    /**
     * 更新图片
     * @param image
     * @return
     */
    int updateImage(Image image);

    /**
     * 删除图片
     * @param imageId
     * @return
     */
    int delImage(@Param("imageId") int imageId);
}
