package com.platform.service

import com.platform.dao.domain.ToolsInfo
import org.json.JSONObject

interface ToolsInfoService {

    int countAll();

    String selectAll();

    int insert(ToolsInfo toolsInfo);

    int update(ToolsInfo toolsInfo);

    int delete(String id);

    ToolsInfo findById(String id);
}
