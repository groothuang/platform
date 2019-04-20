package com.platform.service.impl

import com.platform.dao.domain.UserInfo
import com.platform.dao.mapper.UserInfoMapper
import com.platform.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat

@Service
class LoginServiceImpl implements LoginService{

    @Autowired
    UserInfoMapper userInfoMapper

    String loginCheck(UserInfo userInfo){
        UserInfo d_user
        try {
            d_user = userInfoMapper.findByName(userInfo.user_name);
        } catch (DataAccessException e) {
            return "none"
        }
        if(d_user == null){
            return "none"
        }
        if (d_user.role == "0"){
            if (d_user.user_name == userInfo.user_name && d_user.password == userInfo.password){
                return 0
            }else {
                return "wrong"
            }
        }else if (d_user.role == "1"){
            if (d_user.password != userInfo.password){
                return "wrong"
            }
            if (d_user.user_name == userInfo.user_name && d_user.password == userInfo.password){
                return 1
            }
        }else if (d_user.role == "2"){
            if (d_user.password != userInfo.password){
                return "wrong"
            }
            if (d_user.user_name == userInfo.user_name && d_user.password == userInfo.password){
                return 2
            }
        }
    }

    String registerCheck(UserInfo userInfo){
        UserInfo d_user
        try {
            d_user = userInfoMapper.findByName(userInfo.user_name);
        } catch (DataAccessException e) {
            return "wrong"
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
                return 2
            }else {
                return "wrong"
            }
        }
        if (d_user.user_id != "" || d_user.user_id != null ){
            return  "exist"
        }
    }
}
