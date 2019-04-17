package com.platform.service

import com.platform.dao.domain.UserInfo

interface UserInfoService {
    int insert(UserInfo userInfo);
    
    String selectAll();

    String selectAdmin();

    int insertAdmin(UserInfo userInfo)

    int update(UserInfo userInfo);

    int delete(String user_id);

    int countAll();

    UserInfo findByUserId(String id);
}
