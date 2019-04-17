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

    @RequestMapping(value = "/index")
    public String index() {
        return "super/index";
    }
    @RequestMapping(value = "/userForm")
    public String userForm() {
        return "super/userForm";
    }
    @RequestMapping(value = "/adminForm")
    public String adminForm() {
        return "super/adminForm";
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

    @Autowired
    UserInfoService userInfoService

    @RequestMapping("/userMsg")
    @ResponseBody
    String userMsg(UserInfo user){
        return userInfoService.selectAll()
    }

    @RequestMapping("/adminMsg")
    @ResponseBody
    String adminMsg(UserInfo user){
        return userInfoService.selectAdmin()
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


    @RequestMapping("/addAdmin")
    @ResponseBody
    String addAdmin(UserInfo user){
        return userInfoService.insertAdmin(user)
    }

    @RequestMapping("/findByUserId")
    @ResponseBody
    UserInfo findByUserId(UserInfo user){
        return userInfoService.findByUserId(user.user_id)
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    String updateUser(UserInfo user){
        return userInfoService.update(user)
    }
}
