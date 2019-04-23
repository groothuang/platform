package com.platform.controller

import com.google.gson.Gson
import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.UserInfo
import com.platform.service.LoginService
import com.platform.service.PostService
import com.platform.service.UserInfoService
import com.sun.java.util.jar.pack.Instruction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpSession

@Controller
@RequestMapping(value = "/web")
class WebController {

    @Autowired
    UserInfoService userInfoService
    @Autowired
    LoginService loginService
    @Autowired
    PostService postService

    @RequestMapping(value = "/index")
    public String index() {
        return "web/index";
    }
    @PostMapping(value = "/postMsg")
    ModelAndView postMsg(MsgInfo msgInfo, ModelAndView modelAndView, HttpSession httpSession){
        String result = postService.postMsg(msgInfo)
        httpSession.setAttribute("msgInfo", result)
        modelAndView.addObject("msgInfo",result)
        modelAndView.setViewName("redirect:/web/booking.html")
        return modelAndView
    }
    @RequestMapping(value = "/about")
    public String about() {
        return "web/about";
    }
    @RequestMapping(value = "/passwordForm")
    public String passwordForm() {
        return "web/passwordForm";
    }
    @PostMapping(value = "/passwordForm")
    ModelAndView passwordForm(UserInfo userInfo, ModelAndView modelAndView, HttpSession httpSession){
        userInfo.user_name = httpSession.getAttribute('user_name')
        Gson gson = new Gson();
        println(gson.toJson(userInfo))
        String flag = postService.updatePassword(userInfo);
        if (flag == "0"){
            modelAndView.addObject("message", "旧密码错误！");
        }else {
            modelAndView.addObject("message", "修改成功！");
        }
        modelAndView.setViewName("web/passwordForm");
        return modelAndView
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
    ModelAndView doLogin(UserInfo userInfo, ModelAndView modelAndView, HttpSession httpSession){
        String flag = loginService.loginCheck(userInfo)
        if (flag == "0"){
            httpSession.setAttribute("user_name", userInfo.user_name);
            httpSession.setAttribute("role", "0");
            modelAndView.setViewName("redirect:/super/index.html");
        }
        if (flag == "1"){
            httpSession.setAttribute("user_name", userInfo.user_name);
            httpSession.setAttribute("role", "1");
            modelAndView.setViewName("redirect:/admin/index.html");
        }
        if (flag == "2"){
            httpSession.setAttribute("user_name", userInfo.user_name);
            httpSession.setAttribute("role", "2");
            modelAndView.setViewName("redirect:/web/index.html");
        }
        if  (flag == "none"){
            modelAndView.addObject("loginError", "用户名不存在！");
            modelAndView.setViewName("web/login");
        }
        if (flag == "wrong") {
            modelAndView.addObject("loginError", "密码不正确！");
            modelAndView.setViewName("web/login");
        }
        return modelAndView;
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute('user_name');
        session.removeAttribute('role');
        return "web/login";
    }
    @RequestMapping(value = "/register")
    public String register() {
        return "web/register";
    }
    @PostMapping(value = "/register")
    ModelAndView doRegister(UserInfo userInfo, ModelAndView modelAndView, HttpSession httpSession){
        String flag = loginService.registerCheck(userInfo)
        if(flag == "2"){
            modelAndView.setViewName("redirect:/web/login");
        }
        if (flag == "exist"){
            modelAndView.addObject("loginError", "用户名已存在！");
            modelAndView.setViewName("web/register");
        }
        if (flag == "wrong") {
            modelAndView.addObject("loginError", "密码不一致！");
            modelAndView.setViewName("web/register");
        }
        return modelAndView;
    }
    @RequestMapping(value = "/forgetForm")
    public String forgetForm() {
        return "web/forgetForm";
    }
    @PostMapping(value = "/forgetForm")
    ModelAndView forgetForm(UserInfo userInfo, ModelAndView modelAndView, HttpSession httpSession){
        Gson gson = new Gson();
        println(gson.toJson(userInfo))
        String flag = postService.forget(userInfo);
        if (flag == "0"){
            modelAndView.addObject("message", "手机号不正确！");
        }else {
            modelAndView.addObject("message", "修改成功！");
        }
        modelAndView.setViewName("web/forgetForm");
        return modelAndView
    }
    @RequestMapping(value = "/center")
    public String center() {
        return "web/center";
    }
    @PostMapping(value = "/center")
    ModelAndView center(UserInfo userInfo, ModelAndView modelAndView, HttpSession httpSession){
        Gson gson = new Gson();
        userInfo.user_name = httpSession.getAttribute("user_name");
        println(gson.toJson(userInfo))
        String flag = postService.updateUser(userInfo);
        if (flag == "0"){
            modelAndView.addObject("message", "保存失败！");
        }else {
            modelAndView.addObject("message", "已保存！");
        }
        modelAndView.setViewName("web/center");
        return modelAndView
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
