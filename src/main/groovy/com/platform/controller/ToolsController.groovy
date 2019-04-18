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
import org.springframework.web.multipart.commons.CommonsMultipartFile

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

    @RequestMapping("/deleteTools")
    @ResponseBody
    String deleteTools(ToolsInfo toolsInfo){
        return toolsInfoService.delete(toolsInfo.car_id)
    }

    @RequestMapping("/addTools")
    @ResponseBody
    String addTools(ToolsInfo toolsInfo){
        return toolsInfoService.insert(toolsInfo)
    }

    @RequestMapping("/findById")
    @ResponseBody
    ToolsInfo findById(ToolsInfo toolsInfo){
        return toolsInfoService.findById(toolsInfo.car_id)
    }

    @RequestMapping("/updateTools")
    @ResponseBody
    String updateTools(ToolsInfo toolsInfo){
        return toolsInfoService.update(toolsInfo)
    }

/*
     * ����file.Transto �������ϴ����ļ�
     */
//    @RequestMapping("/uploadPic")
//    public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {
//        long  startTime=System.currentTimeMillis();
//        System.out.println("fileName��"+file.getOriginalFilename());
//        String path="D:/"+new Date().getTime()+file.getOriginalFilename();
//
//        File newFile=new File(path);
//        //ͨ��CommonsMultipartFile�ķ���ֱ��д�ļ���ע�����ʱ��
//        file.transferTo(newFile);
//        long  endTime=System.currentTimeMillis();
//        System.out.println("����file.Transto������ʱ�䣺"+String.valueOf(endTime-startTime)+"ms");
//        return "/success";
//    }
}
