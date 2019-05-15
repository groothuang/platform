package com.platform.controller

import com.google.gson.Gson
import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.OrderInfo
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.UserInfo
import com.platform.service.UserService
import com.platform.service.OrderInfoService
import com.platform.service.BookService
import com.platform.service.ToolsInfoService
import com.platform.service.UserInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping(value = "/web")
class WebController {

    @Autowired
    UserInfoService userInfoService
    @Autowired
    UserService userService
    @Autowired
    BookService bookService
    @Autowired
    ToolsInfoService toolsInfoService
    @Autowired
    OrderInfoService orderInfoService

    @RequestMapping(value = "/login")
    public String login() {
        return "web/login";
    }
    @PostMapping(value = "/login")
    ModelAndView login(UserInfo userInfo, ModelAndView modelAndView, HttpSession httpSession){
        String flag = userService.loginCheck(userInfo)
        if (flag == "0"){
            httpSession.setAttribute("user_name", userInfo.user_name);
            httpSession.setAttribute("role", "0");
            modelAndView.setViewName("redirect:/super/index");
        }
        if (flag == "1"){
            httpSession.setAttribute("user_name", userInfo.user_name);
            httpSession.setAttribute("role", "1");
            modelAndView.setViewName("redirect:/admin/index");
        }
        if (flag == "2"){
            httpSession.setAttribute("user_name", userInfo.user_name);
            httpSession.setAttribute("role", "2");
            modelAndView.setViewName("redirect:/web/index");
        }
        if  (flag == "none"){
            modelAndView.addObject("message", "用户名不存在！");
            modelAndView.setViewName("web/login");
        }
        if (flag == "wrong") {
            modelAndView.addObject("message", "密码不正确！");
            modelAndView.setViewName("web/login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute('user_name');
        session.removeAttribute('role');
        session.removeAttribute('car_id');
        session.removeAttribute('orderInfo');
        session.removeAttribute('msgInfo');
        return "web/login";
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "web/register";
    }
    @PostMapping(value = "/register")
    ModelAndView register(UserInfo userInfo, ModelAndView modelAndView){
        String flag = userService.registerCheck(userInfo)
        if(flag == "2"){
            modelAndView.setViewName("redirect:/web/login");
        }
        if (flag == "exist"){
            modelAndView.addObject("message", "用户名已存在！");
            modelAndView.setViewName("web/register");
        }
        if (flag == "wrong") {
            modelAndView.addObject("message", "密码不一致！");
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
        String flag = userService.forget(userInfo);
        if (flag == "0"){
            modelAndView.addObject("message", "手机号不正确！");
        }else {
            modelAndView.addObject("message", "修改成功！");
        }
        modelAndView.setViewName("web/forgetForm");
        return modelAndView
    }

    @RequestMapping(value = "/center")
    ModelAndView center(ModelAndView modelAndView, HttpSession httpSession){
        String user_name = httpSession.getAttribute('user_name')
        if(user_name == '' || user_name == null){
            modelAndView.setViewName("redirect:/web/login")
        }else {
            UserInfo userInfo = userInfoService.findByName(user_name);
            modelAndView.addObject("u",userInfo)
        }
        return modelAndView;
    }
    @PostMapping(value = "/center")
    ModelAndView center(UserInfo userInfo, ModelAndView modelAndView, HttpSession httpSession){
        userInfo.user_name = httpSession.getAttribute("user_name");
        String flag = userService.updateUser(userInfo);
        if (flag == "0"){
            modelAndView.addObject("message", "保存失败！");
        }else {
            modelAndView.addObject("message", "已保存！");
        }
        modelAndView.setViewName("redirect:/web/center");
        return modelAndView
    }

    @RequestMapping(value = "/passwordForm")
    public String passwordForm() {
        return "web/passwordForm";
    }
    @PostMapping(value = "/passwordForm")
    ModelAndView passwordForm(UserInfo userInfo, ModelAndView modelAndView, HttpSession httpSession){
        userInfo.user_name = httpSession.getAttribute('user_name')
        String flag = userService.updatePassword(userInfo);
        if (flag == "0"){
            modelAndView.addObject("message", "旧密码错误！");
            modelAndView.setViewName("web/passwordForm");
        }else {
            modelAndView.setViewName("redirect:/web/logout");
        }
        return modelAndView
    }

    @RequestMapping(value = "/index")
    ModelAndView index(ModelAndView modelAndView){
        List<ToolsInfo> tools = bookService.selectTools()
        modelAndView.addObject("tools",tools)
        return modelAndView
    }

    @PostMapping(value = "/postMsg")
    ModelAndView postMsg(MsgInfo msgInfo, ModelAndView modelAndView, HttpSession httpSession){
        httpSession.setAttribute("orderInfo", bookService.postMsg(msgInfo))
        modelAndView.setViewName("redirect:/web/booking")
        return modelAndView
    }

    @RequestMapping(value = "/booking")
    ModelAndView doBooking(ModelAndView modelAndView){
        List<ToolsInfo> tools = bookService.selectTools()
        modelAndView.addObject("tools",tools)
        modelAndView.addObject("count",toolsInfoService.countAll())
        return modelAndView
    }

    @RequestMapping(value = "/addition")
    ModelAndView doAddition(OrderInfo orderInfo, ModelAndView modelAndView, String car_id, HttpSession httpSession){
        orderInfo = httpSession.getAttribute('orderInfo')
        String user_name = httpSession.getAttribute('user_name');
        if (orderInfo == null || user_name == '' || user_name == null){
            //为空
            modelAndView.setViewName("redirect:/web/index")
        }else {
            ToolsInfo toolsInfo = toolsInfoService.findById(car_id);
            modelAndView.addObject("t",toolsInfo)
        }
        return modelAndView
    }

    @RequestMapping(value = "/checkout")
    ModelAndView doCheckout(OrderInfo orderInfo, ModelAndView modelAndView, HttpServletRequest request, HttpSession httpSession){
        String car_id = request.getParameter("car_id");
        httpSession.setAttribute("car_id",car_id)
        orderInfo = httpSession.getAttribute('orderInfo')
        String user_name = httpSession.getAttribute('user_name');
        if (orderInfo == null || user_name == '' || user_name == null){
            //为空
            modelAndView.setViewName("redirect:/web/index")
        }else {
            ToolsInfo toolsInfo = toolsInfoService.findById(car_id);
            UserInfo userInfo = userInfoService.findByName(user_name);
            modelAndView.addObject("t",toolsInfo)
            modelAndView.addObject("u",userInfo)
            modelAndView.addObject("o",orderInfo)
        }
        return modelAndView
    }

    @PostMapping(value = "/submit")
    ModelAndView submit(OrderInfo orderInfo, ModelAndView modelAndView, HttpSession httpSession){
        String car_id = httpSession.getAttribute("car_id");
        orderInfo = httpSession.getAttribute('orderInfo');
        String user_name = httpSession.getAttribute('user_name');
        if (orderInfo == null || user_name == '' || user_name == null){
            //为空
            modelAndView.setViewName("redirect:/web/index")
        }else {
            String order_id = bookService.addOrder(user_name,car_id,orderInfo);
            httpSession.setAttribute('order_id',order_id)
            modelAndView.setViewName("redirect:/web/bookingDone")
        }
        return modelAndView
    }

    @RequestMapping(value = "/bookingDone")
    ModelAndView bookingDone(ModelAndView modelAndView, HttpSession httpSession){
        String user_name = httpSession.getAttribute('user_name');
        if (user_name == '' || user_name == null){
            //为空
            modelAndView.setViewName("redirect:/web/index")
        }else{
            String order_id = httpSession.getAttribute('order_id');
            OrderInfo orderInfo = orderInfoService.findById(order_id);
            ToolsInfo toolsInfo = toolsInfoService.findById(orderInfo.car_id);
            UserInfo userInfo = userInfoService.findByName(orderInfo.user_name);
            modelAndView.addObject("order_id",order_id)
            modelAndView.addObject("t",toolsInfo)
            modelAndView.addObject("u",userInfo)
            modelAndView.addObject("o",orderInfo)
        }
        return modelAndView
    }

    @RequestMapping(value = "/order")
    ModelAndView order(ModelAndView modelAndView,HttpSession httpSession) {
        String user_name = httpSession.getAttribute('user_name');
        if (user_name == '' || user_name == null){
            //为空
            modelAndView.setViewName("redirect:/web/index")
        }else {
            OrderInfo orderInfo = orderInfoService.findByUser(user_name)
            if (orderInfo != null){
                ToolsInfo toolsInfo = toolsInfoService.findById(orderInfo.car_id)
                UserInfo userInfo = userInfoService.findById(orderInfo.user_id)
                modelAndView.addObject("o",orderInfo)
                modelAndView.addObject("t",toolsInfo)
                modelAndView.addObject("u",userInfo)
                modelAndView.setViewName("web/order")
            }else {
                modelAndView.setViewName("redirect:/web/index")
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "web/about";
    }

    @RequestMapping(value = "/msgboard")
    public String category() {
        return "web/msgboard";
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
}
