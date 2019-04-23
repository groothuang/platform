package com.platform.controller

import com.platform.dao.domain.UserInfo
import com.platform.service.UserInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping(value = "/admin")
class AdminController {
    @RequestMapping(value = "/index")
    public String index() {
        return "super/index";
    }
    @RequestMapping(value = "/login")
    public String login() {
        return "super/login";
    }
    @RequestMapping(value = "/users")
    public String users() {
        return "super/users";
    }
    @RequestMapping(value = "/msgboard")
    public String msgboard() {
        return "super/msgboard";
    }
    @RequestMapping(value = "/order")
    public String order() {
        return "super/order";
    }
    @RequestMapping(value = "/tools")
    public String tools() {
        return "super/tools";
    }
    @RequestMapping(value = "/sharing")
    public String sharing() {
        return "super/sharing";
    }
    @RequestMapping(value = "/power")
    public String power() {
        return "super/index";
    }
    @RequestMapping(value = "/adminis")
    public String adminis() {
        return "super/index";
    }

}
