package com.leyou.item.mapper;

import com.leyou.item.po.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category, Long> {

    @Select("SELECT * FROM tb_category WHERE id IN( SELECT category_id FROM \n" +
            "tb_category_brand WHERE brand_id = #{id} )")
    List<Category> queryByBrandId(Long id);
}
