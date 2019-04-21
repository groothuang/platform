package com.platform.controller

import com.google.gson.Gson
import com.platform.dao.domain.UserInfo
import com.platform.service.UserInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping(value = "/user")
class UserController {

    @Autowired
    UserInfoService userInfoService

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

    @RequestMapping("/findById")
    @ResponseBody
    UserInfo findById(UserInfo user){
        return userInfoService.findById(user.user_id)
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    String updateUser(UserInfo user){
        return userInfoService.update(user)
    }


}
