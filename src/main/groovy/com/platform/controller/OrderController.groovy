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

    @RequestMapping("/delete")
    @ResponseBody
    String delete(OrderInfo orderInfo){
        return orderInfoService.delete(orderInfo.order_id)
    }

    @RequestMapping("/insert")
    @ResponseBody
    String insert(OrderInfo orderInfo){
        return orderInfoService.insert(orderInfo)
    }

    @RequestMapping("/findById")
    @ResponseBody
    OrderInfo findById(OrderInfo orderInfo){
        return orderInfoService.findById(orderInfo.order_id)
    }

    @RequestMapping("/update")
    @ResponseBody
    String update(OrderInfo orderInfo){
        return orderInfoService.update(orderInfo)
    }

    @RequestMapping("/uploadPic")
    @ResponseBody
    public Map<String,Object> uploadPic (MultipartFile file, HttpServletRequest request){
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\image";
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
