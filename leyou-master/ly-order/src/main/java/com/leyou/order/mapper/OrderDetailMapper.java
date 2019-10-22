package com.leyou.order.mapper;

import com.leyou.order.pojo.OrderDetail;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.BaseMapper;


/**
 * @author lujianrong
 */
public interface OrderDetailMapper  extends BaseMapper<OrderDetail>, InsertListMapper<OrderDetail> {
}
