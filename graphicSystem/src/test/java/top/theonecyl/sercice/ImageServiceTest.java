package top.theonecyl.sercice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.theonecyl.BaseTest;
import top.theonecyl.graphicSystem.dto.ImageExecution;
import top.theonecyl.graphicSystem.entity.Image;
import top.theonecyl.graphicSystem.entity.User;
import top.theonecyl.graphicSystem.service.IImageService;
import top.theonecyl.graphicSystem.utils.ImageHolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ImageServiceTest extends BaseTest {

    @Autowired
    private IImageService iImageService;

    @Test
    public void testAddImage() throws FileNotFoundException {
        Image image = new Image();
        image.setImageAddress("E:/test/3.png");
        User user = new User();
        user.setUserId(14);
        image.setUser(user);
        image.setUserName("JackZhang");
        image.setImageName("testAddImage");
        File file = new File("E:/test/7.png");
        ImageHolder imageHolder = new ImageHolder(file.getName(), new FileInputStream(file));
        File file1 = new File("E:/test/10.jpg");
        ImageHolder imageHolder2 = new ImageHolder(file.getName(), new FileInputStream(file1));
        List<ImageHolder> imageHolders = new ArrayList<ImageHolder>();
        imageHolders.add(imageHolder);
        imageHolders.add(imageHolder2);
        System.out.println(iImageService.addImage(image,imageHolders));
    }

    @Test
    public void updateImage() throws FileNotFoundException {
        ImageExecution imageExecution = iImageService.queryImageById(6);
        File file = new File("E:/test/5.jpg");
        ImageHolder imageHolder = new ImageHolder(file.getName(),new FileInputStream(file));
        List<ImageHolder> imageHolders = new ArrayList<ImageHolder>();
        iImageService.updateImage(imageExecution.getImageList().get(0),imageHolder,imageHolders);
    }

    @Test
    public void queryImageList(){
        Image image = new Image();
        User user = new User();
        user.setUserId(1);
        image.setUser(user);
        image.setUserName("JackZhang");
        image.setImageName("test");
        System.out.println(iImageService.queryImageList(image,8).getImageList().size());
    }
    @Test
    public void testQueryImageById(){
        System.out.println(iImageService.queryImageById(6));
    }

    @Test
    public void testDelImage(){
        System.out.println(iImageService.delImage(6));
    }
}
