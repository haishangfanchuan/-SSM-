package top.theonecyl.sercice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.theonecyl.BaseTest;
import top.theonecyl.graphicSystem.dto.UserExecution;
import top.theonecyl.graphicSystem.entity.User;
import top.theonecyl.graphicSystem.service.IUserService;
import top.theonecyl.graphicSystem.utils.ImageHolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

public class UserServiceTest extends BaseTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testUserLogin(){
        User user = userService.userLogin("test01", "Test02UP");
        System.out.println(user);
    }

    @Test
     public void testUserSignIn(){
        User user = new User();
        user.setLastTime(new Date());
        user.setCreateTime(new Date());
        user.setUsername("JackZhang");
        user.setUserLoginName("testUserSignIn04");
        user.setUserPassword("testUserSignIn01");
        UserExecution userExecution = userService.userSignIn(user);
        System.out.println(userExecution);
    }

    @Test
    public void testQueryUserById(){
        User user = userService.queryUserById(1);
        System.out.println(user);
    }

    @Test
    public void testUpdateUser(){
        User user = userService.queryUserById(14);
        try {
            File file = new File("E:/test/8.jpg");
            InputStream inputStream = new FileInputStream(file);
            UserExecution userExecution = userService.updateUser(user, new ImageHolder(file.getName(),inputStream));
            System.out.println(userExecution);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
