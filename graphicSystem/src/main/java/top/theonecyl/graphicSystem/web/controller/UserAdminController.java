package top.theonecyl.graphicSystem.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
public class UserAdminController {

    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value = "/userRegister",method = RequestMethod.GET)
    public String userRegister() {
        return "useradmin/userregister";
    }
    /**
     * 用户登陆
     * @return
     */
    @RequestMapping(value = "/userLoginB",method = RequestMethod.GET)
    public String userLogin(){
        return "useradmin/userlogin";
    }

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/getUserInfoB",method = RequestMethod.GET)
    public String getUserInfo(){
        return "useradmin/index";
    }

    /**
     * 修改用户密码
     */
    @RequestMapping(value = "/updateUserPasswordB",method = RequestMethod.GET)
    public String updateUserPassword(){
        return "useradmin/userinfo";
    }

    @RequestMapping(value = "/updateUserB",method = RequestMethod.GET)
    public String updateUser(){
        return "useradmin/userinfo";
    }
}
