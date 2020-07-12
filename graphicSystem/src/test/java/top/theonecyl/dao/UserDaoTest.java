package top.theonecyl.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.theonecyl.graphicSystem.dao.IUserDao;
import top.theonecyl.graphicSystem.entity.User;

import java.util.Date;

//Junit和Spring的整合，Junit启动时会加载SpringIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//Spring的配置地址
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class UserDaoTest {
    @Autowired
    private IUserDao userDao;

    @org.junit.Test
    public void TestInsertUser(){
        User user = new User();
        user.setUsername("JackZhang");
        user.setUserLoginName("test01");
        user.setUserPassword("test01");
        user.setUserImgAddress("test01");
        user.setUserPhoneNumber("18942313966");
        user.setUserEmail("2938637333@qq.com");
        user.setCreateTime(new Date());
        user.setLastTime(new Date());
        int i = userDao.insertUser(user);
        System.out.println(i);
    }

    @org.junit.Test
    public void TestUpdateUser(){
        User user = new User();
        user.setUserId(1);
        user.setLastTime(new Date());
        userDao.updateUser(user);
    }

    @org.junit.Test
    public void TestUpdatePwd(){
        userDao.updateUserPassword(1,"Test02UP",new Date(),"JackZhang");
    }

    @org.junit.Test
    public void TestLogin(){
        User user = userDao.userLogin("test01", "Test02UP");
        System.out.println(user);
    }

    @org.junit.Test
    public void TestQueryUserById(){
        System.out.println(userDao.queryUserById(1));
    }
}
