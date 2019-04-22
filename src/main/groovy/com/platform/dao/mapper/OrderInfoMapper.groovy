package com.platform.dao.mapper

import com.platform.dao.domain.OrderInfo


interface OrderInfoMapper {

    int countAll();

    int countById(OrderInfo orderInfo);

    int countByName(OrderInfo orderInfo);

    List<OrderInfo> selectAll();

    List<OrderInfo> selectById(OrderInfo orderInfo);

    List<OrderInfo> selectByName(OrderInfo orderInfo);

    int insert(OrderInfo orderInfo);

    int update(OrderInfo orderInfo);

    int delete(List list);

    OrderInfo findById(String id);

    OrderInfo findByName(String name);

    int enable(OrderInfo orderInfo);
}