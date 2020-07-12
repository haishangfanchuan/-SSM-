package top.theonecyl.graphicSystem.dao;

import org.apache.ibatis.annotations.Param;
import top.theonecyl.graphicSystem.entity.User;

import java.util.Date;

public interface IUserDao {

    /**
     * 用户登陆
     * @param userLoginName
     * @param userPassword
     * @return
     */
    User userLogin(@Param("userLoginName")String userLoginName,@Param("userPassword")String userPassword);

    /**
     * 用户注册
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    User queryUserById(@Param("userId")Integer userId);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 修改用户密码
     * @param userId
     * @param newPassword
     * @param lastTime
     * @return
     */
    int updateUserPassword(@Param("userId")Integer userId,
                           @Param("newPassword")String newPassword,
                           @Param("lastTime")Date lastTime,
                           @Param("userLoginName") String  userLoginName);

}
