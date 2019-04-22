package com.platform.service

import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.UserInfo

interface PostService {
    String loginCheck(UserInfo userInfo);

    String registerCheck(UserInfo userInfo);

    String postMsg(MsgInfo msgInfo)
}