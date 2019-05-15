package com.platform.service

import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.OrderInfo
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.UserInfo

interface UserService {

    String loginCheck(UserInfo userInfo);

    String registerCheck(UserInfo userInfo);

    int updatePassword(UserInfo userInfo)

    int forget(UserInfo userInfo)

    int updateUser(UserInfo userInfo)

}