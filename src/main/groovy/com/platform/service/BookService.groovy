package com.platform.service

import com.platform.dao.domain.MsgInfo
import com.platform.dao.domain.OrderInfo
import com.platform.dao.domain.ToolsInfo
import com.platform.dao.domain.UserInfo

interface BookService {

    OrderInfo postMsg(MsgInfo msgInfo)

    List<ToolsInfo> selectTools()

    String addOrder(String user_name, String car_id, OrderInfo orderInfo)
}