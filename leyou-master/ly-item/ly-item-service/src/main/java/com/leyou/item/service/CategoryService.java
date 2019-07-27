package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryService {

    //查询商品分查询
    List<Category> queryCategoryListByParentId(Long pid);
}
