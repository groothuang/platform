package com.platform.service

import com.platform.dao.domain.UserInfo

interface LoginService {
    String loginCheck(UserInfo userInfo);

    String registerCheck(UserInfo userInfo);
}