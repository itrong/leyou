package com.leyou.item.service.impl;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.po.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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


    /**
     * 编辑品牌
     */
    @Override
    public void editCategory(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }


    /**
     * 根据品牌获得分类列表
     */
    @Override
    public List<Category> queryByBrandId(Long id) {
        return categoryMapper.queryByBrandId(id);
    }

    @Override
    public List<String> queryNameByIds(List<Long> ids) {
        return this.categoryMapper.selectByIdList(ids).stream().map(Category::getName).collect(Collectors.toList());
    }


    /**
     * 根据分类id集合查询分类名称
     */
    @Override
    public List<Category> queryCategoryByIds(List<Long> ids) {
        return categoryMapper.selectByIdList(ids);
    }

    /**
     * 根据3级分类id，查询1~3级的分类
     */
    @Override
    public List<Category> queryAllByCid3(Long id) {
        Category c3 = this.categoryMapper.selectByPrimaryKey(id);
        Category c2 = this.categoryMapper.selectByPrimaryKey(c3.getParentId());
        Category c1 = this.categoryMapper.selectByPrimaryKey(c2.getParentId());
        return Arrays.asList(c1,c2,c3);
    }

}
