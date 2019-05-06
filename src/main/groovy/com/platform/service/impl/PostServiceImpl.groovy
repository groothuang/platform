package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.OrderInfo
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.UserInfo
import com.platform.dao.mapper.OrderInfoMapper
import com.platform.dao.mapper.ToolsInfoMapper
import com.platform.dao.mapper.UserInfoMapper
import com.platform.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service

import java.text.ParseException
import java.text.SimpleDateFormat

@Service
class PostServiceImpl implements PostService{

    @Autowired
    UserInfoMapper userInfoMapper
    @Autowired
    ToolsInfoMapper toolsInfoMapper
    @Autowired
    OrderInfoMapper orderInfoMapper

    OrderInfo postMsg(MsgInfo msgInfo){
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat s2 = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss", Locale.ENGLISH);
        String start_date = msgInfo.start_date+" "+msgInfo.start_date_h+":"+msgInfo.start_date_m+":00 PM"
        String return_date = msgInfo.return_date+" "+msgInfo.return_date_h+":"+msgInfo.return_date_m+":00 PM"
        OrderInfo orderInfo = new OrderInfo()
        orderInfo.start_date = s1.format(s2.parse(start_date))
        orderInfo.return_date = s1.format(s2.parse(return_date))
        return orderInfo
    }

    int updatePassword(UserInfo userInfo){
        UserInfo d_user = userInfoMapper.findByName(userInfo.user_name)
        if (userInfo.password == d_user.password){
            userInfo.password = userInfo.repassword
            return userInfoMapper.updatePassword(userInfo)
        }else {
            return 0
        }
    }

    int forget(UserInfo userInfo) {
        UserInfo d_user = userInfoMapper.findByName(userInfo.user_name)
        if (userInfo.tel == d_user.tel) {
            userInfo.password = userInfo.password
            return userInfoMapper.updatePassword(userInfo)
        } else {
            return 0
        }
    }

    int updateUser(UserInfo userInfo){
        return  userInfoMapper.updateUser(userInfo)
    }

    List<ToolsInfo> selectTools(){
        return toolsInfoMapper.selectAll()
    }

    Map getUrlParams(String url){
        Map<String,Object> map = new HashMap<>();
        url = url.replace("?",";");
        if (!url.contains(";")){
            return map;
        }
        if (url.split(";").length > 0){
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr){
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key,value);
            }
            return  map;

        }else{
            return map;
        }
    }

    ToolsInfo findById(String id){
        return toolsInfoMapper.findById(id)
    }

    UserInfo findByUser(String name){
        return userInfoMapper.findByName(name)
    }

    String addOrder(String name,String id,MsgInfo msgInfo){
        UserInfo userInfo = findByUser(name);
        ToolsInfo toolsInfo = findById(id);
        OrderInfo orderInfo = new OrderInfo();
        def currentDay = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        orderInfo.order_id = currentDay;
        orderInfo.user_id = userInfo.user_id
        orderInfo.user_name = userInfo.user_name
        orderInfo.car_id = toolsInfo.car_id
        orderInfo.car_name = toolsInfo.car_name
        orderInfo.car_img = toolsInfo.car_img
        orderInfo.car_price = toolsInfo.car_price
        orderInfo.start_date = msgInfo.start_date
        orderInfo.start_site = msgInfo.start_site
        orderInfo.return_date = msgInfo.return_date
        orderInfo.return_site = msgInfo.return_site
        orderInfo.self_driving = msgInfo.self_driving

        orderInfo.create_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        orderInfo.order_status = "新增";  //新增；作废；处理中
        orderInfo.enable_flag = "false";
        Gson gson = new Gson();
        println(gson.toJson(orderInfo));
        orderInfoMapper.insert(orderInfo)
        return orderInfo.order_id
    }
}
