package com.platform.dao.mapper;

import com.platform.dao.domain.UserInfo;

import java.util.List;


public interface UserInfoMapper {
    public int insert(UserInfo userInfo);

    public int update(UserInfo userInfo);

    public int delete(String userName);

    public List<UserInfo> selectAll();

    public int countAll();

    public UserInfo findByUserName(String userName);
}