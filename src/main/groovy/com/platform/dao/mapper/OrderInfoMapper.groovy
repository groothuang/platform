package com.platform.dao.mapper

import com.platform.dao.domain.OrderInfo


interface OrderInfoMapper {

    int countAll();

    int countById(OrderInfo orderInfo);

    int countByUser(OrderInfo orderInfo);

    int countByCar(OrderInfo orderInfo);

    List<OrderInfo> selectAll();

    List<OrderInfo> selectById(OrderInfo orderInfo);

    List<OrderInfo> selectByUser(OrderInfo orderInfo);

    List<OrderInfo> selectByCar(OrderInfo orderInfo);

    int insert(OrderInfo orderInfo);

    int update(OrderInfo orderInfo);

    int delete(List list);

    OrderInfo findById(String id);

    OrderInfo findByUser(String id);

    OrderInfo findByCar(String id);

    int enable(OrderInfo orderInfo);
}