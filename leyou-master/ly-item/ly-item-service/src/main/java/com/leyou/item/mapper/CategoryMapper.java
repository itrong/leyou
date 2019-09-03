package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {

    @Select("SELECT * FROM tb_category WHERE id IN( SELECT category_id FROM \n" +
            "tb_category_brand WHERE brand_id = #{id} )")
    List<Category> queryByBrandId(Long id);
}
