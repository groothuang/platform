package com.platform.service

import com.platform.dao.domain.OrderInfo
import org.springframework.web.multipart.MultipartFile

interface OrderInfoService {

    int countAll();

    String selectAll();

    String search(OrderInfo orderInfo);

    OrderInfo findById(String id);

    int insert(OrderInfo orderInfo);

    int update(OrderInfo orderInfo);

    int delete(String id);

    Map<String,Object> uploadPic (MultipartFile file, String path);

    String uploadFile(MultipartFile file, String path);

    int enable(OrderInfo orderInfo);

}
