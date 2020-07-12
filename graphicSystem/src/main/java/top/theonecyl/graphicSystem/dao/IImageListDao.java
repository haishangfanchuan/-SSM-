package top.theonecyl.graphicSystem.dao;

import org.apache.ibatis.annotations.Param;
import top.theonecyl.graphicSystem.entity.ImageList;

import java.util.List;

public interface IImageListDao {

    /**
     * 通过ImageId获取ImageList的所有图片
     * @param imageId
     * @return
     */
    List<ImageList> queryImageList(@Param("imageId")int imageId);

    /**
     * 批量插入图片
     * @param imageLists
     * @return
     */
    int batchInsertImageList(@Param("imageLists") List<ImageList> imageLists);

    /**
     * 通过imageId删除所有图片详情
     * @param imageListId
     * @return
     */
    int delImageList(int imageListId);
}
