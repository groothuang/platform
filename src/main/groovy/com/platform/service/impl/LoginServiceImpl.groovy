package com.platform.service.impl

import com.platform.dao.domain.UserInfo
import com.platform.dao.mapper.UserInfoMapper
import com.platform.service.LoginService
import org.springframework.beans.factory.annotation.Autowired

class LoginServiceImpl implements LoginService{

    @Autowired
    UserInfoMapper userInfoMapper

    String loginCheck(UserInfo userInfo){
        UserInfo d_user = userInfoMapper.findByName(userInfo.user_name);
        if (d_user.user_name.equals(userInfo.user_name)){

        }
    }
}
