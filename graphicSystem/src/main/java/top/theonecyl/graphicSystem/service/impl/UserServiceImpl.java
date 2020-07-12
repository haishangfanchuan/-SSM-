package top.theonecyl.graphicSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import top.theonecyl.graphicSystem.dao.IUserDao;
import top.theonecyl.graphicSystem.dto.UserExecution;
import top.theonecyl.graphicSystem.entity.User;
import top.theonecyl.graphicSystem.enums.UserStateEnum;
import top.theonecyl.graphicSystem.service.IUserService;
import top.theonecyl.graphicSystem.utils.ImageHolder;
import top.theonecyl.graphicSystem.utils.ImageUtil;
import top.theonecyl.graphicSystem.utils.PathUtil;

import java.util.Date;


@Service(value = "userService")
@EnableTransactionManagement
public class UserServiceImpl implements IUserService {


    @Autowired
    private  IUserDao userDao;


    @Override
    public User userLogin(String userLoginName, String password) {
       return userDao.userLogin(userLoginName, password);
    }

    @Override
    public UserExecution userSignIn(User user) {

       if(user!=null){
           try{
               user.setCreateTime(new Date());
               user.setLastTime(new Date());
               int effectNum = userDao.insertUser(user);
               if(effectNum<=0){
                   //添加用户失败
                   return new UserExecution(UserStateEnum.ERROR);
               }else{
                   //添加成功
                   return new UserExecution(UserStateEnum.SUCCESS,user);
               }
           }catch (Exception e){
               throw new RuntimeException("UserSignIn Error:"+e.getMessage());
           }
       }
       else{
           return new UserExecution(UserStateEnum.NOT_USER);
       }
    }

    @Override
    public User queryUserById(Integer userId) {
        return userDao.queryUserById(userId);
    }

    @Override
    @Transactional
    public UserExecution updateUser(User user,ImageHolder imageHolder) {

        if(user!=null){
            user.setLastTime(new Date());
            if(imageHolder!=null){
                if(user.getUserImgAddress()!=null){
                    ImageUtil.deleteFileOrPath(user.getUserImgAddress());
                }
                addImageHolder(user,imageHolder);
                System.out.println("updateUser-->"+user);
            }
            try{
                int effectNum = userDao.updateUser(user);
                if(effectNum<=0){
                    //用户更新失败
                    return new UserExecution(UserStateEnum.ERROR);
                }else{
                    return new UserExecution(UserStateEnum.SUCCESS,user);
                }
            }catch (Exception e){
                throw new RuntimeException("UserUpdate Error:"+e.getMessage());
            }
        }else{
            return new UserExecution(UserStateEnum.NOT_USER);

        }
    }

    private void addImageHolder(User user, ImageHolder imageHolder) {
        String dest = PathUtil.getUserImagePath(user.getUserId());
        String s = ImageUtil.generateNormalImg(imageHolder, dest);
        user.setUserImgAddress(s);
    }


    @Override
    @Transactional
    public UserExecution updateUserPassword(Integer userId,String newPassword,String userLoginName) {
        if(userId!=null && newPassword!=null){
            User user = queryUserById(userId);
            try{
                //可以进行修改密码操作
                int effectNum = userDao.updateUserPassword(userId, newPassword, new Date(),userLoginName);
                if(effectNum<=0){
                    //操作失败
                    return new UserExecution(UserStateEnum.ERROR);
                }else{
                    if(!user.getUserPassword().equals(newPassword)
                            &&user.getUserLoginName().equals(userLoginName)){
                        return new UserExecution(UserStateEnum.SUCCESS,queryUserById(userId));
                }
                    //前后密码一样
                    //操作失败
                    return new UserExecution(UserStateEnum.UPDATE_PWD_ERROR);
                }
            }catch (Exception e){
                throw new RuntimeException("updateUserPassword Error:"+e.getMessage());
            }

        }
        return new UserExecution(UserStateEnum.NOT_USER);
    }
}
