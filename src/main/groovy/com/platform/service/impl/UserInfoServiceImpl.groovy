package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.User
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
        try {
            def currentDay = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
            userInfo.user_id = "U"+currentDay;
            return userInfoMapper.insert(userInfo)
        }catch (DataAccessException e) {
            println("数据库异常！")
            return 0
        }
    }

    String selectAll(){
        try{
            List<User> users= userInfoMapper.selectAll();
            Gson gson = new Gson();
            String jsonStr = gson.toJson(users);
            JSONObject jsonObject = new JSONObject();  //创建Json对象
            jsonObject.put("code", 0);         //设置Json对象的属性
            jsonObject.put("msg", "");
            jsonObject.put("count", userInfoMapper.countAll());
            String m = jsonObject.toString();
            String result = "{"+m.substring(1,m.length()-1)+",\"data\":"+jsonStr+"}"
            return result
        }catch (DataAccessException e) {
            println("数据库异常！")
        }
    }

    int update(UserInfo userInfo){
        try{
            return  userInfoMapper.update(userInfo)
        }catch (DataAccessException e) {
            println("数据库异常！")
        }
    }

    int delete(String user_id){
        try {
            List<String> user_ids = java.util.Arrays.asList(user_id.split(","));
            return userInfoMapper.delete(user_ids)
        } catch (DataAccessException e) {
            println("数据库异常！")
        }
    }

    int countAll(){
        try {
            return userInfoMapper.countAll()
        } catch (DataAccessException e) {
            println("数据库异常！")
        }
    }

    UserInfo findByUserName(String username){
        try {
            return userInfoMapper.findByUserName(username)
        } catch (DataAccessException e) {
            println("数据库异常！")
        }
    }
}
