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
        return "admin/index";
    }
    @RequestMapping(value = "/login")
    public String login() {
        return "admin/login";
    }
    @RequestMapping(value = "/users")
    public String users() {
        return "admin/users";
    }
    @RequestMapping(value = "/platform")
    public String platform() {
        return "admin/platform";
    }
    @RequestMapping(value = "/msgboard")
    public String msgboard() {
        return "admin/msgboard";
    }
    @RequestMapping(value = "/order")
    public String order() {
        return "admin/order";
    }
    @RequestMapping(value = "/tools")
    public String tools() {
        return "admin/tools";
    }
    @RequestMapping(value = "/sharing")
    public String sharing() {
        return "admin/sharing";
    }
    @RequestMapping(value = "/leacots")
    public String leacots() {
        return "admin/leacots";
    }
    @RequestMapping(value = "/adminis")
    public String adminis() {
        return "admin/adminis";
    }

}
