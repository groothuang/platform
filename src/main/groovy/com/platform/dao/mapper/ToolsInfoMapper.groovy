package com.platform.dao.mapper

import com.platform.dao.domain.ToolsInfo


interface ToolsInfoMapper {

    int countAll();

    int countById(ToolsInfo toolsInfo);

    int countByName(ToolsInfo toolsInfo);

    List<ToolsInfo> selectAll();

    List<ToolsInfo> selectById(ToolsInfo toolsInfo);

    List<ToolsInfo> selectByName(ToolsInfo toolsInfo);

    int insert(ToolsInfo toolsInfo);

    int update(ToolsInfo toolsInfo);

    int delete(List list);

    ToolsInfo findById(String id);

    ToolsInfo findByName(String name);
}