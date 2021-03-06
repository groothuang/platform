package com.platform.controller

import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.ToolsInfo
import com.platform.service.SharingInfoService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.commons.CommonsMultipartFile

import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping(value = "/sharing")
class SharingController {

    @Autowired
    SharingInfoService sharingInfoService

    @RequestMapping("/sharingMsg")
    @ResponseBody
    String sharingMsg(){
        return sharingInfoService.selectSharing();
    }

    @RequestMapping("/delete")
    @ResponseBody
    String delete(ToolsInfo toolsInfo){
        return sharingInfoService.delete(toolsInfo.car_id)
    }

    @RequestMapping("/insert")
    @ResponseBody
    String insert(ToolsInfo toolsInfo){
        return sharingInfoService.insert(toolsInfo)
    }

    @RequestMapping("/findById")
    @ResponseBody
    ToolsInfo findById(ToolsInfo toolsInfo){
        return sharingInfoService.findById(toolsInfo.car_id)
    }

    @RequestMapping("/update")
    @ResponseBody
    String update(ToolsInfo toolsInfo){
        return sharingInfoService.update(toolsInfo)
    }

    @RequestMapping("/uploadPic")
    @ResponseBody
    public Map<String,Object> uploadPic (MultipartFile file, HttpServletRequest request){
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\image";
        return sharingInfoService.uploadPic(file,path);
    }

    @RequestMapping("/search")
    @ResponseBody
    String search(ToolsInfo toolsInfo){
        return sharingInfoService.search(toolsInfo)
    }

    @RequestMapping("/enable")
    @ResponseBody
    String enable(ToolsInfo toolsInfo){
        return sharingInfoService.enable(toolsInfo)
    }
}
