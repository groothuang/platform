package com.platform.dao.mapper;

import com.platform.dao.domain.UserInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;


public interface UserInfoMapper {
    public int insert(UserInfo userInfo);

    public int update(UserInfo userInfo);

    public int delete(List list);

    public List<UserInfo> selectAll();

    public int countAll();

    public UserInfo findByUserName(String userName);
}