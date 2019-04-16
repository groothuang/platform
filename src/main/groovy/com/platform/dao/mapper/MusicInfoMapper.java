package com.platform.dao.mapper;

import com.platform.dao.domain.MusicInfo;
import com.platform.dao.domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MusicInfoMapper {
    @ResultMap("BaseResultMap")
    @Select("select * from music_info")
    List<MusicInfo> selectAll(MusicInfo musicInfo);

    @Insert("insert into user(username) "+ "values(#{username})")
    int insert(UserInfo userInfo);
}