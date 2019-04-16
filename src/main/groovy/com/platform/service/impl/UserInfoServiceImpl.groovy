package com.platform.service.impl

import com.google.gson.Gson
import com.platform.dao.domain.User
import com.platform.dao.domain.UserInfo
import com.platform.dao.mapper.UserInfoMapper
import com.platform.service.UserInfoService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper

    public void insert(UserInfo userInfo){
        userInfoMapper.insert(userInfo)
    }

    String selectAll(){
        List<User> users= userInfoMapper.selectAll();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(users);
        JSONObject jsonObject = new JSONObject();  //����Json����
        jsonObject.put("code", 0);         //����Json���������
        jsonObject.put("msg", "");
        jsonObject.put("count", userInfoMapper.countAll());
        String m = jsonObject.toString();
        String result = "{"+m.substring(1,m.length()-1)+",\"data\":"+jsonStr+"}"
        return result
    }

    int update(UserInfo userInfo){
        return  userInfoMapper.update(userInfo)
    }

    int delete(String username){
        return userInfoMapper.delete(username)
    }

    int countAll(){
        return userInfoMapper.countAll()
    }

    UserInfo findByUserName(String username){

    }
}
