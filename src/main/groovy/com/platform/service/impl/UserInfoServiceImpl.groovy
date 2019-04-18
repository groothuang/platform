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
        if("".equals(userInfo.user_name) || userInfo.user_name == null){
            return 0
        }
        UserInfo d_user = userInfoMapper.findByName(userInfo.user_name)
        if(userInfo.user_name.equals(d_user.user_name) || d_user.user_name == userInfo.user_name){
            return 0
        }
        def currentDay = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        def currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        userInfo.user_id = "U"+currentDay;
        userInfo.create_time = currentTime;
        userInfo.role = "2";
        userInfo.enable_flag = "true";
        return userInfoMapper.insert(userInfo)
    }

    int insertAdmin(UserInfo userInfo){
        def currentDay = new SimpleDateFormat("ddHHmmss").format(new Date());
        userInfo.user_id = "A"+currentDay;
        def currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        userInfo.create_time = currentTime;
        userInfo.role = "1";
        userInfo.enable_flag = "true";
        return userInfoMapper.insertAdmin(userInfo)
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
        return  userInfoMapper.update(userInfo)
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
