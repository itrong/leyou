package com.leyou.item.mapper;

import com.leyou.item.po.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {

    /**
     * 新增商品分类和品牌中间表数据
     *
     * @param cid 商品分类id
     * @param bid 品牌id
     * @return
     */
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    void insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);


    /**
     * 删除原有中间表信息
     *
     * @param id
     */
    @Delete("DELETE FROM tb_category_brand WHERE brand_id = #{id}")
    void deleteCategoryBrandByBrandId(Long id);


    /**
     * 根据分类id查品牌
     */
    @Select("SELECT * from tb_brand where id in (select brand_id from tb_category_brand where category_id = #{id})")
    List<Brand> queryByCategoryId(Long id);
}
