package top.theonecyl.graphicSystem.web.useradmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.theonecyl.graphicSystem.dto.UserExecution;
import top.theonecyl.graphicSystem.entity.User;
import top.theonecyl.graphicSystem.service.IUserService;
import top.theonecyl.graphicSystem.utils.HttpServletRequestUtil;
import top.theonecyl.graphicSystem.utils.ImageHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户设置控制器
 */
@Controller
@RequestMapping("/user")
public class UserManagementController {

    @Autowired
    private  IUserService userService;

    //注册用户
    @RequestMapping(path = "/registerUser" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> userRegister(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(user);
        if(user!=null){
            UserExecution userExecution = userService.userSignIn(user);
            return getUserState(userExecution,map);
        }
        map.put("success",false);
        map.put("errMsg","用户信息为空，注册失败！");
        return map;
    }

    //用户登录
    @RequestMapping(value = "/userLogin",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> userLogin(HttpServletRequest request,@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        if(user!=null) {
            User loginUser= userService.userLogin(user.getUserLoginName(), user.getUserPassword());
            if(loginUser!=null){
                //登陆成功
                //将用户信息存入session域中
                request.getSession().setAttribute("loginUser",loginUser);
                map.put("success",true);
                map.put("errMsg","恭喜登陆成功！");
                return map;
            }else{
                map.put("success",false);
                map.put("errMsg","用户名或者密码错误！");
                return map;
            }
        }
        map.put("success",false);
        map.put("errMsg","用户信息为空登陆失败！");
        return map;
    }

    //获取当前登陆用户信息
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserInfo(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        //从session域中获取用户信息
        User currentUser = (User) request.getSession().getAttribute("loginUser");
//        System.out.println("getUserInfo-->"+currentUser);
        if(currentUser!=null){
            //获取用户信息成功
            map.put("success",true);
            map.put("loginUser",currentUser);
            return map;
        }
        map.put("success", false);
        map.put("errMsg", "获取用户信息失败！");
        return map;
    }

    //修改用户密码
    @RequestMapping(value = "/updateUserPassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateUserPassword(HttpServletRequest request, @RequestBody List<String> list){
        Map<String, Object> map = new HashMap<>();
        //从Session域中获取登陆的User信息
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        //更新用户信息
        User user=null;
        if(loginUser!=null) {
            user= userService.queryUserById(loginUser.getUserId());
        }
        if(user!=null) {
            //获取前端传递过来的数据
            String userLoginName = list.get(0);
            String oldUserPassword = list.get(1);
            String newUserPassword = list.get(2);
            String confirmNewUserPassword =list.get(3);

            if(oldUserPassword!=null&&!oldUserPassword.equals("")
                    &&userLoginName!=null&&!userLoginName.equals("")
                    &&newUserPassword!=null&&!newUserPassword.equals("")
                    &&confirmNewUserPassword!=null&&!confirmNewUserPassword.equals("")){
                if(oldUserPassword.equals(user.getUserPassword())) {
                    //可以进行修改密码操作
                    UserExecution userExecution = userService.updateUserPassword(user.getUserId(), newUserPassword, userLoginName);
                    return getUserState(userExecution, map);
                }else{
                    //密码输入错误
                    map.put("success",false);
                    map.put("errMsg","密码输入错误！");
                    return map;
                }
            }
        }
        map.put("success",false);
        map.put("errMsg","用户信息为空！");
        return map;
    }

    //更新用户信息
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateUserInfo(HttpServletRequest request,@RequestBody User user) throws FileNotFoundException {
        Map<String, Object> map = new HashMap<>();
//        System.out.println(user);
        //从session域中获取登陆用户信息
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser != null && user != null) {
            if (user.getUsername() != null && !user.getUsername().equals("")) {
                loginUser.setUsername(user.getUsername());
            }

            if (user.getUserPhoneNumber() != null && !user.getUserPhoneNumber().equals("")) {
                loginUser.setUserPhoneNumber(user.getUserPhoneNumber());
            }
            if (user.getUserEmail() != null && !user.getUserEmail().equals("")) {
                loginUser.setUserEmail(user.getUserEmail());
            }
            if (user.getUserAddress() != null && !user.getUserAddress().equals("")) {
                loginUser.setUserAddress(user.getUserAddress());
            }

//            System.out.println("更新之前--->" + loginUser);
            if (user.getUserImgAddress() != null && !user.getUserImgAddress().equals("")) {
                File file = new File(user.getUserImgAddress());
                ImageHolder imageHolder = new ImageHolder(file.getName(), new FileInputStream(file));
                System.out.println(imageHolder);
                UserExecution userExecution = userService.updateUser(loginUser, imageHolder);
//                System.out.println("更新之后的--->" + loginUser);
                return getUserState(userExecution, map);
            } else {
                ImageHolder imageHolder =null;
                UserExecution userExecution = userService.updateUser(loginUser, imageHolder);
                return getUserState(userExecution, map);
            }
        }
        map.put("success", false);
        map.put("errMsg", "获取用户信息失败！");
        return map;
    }

    private static Map<String,Object> getUserState(UserExecution userExecution,Map<String,Object> map){
        if(userExecution.getState()==1){
            //修改成功
            map.put("success",true);
            map.put("errMsg",userExecution.getStateInfo());
            return map;
        }else{
            map.put("success",false);
            map.put("errMsg",userExecution.getStateInfo());
            return map;
        }
    }
}
