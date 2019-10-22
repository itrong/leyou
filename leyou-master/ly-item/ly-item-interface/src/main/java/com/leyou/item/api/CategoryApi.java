package com.leyou.item.api;

import com.leyou.item.po.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("category")
public interface CategoryApi {


    /**
     * 根据父节点id查商品分类
     *
     * @param pid 父节点id
     */
    @GetMapping("/list")
    List<Category> queryCategoryListByParentId(@RequestParam(value = "pid", defaultValue = "0") Long pid);

    /**
     * 根据品牌获得分类列表
     *
     * @param id 品牌id
     */
    @GetMapping("bid")
    List<Category> queryByBrandId(@RequestParam(value = "id") Long id);

    /**
     * 根据分类id集合查询分类名称
     * @param ids
     */
    @GetMapping("names")
    List<String> queryNameByIds(@RequestParam("ids")List<Long> ids);

    /**
     * 根据分类id集合查询分类名称
     * @param ids
     * @return
     */
    @GetMapping("all")
    List<Category> queryCategoryByIds(@RequestParam("ids")List<Long> ids);

}
