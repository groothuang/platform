package com.platform.service

import com.platform.dao.domain.UserInfo

interface UserInfoService {

    int insert(UserInfo userInfo);

    String selectAll();

    String selectAdmin();

    int insertUser(UserInfo userInfo)

    int update(UserInfo userInfo);

    int updateMsg(UserInfo userInfo);

    int enableAdmin(UserInfo userInfo);

    int delete(String user_id);

    int countAll();

    UserInfo findById(String id);

    UserInfo findByName(String name);
}
