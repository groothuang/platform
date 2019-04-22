package com.platform.controller

import com.platform.dao.domain.OrderInfo
import com.platform.service.OrderInfoService
import com.platform.service.SharingInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping(value = "/order")
class OrderController {

    @Autowired
    OrderInfoService orderInfoService

    @RequestMapping("/orderMsg")
    @ResponseBody
    String orderMsg(){
        return orderInfoService.selectAll();
    }

    @RequestMapping("/deleteTools")
    @ResponseBody
    String deleteTools(OrderInfo orderInfo){
        return orderInfoService.delete(orderInfo.car_id)
    }

    @RequestMapping("/addTools")
    @ResponseBody
    String addTools(OrderInfo orderInfo){
        return orderInfoService.insert(orderInfo)
    }

    @RequestMapping("/findById")
    @ResponseBody
    OrderInfo findById(OrderInfo orderInfo){
        return orderInfoService.findById(orderInfo.car_id)
    }

    @RequestMapping("/updateTools")
    @ResponseBody
    String updateTools(OrderInfo orderInfo){
        return orderInfoService.update(orderInfo)
    }

    @RequestMapping("/uploadPic")
    @ResponseBody
    public Map<String,Object> uploadPic (MultipartFile file, HttpServletRequest request){
        String path = "D:\\IdeaProject\\platform\\src\\main\\resources\\static\\image";
        return orderInfoService.uploadPic(file,path);
    }

    @RequestMapping("/search")
    @ResponseBody
    String search(OrderInfo orderInfo){
        return orderInfoService.search(orderInfo)
    }

    @RequestMapping("/enable")
    @ResponseBody
    String enable(OrderInfo orderInfo){
        return orderInfoService.enable(orderInfo)
    }
}
