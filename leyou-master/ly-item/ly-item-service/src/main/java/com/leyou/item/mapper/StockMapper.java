package com.leyou.item.mapper;

import com.leyou.item.po.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface StockMapper extends Mapper<Stock>, SelectByIdListMapper<Stock,Long> {
    @Update("UPDATE tb_stock SET stock = stock - #{num} WHERE sku_id = #{id} AND stock >= #{num}")
    int decreaseStock(@Param("id") Long id, @Param("num") Integer num);
}
