package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.OrderInfo
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.UserInfo
import com.platform.dao.mapper.OrderInfoMapper
import com.platform.dao.mapper.ToolsInfoMapper
import com.platform.dao.mapper.UserInfoMapper
import com.platform.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat

@Service
class UserServiceImpl implements UserService{

    @Autowired
    UserInfoMapper userInfoMapper
    @Autowired
    ToolsInfoMapper toolsInfoMapper
    @Autowired
    OrderInfoMapper orderInfoMapper

    String loginCheck(UserInfo userInfo){
        UserInfo d_user
        String flag = "none"
        try {
            d_user = userInfoMapper.findByName(userInfo.user_name);
        } catch (DataAccessException e) {
            flag = "none"
        }
        if(d_user == null){
            flag = "none"
        }else {
            if (d_user.role == "0"){
                if (d_user.user_name == userInfo.user_name && d_user.password == userInfo.password){
                    flag = "0"
                }else {
                    flag = "wrong"
                }
            }else if (d_user.role == "1"){
                if (d_user.password != userInfo.password){
                    flag = "wrong"
                }
                if (d_user.user_name == userInfo.user_name && d_user.password == userInfo.password){
                    flag = '1'
                }
            }else if (d_user.role == "2"){
                if (d_user.password != userInfo.password){
                    flag = "wrong"
                }
                if (d_user.user_name == userInfo.user_name && d_user.password == userInfo.password){
                    flag = '2'
                }
            }
        }
        return flag
    }

    String registerCheck(UserInfo userInfo){
        UserInfo d_user
        String flag = "wrong"
        try {
            d_user = userInfoMapper.findByName(userInfo.user_name);
        } catch (DataAccessException e) {
            flag = "wrong"
        }
        if (d_user == null){
            if(userInfo.password == userInfo.repassword){
                def currentDay = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
                def currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                userInfo.user_id = "U"+currentDay;
                userInfo.create_time = currentTime;
                userInfo.role = "2";
                userInfo.enable_flag = "true";
                userInfoMapper.insertUser(userInfo)
                flag = '2'
            }else {
                flag = "wrong"
            }
        }else{
            flag = "exist"
        }
        return flag
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
        return userInfoMapper.updateUser(userInfo)
    }

}
