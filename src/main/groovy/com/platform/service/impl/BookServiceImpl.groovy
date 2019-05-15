package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.OrderInfo
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.UserInfo
import com.platform.dao.mapper.OrderInfoMapper
import com.platform.dao.mapper.ToolsInfoMapper
import com.platform.dao.mapper.UserInfoMapper
import com.platform.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat

@Service
class BookServiceImpl implements BookService{

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
        orderInfo.start_site = msgInfo.start_site
        orderInfo.return_site = msgInfo.return_site
        orderInfo.self_driving = msgInfo.self_driving
        return orderInfo
    }

    List<ToolsInfo> selectTools(){
        return toolsInfoMapper.selectAll()
    }

    String addOrder(String user_name, String car_id, OrderInfo orderInfo){
        OrderInfo d_orderInfo = orderInfoMapper.findByUser(user_name)
        String order_id
        if (d_orderInfo == null){
            UserInfo userInfo = userInfoMapper.findByName(user_name);
            ToolsInfo toolsInfo = toolsInfoMapper.findById(car_id);
            orderInfo.order_id =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            orderInfo.user_id = userInfo.user_id
            orderInfo.user_name = userInfo.user_name
            orderInfo.car_id = toolsInfo.car_id
            orderInfo.car_name = toolsInfo.car_name
            orderInfo.car_img = toolsInfo.car_img
            orderInfo.car_price = toolsInfo.car_price
            orderInfo.create_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            orderInfo.order_status = "新增";  //新增；作废；处理中
            orderInfo.enable_flag = "false";
            Gson gson = new Gson();
            println(gson.toJson(orderInfo));
            orderInfoMapper.insert(orderInfo);
            order_id = orderInfo.order_id
        }else {
            order_id = d_orderInfo.order_id
        }
        return order_id
    }
}
