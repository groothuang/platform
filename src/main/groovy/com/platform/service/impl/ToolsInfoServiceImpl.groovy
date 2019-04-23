package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.UserInfo
import com.platform.dao.mapper.ToolsInfoMapper
import com.platform.service.ToolsInfoService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

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

    ToolsInfo findById(String id){
        return toolsInfoMapper.findById(id)
    }

    ToolsInfo findByName(String name){
        return  toolsInfoMapper.findByName(name)
    }

    int countAll(){
        return toolsInfoMapper.countAll();
    }

    int insert(ToolsInfo toolsInfo){
        ToolsInfo d_tools
        if (toolsInfo.car_name == "" || toolsInfo.car_name == null){
            return 0
        }else {
            try{
                d_tools = toolsInfoMapper.findByName(toolsInfo.car_name)
            } catch (DataAccessException e) {
                return 0
            }
            if (d_tools == null){
                def currentDay = new SimpleDateFormat("HHmmss").format(new Date());
                toolsInfo.car_id = "C"+currentDay;
                toolsInfo.create_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                toolsInfo.car_state = "空闲";
                toolsInfo.car_source = "系统";
                Gson gson = new Gson()
                println(gson.toJson(toolsInfo))
                return toolsInfoMapper.insert(toolsInfo)
            }else {
                return 0
            }
        }
    }

    int update(ToolsInfo toolsInfo){
        return toolsInfoMapper.update(toolsInfo)
    }

    int delete(String id){
        List<String> ids = java.util.Arrays.asList(id.split(","));
        return toolsInfoMapper.delete(ids)
    }

    public Map<String,Object> uploadPic (MultipartFile file, String path) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String image = uploadFile(file, path);
            map.put("code", 0);
            map.put("image", image);
        } catch (Exception e) {
            map.put("code", 1);
            e.printStackTrace();
        }
        return map;
    }

    String uploadFile(MultipartFile file, String path) throws IOException{
        String name = file.getOriginalFilename();
        String suffixName = name.substring(name.lastIndexOf(".")); //获取源文件名后缀
//        String hash = Integer.toHexString(new Random().nextInt()); //随机名
        def currentDay = new SimpleDateFormat("HHmmss").format(new Date());
        String hash = currentDay;
        String filename = hash + suffixName; //新文件名
        File tempFile = new File(path,filename);
        if(!tempFile.getParentFile().exists()){
            tempFile.getParentFile().mkdir();
        }
        if (tempFile.exists()){
            tempFile.delete();
        }
        tempFile.createNewFile();
        file.transferTo(tempFile);
        return  tempFile.getName();
    }

    String searchTools(ToolsInfo toolsInfo) {
        Gson gson = new Gson();
        List<ToolsInfo> tools;
        String jsonStr, count;

        println("输入打印:"+gson.toJson(toolsInfo))

        if(toolsInfo.select_type == "" || toolsInfo.select_type == null){
            tools = toolsInfoMapper.selectByName(toolsInfo);
            count = toolsInfoMapper.countByName(toolsInfo);
            jsonStr = gson.toJson(tools);
            if (jsonStr == "[]"){
                tools = toolsInfoMapper.selectById(toolsInfo)
                count = toolsInfoMapper.countById(toolsInfo);
                jsonStr = gson.toJson(tools);
                if (jsonStr == "[]"){
                    tools = toolsInfoMapper.selectByName(toolsInfo);
                    count = toolsInfoMapper.countByName(toolsInfo);
                    jsonStr = gson.toJson(tools);
                }
            }
            if (jsonStr == "[]"){
                tools = toolsInfoMapper.selectAll();
                count = toolsInfoMapper.countAll();
                jsonStr = gson.toJson(tools);
            }
        }
        if (toolsInfo.select_type == "车辆名称"){
            tools = toolsInfoMapper.selectByName(toolsInfo);
            count = toolsInfoMapper.countByName(toolsInfo);
            jsonStr = gson.toJson(tools);
            if (jsonStr == "[]"){
                tools = toolsInfoMapper.selectAll();
                count = toolsInfoMapper.countAll();
                jsonStr = gson.toJson(tools);
            }
        }
        if (toolsInfo.select_type == "车辆ID"){
            tools = toolsInfoMapper.selectById(toolsInfo)
            count = toolsInfoMapper.countById(toolsInfo);
            jsonStr = gson.toJson(tools);
            if (jsonStr == "[]"){
                tools = toolsInfoMapper.selectAll();
                count = toolsInfoMapper.countAll();
                jsonStr = gson.toJson(tools);
            }
        }

        JSONObject jsonObject = new JSONObject();  //创建Json对象
        jsonObject.put("code", 0);         //设置Json对象的属性
        jsonObject.put("msg", "");
        jsonObject.put("count", count);
        String str = jsonObject.toString();
        String result = "{"+str.substring(1,str.length()-1)+",\"data\":"+jsonStr+"}"
        return result
    }
}
