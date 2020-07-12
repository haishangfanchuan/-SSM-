package top.theonecyl.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.theonecyl.BaseTest;
import top.theonecyl.graphicSystem.dao.IImageListDao;
import top.theonecyl.graphicSystem.entity.ImageList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class IImageListDaoTest extends BaseTest {

    @Autowired
    private IImageListDao imageListDao;

    @Test
    public void queryImageList() {

    }

    @Test
    public void batchInsertImageList() {
        List<ImageList> imageLists = new ArrayList<>();
        ImageList imageList = new ImageList();
        imageList.setCreateTime(new Date());
        imageList.setImageListAddress("E:/test/4.jpg");
        imageList.setImageId(1);
        imageLists.add(imageList);

        ImageList imageList1 = new ImageList();
        imageList1.setCreateTime(new Date());
        imageList1.setImageListAddress("E:/test/3.png");
        imageList1.setImageId(1);
        imageLists.add(imageList1);
        int i = imageListDao.batchInsertImageList(imageLists);
        System.out.println(i);
    }

    @Test
    public void delImageList() {
        for(int i=2;i<=6;i++){
            imageListDao.delImageList(i);
        }
    }
}