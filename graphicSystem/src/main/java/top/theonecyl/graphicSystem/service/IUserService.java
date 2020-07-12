package top.theonecyl.graphicSystem.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import top.theonecyl.graphicSystem.dto.UserExecution;
import top.theonecyl.graphicSystem.entity.User;
import top.theonecyl.graphicSystem.utils.ImageHolder;

import java.util.Date;

/**
 * 用户业务层接口类
 */
public interface IUserService {

    /**
     * 用户登陆
     * @param userLoginName
     * @param password
     * @return
     */
    User userLogin(String userLoginName, String password);

    /**
     * 用户注册
     * @param user
     * @return
     */
    UserExecution userSignIn(User user);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    User queryUserById(Integer userId);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    UserExecution updateUser(User user, ImageHolder imageHolder);

    /**
     * 修改用户密码
     * @param userId
     * @param newPassword
     * @return
     */
    UserExecution updateUserPassword(Integer userId,String newPassword,String userLoginName);
}
