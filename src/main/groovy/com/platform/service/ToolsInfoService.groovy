package com.platform.service

import com.platform.dao.domain.ToolsInfo
import org.json.JSONObject
import org.springframework.web.multipart.MultipartFile

interface ToolsInfoService {

    int countAll();

    String selectAll();

    String searchTools(ToolsInfo toolsInfo);

    ToolsInfo findByName(String name);

    ToolsInfo findById(String id);

    int insert(ToolsInfo toolsInfo);

    int update(ToolsInfo toolsInfo);

    int delete(String id);

    Map<String,Object> uploadPic (MultipartFile file, String path);

    String uploadFile(MultipartFile file, String path);

}
