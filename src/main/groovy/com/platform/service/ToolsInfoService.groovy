package com.platform.service

import com.platform.dao.domain.ToolsInfo
import org.json.JSONObject
import org.springframework.web.multipart.MultipartFile

interface ToolsInfoService {

    int countAll();

    String selectAll();

    int insert(ToolsInfo toolsInfo);

    int update(ToolsInfo toolsInfo);

    int delete(String id);

    ToolsInfo findById(String id);

    Map<String,Object> uploadPic (MultipartFile file, String path);

    String uploadFile(MultipartFile file, String path);

    ToolsInfo findByName(String name);
}
