package top.theonecyl.graphicSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import top.theonecyl.graphicSystem.dao.IImageDao;
import top.theonecyl.graphicSystem.dao.IImageListDao;
import top.theonecyl.graphicSystem.dto.ImageExecution;
import top.theonecyl.graphicSystem.entity.Image;
import top.theonecyl.graphicSystem.entity.ImageList;
import top.theonecyl.graphicSystem.enums.ImageStateEnum;
import top.theonecyl.graphicSystem.service.IImageService;
import top.theonecyl.graphicSystem.utils.ImageHolder;
import top.theonecyl.graphicSystem.utils.ImageUtil;
import top.theonecyl.graphicSystem.utils.PathUtil;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("imageServiceImpl")
@EnableTransactionManagement
public class ImageServiceImpl implements IImageService {

    private final static int pageCount = 8;
    @Autowired
    private IImageDao imageDao;

    /*public ImageServiceImpl(IImageDao imageDao){
        this.imageDao=imageDao;
    }*/
    @Autowired
    private IImageListDao imageListDao;

    /*public ImageServiceImpl(IImageListDao imageListDao){
        this.imageListDao=imageListDao;
    }*/

    @Override
    @Transactional
    public ImageExecution queryImageList(Image image, int rowIndex) {
        if(image!=null && rowIndex>=0){
            try{
                List<Image> images = imageDao.queryImageList(image, (rowIndex - 1) * pageCount, pageCount);
                if(images.size()>=0){
                    //查询成功
                    return new ImageExecution(ImageStateEnum.SUCCESS,images);
                }else{
                    return new ImageExecution(ImageStateEnum.ERROR);
                }
            }catch (Exception e){
                throw new RuntimeException("QueryImageList Error:"+e.getMessage());
            }
        }
        return new ImageExecution(ImageStateEnum.NOT_IMAGE);
    }

    @Override
    public int queryImageCount(Image image) {
        return imageDao.queryImageCount(image);
    }

    @Override
    public ImageExecution queryImageById(int imageId) {
        List<Image> images = imageDao.queryImageById(imageId);

        if(images.size()>0){
            //查询成功
            return new ImageExecution(ImageStateEnum.SUCCESS,images);
        }
        return new ImageExecution(ImageStateEnum.ERROR);
    }

    @Override
    @Transactional
    public ImageExecution addImage(Image image,List<ImageHolder> imageHolders) {
        if(image!=null){
            image.setCreateTime(new Date());
            image.setLastTime(new Date());
            try{
                //先添加图片为了给imageId复制,之后再进行更新
                int effectNum1 = imageDao.addImage(image);
                if(effectNum1==1) {
                    //添加封面图
                    if (image.getImageAddress() != null) {
                        //给图片添加水印
                        File file = new File(image.getImageAddress());
                        String s = ImageUtil.generateNormalImg(new ImageHolder(file.getName(), new FileInputStream(file)), PathUtil.getImagePath(image.getImageId(),image.getUser().getUserId()));
                        image.setImageAddress(s);
                    }
                    //添加详情图
                   if(imageHolders!=null&&imageHolders.size()>0) {
                       addImageList(image, imageHolders);
                   }
                    //更新image
                    int effectNum2 = imageDao.updateImage(image);
                    if(effectNum2==1){
                        //添加成功
                        return new ImageExecution(ImageStateEnum.SUCCESS,image);
                    }
                }else{
                    //添加失败
                    return new ImageExecution(ImageStateEnum.ERROR);
                }
            }catch (Exception e){
                throw new RuntimeException("AddImage Error:"+e.getMessage());
            }
        }
        return new ImageExecution(ImageStateEnum.NOT_IMAGE);
    }

    @Override
    public int delImage(int imageId) {
        return imageDao.delImage(imageId);
    }

    @Override
    @Transactional
    public ImageExecution updateImage(Image image,ImageHolder imageHolder,List<ImageHolder> imageHolders) {
        if(image!=null){
            image.setLastTime(new Date());
            try{
                if(image.getImageAddress()!=null){
                    //删除原封面图
                    ImageUtil.deleteFileOrPath(image.getImageAddress());
                }
                //添加新的封面图
                image.setImageAddress(ImageUtil.generateNormalImg(imageHolder,PathUtil.getImagePath(image.getImageId(),image.getUser().getUserId())));
                //添加详情图
                if(imageHolders!=null && imageHolders.size()>0){
                    addImageList(image,imageHolders);
                }
                int effectNum = imageDao.updateImage(image);
                if(effectNum==1){
                    //操作成功
                    return new ImageExecution(ImageStateEnum.SUCCESS,image);
                }else{
                    return new ImageExecution(ImageStateEnum.ERROR);
                }

            }catch (Exception e){
                throw new RuntimeException("UpdateImage Error:" + e.getMessage());
            }
        }
        return new ImageExecution(ImageStateEnum.NOT_IMAGE);
    }


    //批量添加图片
    private void addImageList(Image image,List<ImageHolder> imageHolders){
        //获取图片存储路径
        String targetAddr = PathUtil.getImagePath(image.getImageId(),image.getUser().getUserId());
        List<ImageList> imageLists = new ArrayList<>();
        //添加水印
        for(ImageHolder imageHolder :imageHolders){
            String generateNormalImg = ImageUtil.generateNormalImg(imageHolder, targetAddr);
            ImageList imageList = new ImageList();
            imageList.setCreateTime(new Date());
            imageList.setImageId(image.getImageId());
            imageList.setImageListAddress(generateNormalImg);
            //add到ImageList图片列表实体类
            imageLists.add(imageList);
        }

        //imageLists添加到数据库
        if(imageLists.size()>0){
            //批量添加成功
            try {
                int i = imageListDao.batchInsertImageList(imageLists);
                if (i <= 0) {
                    //添加到db_imageList失败
                    throw new RuntimeException("AddImageList Error!");
                }
            }catch (Exception e){
                throw new RuntimeException("AddImageList Error:"+e.getMessage());
            }
        }
    }
}
