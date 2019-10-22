package com.leyou.item.service;

import com.leyou.item.po.Category;

import java.util.List;

public interface CategoryService {

    //查询商品分查询
    List<Category> queryCategoryListByParentId(Long pid);

    //根据id查询
    List<Category> queryCategoryListById(Long id);

    //新增保存
    void saveCategory(Category category);

    //删除节点及其子节点
    void deleteById(Long id);

    //编辑品牌
    void editCategory(Category category);

    List<Category> queryByBrandId(Long id);

    List<String> queryNameByIds(List<Long> asList);

    List<Category> queryCategoryByIds(List<Long> ids);

    List<Category> queryAllByCid3(Long id);
}
