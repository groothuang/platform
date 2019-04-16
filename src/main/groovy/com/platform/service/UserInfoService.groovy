package com.platform.service

import com.platform.dao.domain.UserInfo

interface UserInfoService {
    int insert(UserInfo userInfo);
    
    String selectAll();

    int update(UserInfo userInfo);

    int delete(String user_id);

    int countAll();

    UserInfo findByUserName(String userName);
}
