package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.UserInfo
import com.platform.dao.mapper.SharingInfoMapper
import com.platform.service.SharingInfoService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.text.SimpleDateFormat

@Service
class SharingInfoServiceImpl implements SharingInfoService {

    @Autowired
    SharingInfoMapper sharingInfoMapper

    String selectSharing(){
        List<ToolsInfo> toolsInfo = sharingInfoMapper.selectSharing();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(toolsInfo);
//        println(jsonStr)
        JSONObject jsonObject = new JSONObject();  //创建Json对象
        jsonObject.put("code", 0);         //设置Json对象的属性
        jsonObject.put("msg", "");
        jsonObject.put("count", sharingInfoMapper.countSharing());
        String str = jsonObject.toString();
        String result = "{"+str.substring(1,str.length()-1)+",\"data\":"+jsonStr+"}"
        return result
    }

    ToolsInfo findById(String id){
        return sharingInfoMapper.findById(id)
    }

    ToolsInfo findByName(String name){
        return  sharingInfoMapper.findByName(name)
    }

    int countSharing(){
        return sharingInfoMapper.countSharing();
    }

    int insert(ToolsInfo toolsInfo){
        def currentDay = new SimpleDateFormat("HHmmss").format(new Date());
        toolsInfo.car_id = "C"+currentDay;
        toolsInfo.create_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        toolsInfo.car_state = "空闲";
        toolsInfo.enable_flag = "false";
        toolsInfo.car_source = "用户";
        Gson gson = new Gson();
        println(gson.toJson(toolsInfo));
        return sharingInfoMapper.insert(toolsInfo);
    }

    int update(ToolsInfo toolsInfo){
        return sharingInfoMapper.update(toolsInfo);
    }

    int delete(String id){
        List<String> ids = java.util.Arrays.asList(id.split(","));
        return sharingInfoMapper.delete(ids);
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

    String search(ToolsInfo toolsInfo) {
        Gson gson = new Gson();
        List<ToolsInfo> tools;
        String jsonStr, count;

        println("输入打印:"+gson.toJson(toolsInfo))

        if(toolsInfo.select_type == "" || toolsInfo.select_type == null){
            tools = sharingInfoMapper.selectByName(toolsInfo);
            count = sharingInfoMapper.countByName(toolsInfo);
            jsonStr = gson.toJson(tools);
            if (jsonStr == "[]"){
                tools = sharingInfoMapper.selectById(toolsInfo)
                count = sharingInfoMapper.countById(toolsInfo);
                jsonStr = gson.toJson(tools);
                if (jsonStr == "[]"){
                    tools = sharingInfoMapper.selectByName(toolsInfo);
                    count = sharingInfoMapper.countByName(toolsInfo);
                    jsonStr = gson.toJson(tools);
                }
            }
            if (jsonStr == "[]"){
                tools = sharingInfoMapper.selectSharing();
                count = sharingInfoMapper.countSharing();
                jsonStr = gson.toJson(tools);
            }
        }
        if (toolsInfo.select_type == "车辆名称"){
            tools = sharingInfoMapper.selectByName(toolsInfo);
            count = sharingInfoMapper.countByName(toolsInfo);
            jsonStr = gson.toJson(tools);
            if (jsonStr == "[]"){
                tools = sharingInfoMapper.selectSharing();
                count = sharingInfoMapper.countSharing();
                jsonStr = gson.toJson(tools);
            }
        }
        if (toolsInfo.select_type == "车辆ID"){
            tools = sharingInfoMapper.selectById(toolsInfo)
            count = sharingInfoMapper.countById(toolsInfo);
            jsonStr = gson.toJson(tools);
            if (jsonStr == "[]"){
                tools = sharingInfoMapper.selectSharing();
                count = sharingInfoMapper.countSharing();
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

    int enable(ToolsInfo toolsInfo){
        return sharingInfoMapper.enable(toolsInfo)
    }
}
