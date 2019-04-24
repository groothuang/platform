package com.platform.service

import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.UserInfo

interface PostService {
    String loginCheck(UserInfo userInfo);

    String registerCheck(UserInfo userInfo);

    MsgInfo postMsg(MsgInfo msgInfo)

    int updatePassword(UserInfo userInfo)

    int forget(UserInfo userInfo)

    int updateUser(UserInfo userInfo)

    List<ToolsInfo> selectTools()

    Map getUrlParams(String url)

    ToolsInfo findById(String id);

    UserInfo findByUser(String name)

    String addOrder(String name,String id,MsgInfo msgInfo)
}