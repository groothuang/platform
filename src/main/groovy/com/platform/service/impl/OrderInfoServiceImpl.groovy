package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.OrderInfo
import com.platform.dao.mapper.OrderInfoMapper
import com.platform.dao.mapper.SharingInfoMapper
import com.platform.service.OrderInfoService
import com.platform.service.SharingInfoService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.text.SimpleDateFormat

@Service
class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    OrderInfoMapper orderInfoMapper 

    String selectAll(){
        List<OrderInfo> orderInfo = orderInfoMapper.selectAll();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(orderInfo);
//        println(jsonStr)
        JSONObject jsonObject = new JSONObject();  //创建Json对象
        jsonObject.put("code", 0);         //设置Json对象的属性
        jsonObject.put("msg", "");
        jsonObject.put("count", orderInfoMapper.countAll());
        String str = jsonObject.toString();
        String result = "{"+str.substring(1,str.length()-1)+",\"data\":"+jsonStr+"}"
        return result
    }

    OrderInfo findById(String id){
        return orderInfoMapper.findById(id)
    }

    OrderInfo findByName(String name){
        return  orderInfoMapper.findByName(name)
    }

    int countAll(){
        return orderInfoMapper.countAll();
    }

    int insert(OrderInfo orderInfo){
        def currentDay = new SimpleDateFormat("HHmmss").format(new Date());
        orderInfo.car_id = "C"+currentDay;
        orderInfo.create_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        orderInfo.car_state = "空闲";
        orderInfo.enable_flag = "false";
        orderInfo.car_source = "用户";
        Gson gson = new Gson();
        println(gson.toJson(orderInfo));
        return orderInfoMapper.insert(orderInfo);
    }

    int update(OrderInfo orderInfo){
        return orderInfoMapper.update(orderInfo);
    }

    int delete(String id){
        List<String> ids = java.util.Arrays.asList(id.split(","));
        return orderInfoMapper.delete(ids);
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

    String search(OrderInfo orderInfo) {
        Gson gson = new Gson();
        List<OrderInfo> tools;
        String jsonStr, count;

        println("输入打印:"+gson.toJson(orderInfo))

        if(orderInfo.select_type == "" || orderInfo.select_type == null){
            tools = orderInfoMapper.selectByName(orderInfo);
            count = orderInfoMapper.countByName(orderInfo);
            jsonStr = gson.toJson(tools);
            if (jsonStr == "[]"){
                tools = orderInfoMapper.selectById(orderInfo)
                count = orderInfoMapper.countById(orderInfo);
                jsonStr = gson.toJson(tools);
                if (jsonStr == "[]"){
                    tools = orderInfoMapper.selectByName(orderInfo);
                    count = orderInfoMapper.countByName(orderInfo);
                    jsonStr = gson.toJson(tools);
                }
            }
            if (jsonStr == "[]"){
                tools = orderInfoMapper.selectAll();
                count = orderInfoMapper.countAll();
                jsonStr = gson.toJson(tools);
            }
        }
        if (orderInfo.select_type == "车辆名称"){
            tools = orderInfoMapper.selectByName(orderInfo);
            count = orderInfoMapper.countByName(orderInfo);
            jsonStr = gson.toJson(tools);
            if (jsonStr == "[]"){
                tools = orderInfoMapper.selectAll();
                count = orderInfoMapper.countAll();
                jsonStr = gson.toJson(tools);
            }
        }
        if (orderInfo.select_type == "车辆ID"){
            tools = orderInfoMapper.selectById(orderInfo)
            count = orderInfoMapper.countById(orderInfo);
            jsonStr = gson.toJson(tools);
            if (jsonStr == "[]"){
                tools = orderInfoMapper.selectAll();
                count = orderInfoMapper.countAll();
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

    int enable(OrderInfo orderInfo){
        return orderInfoMapper.enable(orderInfo)
    }
}
