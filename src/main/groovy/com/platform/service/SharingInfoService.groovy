package com.platform.service

import com.platform.dao.domain.ToolsInfo
import org.json.JSONObject
import org.springframework.web.multipart.MultipartFile

interface SharingInfoService {

    int countSharing();

    String selectSharing();

    String search(ToolsInfo toolsInfo);

    ToolsInfo findByName(String name);

    ToolsInfo findById(String id);

    int insert(ToolsInfo toolsInfo);

    int update(ToolsInfo toolsInfo);

    int delete(String id);

    Map<String,Object> uploadPic (MultipartFile file, String path);

    String uploadFile(MultipartFile file, String path);

    int enable(ToolsInfo toolsInfo);

}
