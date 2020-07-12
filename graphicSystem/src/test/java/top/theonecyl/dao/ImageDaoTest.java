package top.theonecyl.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.theonecyl.graphicSystem.dao.IImageDao;
import top.theonecyl.graphicSystem.entity.Image;
import top.theonecyl.graphicSystem.entity.User;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ImageDaoTest {
    @Autowired
    private IImageDao imageDao;

    @Test
    public void TestQueryImageList(){
        List<Image> images = imageDao.queryImageList(new Image(), 0, 10);
        for(Image image:images){
            System.out.println(image);
        }
    }

    @Test
    public void TestQueryImageCount(){
        Image image = new Image();
        System.out.println(imageDao.queryImageCount(image));

    }

    @Test
    public void TestAddImage(){
        Image image = new Image();
        image.setCreateTime(new Date());
        image.setImageAddress("TestAddImage");
        image.setImageName("ImageDaoTest03");
        image.setLastTime(new Date());
        User user = new User();
        user.setUserId(1);
        image.setUser(user);
        image.setUserName("JackZhang");
        imageDao.addImage(image);
    }

    @Test
    public void testUpdateImage(){
        Image image = new Image();
        image.setLastTime(new Date());
        image.setImageId(1);
        User user = new User();
        user.setUserId(2);
        image.setUser(user);
        int i = imageDao.updateImage(image);
        System.out.println(i);
    }

    @Test
    public void testQueryImageById(){
        List<Image> images = imageDao.queryImageById(9);
        System.out.println(images);
    }

    @Test
    public void TestDelImage(){
        System.out.println(imageDao.delImage(3));
    }

}
