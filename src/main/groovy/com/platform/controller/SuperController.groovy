package com.platform.controller

import com.google.gson.Gson
import com.platform.dao.domain.User
import com.platform.dao.domain.UserInfo
import com.platform.service.UserInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping(value = "/super")
class SuperController {

    @Autowired
    UserInfoService userInfoService

    @RequestMapping(value = "/index")
    public String index() {
        return "super/index";
    }
    @RequestMapping(value = "/userForm")
    public String userForm() {
        return "super/userForm";
    }
    @RequestMapping(value = "/users")
    public String users() {
        return "super/users";
    }
    @RequestMapping(value = "/order")
    public String order() {
        return "super/order";
    }
    @RequestMapping(value = "/power")
    public String power() {
        return "super/power";
    }
    @RequestMapping(value = "/tools")
    public String tools() {
        return "super/tools";
    }
    @RequestMapping(value = "/sharing")
    public String sharing() {
        return "super/sharing";
    }
    @RequestMapping(value = "/adminis")
    public String adminis() {
        return "super/adminis";
    }
    @RequestMapping(value = "/msgboard")
    public String msgboard() {
        return "super/msgboard";
    }

    @RequestMapping("/userMsg")
    @ResponseBody
    String userMsg(UserInfo user){
        return userInfoService.selectAll()
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    String deleteUser(UserInfo user){
        return userInfoService.delete(user.user_id)
    }

    @RequestMapping("/addUser")
    @ResponseBody
    String addUser(UserInfo user){
        return userInfoService.insert(user)
    }

    @RequestMapping("/findByUserName")
    @ResponseBody
    UserInfo findByUserName(UserInfo user){
        return userInfoService.findByUserName(user.user_name)
    }
}
