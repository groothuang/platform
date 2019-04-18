package com.platform.dao.mapper

import com.platform.dao.domain.ToolsInfo


interface ToolsInfoMapper {

    int countAll();

    List<ToolsInfo> selectAll();

    int insert(ToolsInfo toolsInfo);

    int update(ToolsInfo toolsInfo);

    int delete(List list);

    ToolsInfo findById(String id);
}