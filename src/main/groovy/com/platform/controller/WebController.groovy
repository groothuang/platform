package com.platform.controller

import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpSession

@Controller
@RequestMapping(value = "/web")
class WebController {
    @RequestMapping(value = "/index")
    public String index() {
        return "web/index";
    }
    @PostMapping(value = "/addMsg")
    ModelAndView addMsg(MsgInfo msgInfo, ModelAndView modelAndView, HttpSession httpSession){
        def list = []
        list.add(msgInfo.pick_up_location)
        list.add(msgInfo.drop_off_location)
        list.add(msgInfo.pick_up_date)
        list.add(msgInfo.drop_off_date)
        httpSession.setAttribute("list", msgInfo.pick_up_location)
        modelAndView.addObject("list",list)
        modelAndView.setViewName("redirect:/web/booking.html")
        return modelAndView
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "web/about";
    }
    @RequestMapping(value = "/msgboard")
    public String category() {
        return "web/msgboard";
    }
    @RequestMapping(value = "/accessories")
    public String accessories() {
        return "web/accessories";
    }
    @RequestMapping(value = "/booking")
    public String booking() {
        return "web/booking";
    }
    @RequestMapping(value = "/login")
    public String login() {
        return "web/login";
    }
    @PostMapping(value = "/login")
    ModelAndView doLogin(User userVo, ModelAndView modelAndView, HttpSession httpSession){
        String username = userVo.username;
        String password = userVo.password;
        if("admin".equals(username) && "123456".equals(password)){
            httpSession.setAttribute("username", username);
            modelAndView.setViewName("redirect:/admin/index.html");
            return modelAndView;
        }
        if("super".equals(username) && "123123".equals(password)){
            httpSession.setAttribute("username", username);
            modelAndView.setViewName("redirect:/super/index.html");
            return modelAndView;
        }
        if (!"user1".equals(username)) {
            modelAndView.addObject("loginError", "用户名不存在！");
            modelAndView.setViewName("web/login");
            return modelAndView;
        }
        if (!"123".equals(password)) {
            modelAndView.addObject("loginError", "密码不正确！");
            modelAndView.setViewName("web/login");
            return modelAndView;
        }
        httpSession.setAttribute("username", username);
        modelAndView.setViewName("redirect:/web/index.html");
        return modelAndView;
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute('username');
        return "web/login";
    }
    @RequestMapping(value = "/register")
    public String register() {
        return "web/register";
    }
    @RequestMapping(value = "/contact")
    public String contact() {
        return "web/contact";
    }
    @RequestMapping(value = "/services")
    public String services() {
        return "web/services";
    }
    @RequestMapping(value = "/team")
    public String team() {
        return "web/team";
    }
    @RequestMapping(value = "/provide")
    public String provide() {
        return "web/provide";
    }
    @RequestMapping(value = "/detail")
    public String detail() {
        return "web/detail";
    }
    @RequestMapping(value = "/checkout")
    public String checkout() {
        return "web/checkout";
    }
    @RequestMapping(value = "/booking_done")
    public String done() {
        return "web/booking_done";
    }
    @RequestMapping(value = "/order")
    public String order() {
        return "web/order";
    }
}
