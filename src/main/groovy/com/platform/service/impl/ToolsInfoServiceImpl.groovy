package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.mapper.ToolsInfoMapper
import com.platform.service.ToolsInfoService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat

@Service
class ToolsInfoServiceImpl implements ToolsInfoService {

    @Autowired
    ToolsInfoMapper toolsInfoMapper

    String selectAll() {
        List<ToolsInfo> toolsInfo = toolsInfoMapper.selectAll();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(toolsInfo);
//        println(jsonStr)
        JSONObject jsonObject = new JSONObject();  //创建Json对象
        jsonObject.put("code", 0);         //设置Json对象的属性
        jsonObject.put("msg", "");
        jsonObject.put("count", toolsInfoMapper.countAll());
        String str = jsonObject.toString();
        String result = "{"+str.substring(1,str.length()-1)+",\"data\":"+jsonStr+"}"
        return result
    }

    int countAll(){
        return toolsInfoMapper.countAll();
    }

    int insert(ToolsInfo toolsInfo){
        def currentDay = new SimpleDateFormat("HHmmss").format(new Date());
        toolsInfo.car_id = "C"+currentDay
        return toolsInfoMapper.insert(toolsInfo)
    }

    int update(ToolsInfo toolsInfo){
        return toolsInfoMapper.update(toolsInfo)
    }

    int delete(String id){
        List<String> ids = java.util.Arrays.asList(id.split(","));
        return toolsInfoMapper.delete(ids)
    }

    ToolsInfo findById(String id){
        return toolsInfoMapper.findById(id)
    }
}
