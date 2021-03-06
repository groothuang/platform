package com.platform.controller

import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.ToolsInfo
import com.platform.service.ToolsInfoService
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
@RequestMapping(value = "/tools")
class ToolsController {

    @Autowired
    ToolsInfoService toolsInfoService

    @RequestMapping("/toolsMsg")
    @ResponseBody
    String toolsMsg(){
        return toolsInfoService.selectAll()
    }

    @RequestMapping("/delete")
    @ResponseBody
    String delete(ToolsInfo toolsInfo){
        return toolsInfoService.delete(toolsInfo.car_id)
    }

    @RequestMapping("/insert")
    @ResponseBody
    String insert(ToolsInfo toolsInfo){
        return toolsInfoService.insert(toolsInfo)
    }

    @RequestMapping("/findById")
    @ResponseBody
    ToolsInfo findById(ToolsInfo toolsInfo){
        return toolsInfoService.findById(toolsInfo.car_id)
    }

    @RequestMapping("/update")
    @ResponseBody
    String update(ToolsInfo toolsInfo){
        return toolsInfoService.update(toolsInfo)
    }

    @RequestMapping("/uploadPic")
    @ResponseBody
    public Map<String,Object> uploadPic (MultipartFile file, HttpServletRequest request){
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\image";
        return toolsInfoService.uploadPic(file,path);
    }

    @RequestMapping("/searchTools")
    @ResponseBody
    String searchTools(ToolsInfo toolsInfo){
        return toolsInfoService.searchTools(toolsInfo)
    }
}
