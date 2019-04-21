package com.platform.dao.mapper;

import com.platform.dao.domain.UserInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;


interface UserInfoMapper {

    int insert(UserInfo userInfo);

    int insertUser(UserInfo userInfo);

    int update(UserInfo userInfo);

    int updateMsg(UserInfo userInfo);

    int enableAdmin(UserInfo userInfo);

    int delete(List list);

    List<UserInfo> selectAll();

    List<UserInfo> selectAdmin();

    int countAll();

    UserInfo findById(String id);

    UserInfo findByName(String name);
}