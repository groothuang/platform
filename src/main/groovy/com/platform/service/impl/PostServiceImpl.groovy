package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.UserInfo
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

    String postMsg(MsgInfo msgInfo){
        Gson gson = new Gson();
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat s2 = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss", Locale.ENGLISH);
        String result = gson.toJson(msgInfo)
        println(result);
        String start_date = msgInfo.start_date+" "+msgInfo.start_date_h+":"+msgInfo.start_date_m+":00 PM"
        String return_date = msgInfo.return_date+" "+msgInfo.return_date_h+":"+msgInfo.return_date_m+":00 PM"
        msgInfo.start_date = s1.format(s2.parse(start_date))
        msgInfo.return_date = s1.format(s2.parse(return_date))
        println(msgInfo.start_date+"------"+msgInfo.return_date)
        result = gson.toJson(msgInfo)
        println(result);
        return result
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
}
