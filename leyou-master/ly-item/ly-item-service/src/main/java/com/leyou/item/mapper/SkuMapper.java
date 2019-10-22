package com.leyou.item.mapper;

import com.leyou.item.po.Sku;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface SkuMapper extends Mapper<Sku>, SelectByIdListMapper<Sku,Long> {
}
