package com.platform.service

import com.platform.dao.domain.UserInfo

interface UserInfoService {
    void insert(UserInfo userInfo);
    
    String selectAll();

    int update(UserInfo userInfo);

    int delete(String userName);

    int countAll();

    UserInfo findByUserName(String userName);
}
