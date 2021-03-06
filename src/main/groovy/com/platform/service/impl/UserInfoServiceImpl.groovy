package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.UserInfo
import com.platform.dao.mapper.UserInfoMapper
import com.platform.service.UserInfoService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat

@Service
class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper

    public int insert(UserInfo userInfo){
        UserInfo d_user
        try {
            d_user = userInfoMapper.findByName(userInfo.user_name);
        } catch (DataAccessException e) {
            return 0
        }
        if (d_user == null){
            if(userInfo.password == "" || userInfo.password == null){
                return 0
            }
            def currentDay = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
            def currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            userInfo.user_id = "U"+currentDay;
            userInfo.create_time = currentTime;
            userInfo.role = "2";
            userInfo.enable_flag = "true";
            return userInfoMapper.insert(userInfo)
        }else {
            return 0
        }
    }

    int insertUser(UserInfo userInfo){
        def currentDay = new SimpleDateFormat("ddHHmmss").format(new Date());
        userInfo.user_id = "A"+currentDay;
        def currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        userInfo.create_time = currentTime;
        userInfo.role = "1";
        userInfo.enable_flag = "true";
        return userInfoMapper.insertUser(userInfo)
    }

    String selectAll(){
        List<UserInfo> users= userInfoMapper.selectAll();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(users);
        JSONObject jsonObject = new JSONObject();  //创建Json对象
        jsonObject.put("code", 0);         //设置Json对象的属性
        jsonObject.put("msg", "");
        jsonObject.put("count", userInfoMapper.countAll());
        String str = jsonObject.toString();
        String result = "{"+str.substring(1,str.length()-1)+",\"data\":"+jsonStr+"}"
        return result
    }

    int update(UserInfo userInfo){
        if(userInfo.password == "" || userInfo.password == null){
            return 0
        }
        return  userInfoMapper.update(userInfo)
    }

    int updateMsg(UserInfo userInfo){
        return  userInfoMapper.updateMsg(userInfo)
    }

    int enableAdmin(UserInfo userInfo){
        return userInfoMapper.enableAdmin(userInfo)
    }

    int delete(String user_id){
        List<String> user_ids = java.util.Arrays.asList(user_id.split(","));
        return userInfoMapper.delete(user_ids)
    }

    int countAll(){
        return userInfoMapper.countAll()
    }

    UserInfo findById(String id){
        return userInfoMapper.findById(id)
    }

    UserInfo findByName(String name){
        return userInfoMapper.findByName(name)
    }

    String selectAdmin(){
        List<UserInfo> admins= userInfoMapper.selectAdmin();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(admins);
        JSONObject jsonObject = new JSONObject();  //创建Json对象
        jsonObject.put("code", 0);         //设置Json对象的属性
        jsonObject.put("msg", "");
        jsonObject.put("count", userInfoMapper.countAll());
        String str = jsonObject.toString();
        String result = "{"+str.substring(1,str.length()-1)+",\"data\":"+jsonStr+"}"
        return result
    }
}
