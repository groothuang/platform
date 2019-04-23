package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.OrderInfo
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.UserInfo
import com.platform.dao.mapper.OrderInfoMapper
import com.platform.dao.mapper.SharingInfoMapper
import com.platform.dao.mapper.ToolsInfoMapper
import com.platform.dao.mapper.UserInfoMapper
import com.platform.service.OrderInfoService
import com.platform.service.SharingInfoService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.text.SimpleDateFormat

@Service
class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    OrderInfoMapper orderInfoMapper
    @Autowired
    UserInfoMapper userInfoMapper
    @Autowired
    ToolsInfoMapper toolsInfoMapper

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

    int countAll(){
        return orderInfoMapper.countAll();
    }

    int insert(OrderInfo orderInfo){
        //判断输入订单ID是否存在
        if(orderInfo.user_id != '' || orderInfo.user_id != null){
            UserInfo user
            try{
                user = userInfoMapper.findById(orderInfo.user_id)
            }catch (DataAccessException e) {
                return 0
            }
            if (user == null){
                return 0
            }else {
                orderInfo.user_name = user.user_name
            }
        }else {
            return 0
        }
        //判断输入车辆ID是否存在
        if(orderInfo.car_id != '' || orderInfo.car_id != null){
            ToolsInfo tools
            try{
                tools = toolsInfoMapper.findById(orderInfo.car_id)
            }catch (DataAccessException e) {
                return 0
            }
            if (tools == null){
                return 0
            }else {
                orderInfo.car_name = tools.car_name
                orderInfo.car_price = tools.car_price
                orderInfo.car_img = tools.car_img
            }
        }else {
            return 0
        }
        def currentDay = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        orderInfo.order_id = currentDay;
        orderInfo.create_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        orderInfo.order_status = "新增";  //新增；作废；处理中
        orderInfo.enable_flag = "false";
        Gson gson = new Gson();
        println(gson.toJson(orderInfo));
        return orderInfoMapper.insert(orderInfo);
    }

    int update(OrderInfo orderInfo){
        //判断输入订单ID是否存在
        if(orderInfo.user_id != '' || orderInfo.user_id != null){
            UserInfo user
            try{
                user = userInfoMapper.findById(orderInfo.user_id)
            }catch (DataAccessException e) {
                return 0
            }
            if (user == null){
                return 0
            }else {
                orderInfo.user_name = user.user_name
            }
        }else {
            return 0
        }
        //判断输入车辆ID是否存在
        if(orderInfo.car_id != '' || orderInfo.car_id != null){
            ToolsInfo tools
            try{
                tools = toolsInfoMapper.findById(orderInfo.car_id)
            }catch (DataAccessException e) {
                return 0
            }
            if (tools == null){
                return 0
            }else {
                orderInfo.car_name = tools.car_name
                orderInfo.car_price = tools.car_price
                orderInfo.car_img = tools.car_img
            }
        }else {
            return 0
        }
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
        List<OrderInfo> list;
        String jsonStr, count;

        println("输入打印:"+gson.toJson(orderInfo))

        if(orderInfo.select_type == "" || orderInfo.select_type == null){
            list = orderInfoMapper.selectById(orderInfo)
            count = orderInfoMapper.countById(orderInfo);
            jsonStr = gson.toJson(list);
            if (jsonStr == "[]"){
                list = orderInfoMapper.selectByUser(orderInfo)
                count = orderInfoMapper.countByUser(orderInfo);
                jsonStr = gson.toJson(list);
                if (jsonStr == "[]"){
                    list = orderInfoMapper.selectByCar(orderInfo);
                    count = orderInfoMapper.countByCar(orderInfo);
                    jsonStr = gson.toJson(list);
                }
            }
            if (jsonStr == "[]"){
                list = orderInfoMapper.selectAll();
                count = orderInfoMapper.countAll();
                jsonStr = gson.toJson(list);
            }
        }
        if (orderInfo.select_type == "订单号"){
            list = orderInfoMapper.selectById(orderInfo)
            count = orderInfoMapper.countById(orderInfo);
            jsonStr = gson.toJson(list);
            if (jsonStr == "[]"){
                list = orderInfoMapper.selectAll();
                count = orderInfoMapper.countAll();
                jsonStr = gson.toJson(list);
            }
        }
        if (orderInfo.select_type == "用户ID"){
            list = orderInfoMapper.selectByUser(orderInfo)
            count = orderInfoMapper.countByUser(orderInfo);
            jsonStr = gson.toJson(list);
            if (jsonStr == "[]"){
                list = orderInfoMapper.selectAll();
                count = orderInfoMapper.countAll();
                jsonStr = gson.toJson(list);
            }
        }
        if (orderInfo.select_type == "车辆ID"){
            list = orderInfoMapper.selectByCar(orderInfo)
            count = orderInfoMapper.countByCar(orderInfo);
            jsonStr = gson.toJson(list);
            if (jsonStr == "[]"){
                list = orderInfoMapper.selectAll();
                count = orderInfoMapper.countAll();
                jsonStr = gson.toJson(list);
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
        if (orderInfo.enable_flag == "true"){
            orderInfo.order_status = "处理中"
        }else {
            orderInfo.order_status = "已完成"
        }
        return orderInfoMapper.enable(orderInfo)
    }
}
