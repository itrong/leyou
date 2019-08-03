package com.leyou.item.service.impl;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.function.Consumer;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 根据父节点id查商品分类
     */
    @Override
    public List<Category> queryCategoryListByParentId(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return categoryMapper.select(record);
    }

    /**
     * 根据id查询
     */
    @Override
    public List<Category> queryCategoryListById(Long id) {
        Category record = new Category();
        record.setId(id);
        return categoryMapper.select(record);
    }

    /**
     * 新增保存
     */
    @Override
    @Transactional
    public void saveCategory(Category category) {
        //移除前端可能传入的id
        category.setId(null);
        //增加节点
        categoryMapper.insert(category);
        Category record = new Category();
        record.setId(category.getParentId());
        record.setIsParent(true);
        //把上级节点设置为父节点
        categoryMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 删除节点及其子节点
     */
    @Override
    @Transactional
    public void deleteById(Long id) {

        Category record = new Category();
        record.setParentId(id);
        //查询所有子节点
        List<Category> categories = categoryMapper.select(record);
        categories.forEach((category) -> {
            if (category.getIsParent()) {
                //如果是父节点，递归
                this.deleteById(category.getId());
            } else {
                //不是父节点，删除节点
                categoryMapper.deleteByPrimaryKey(category.getId());
            }
        });
        //删除所有子节点后删除本节点
        categoryMapper.deleteByPrimaryKey(id);
    }

    //编辑品牌
    @Override
    public void editCategory(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }


}
