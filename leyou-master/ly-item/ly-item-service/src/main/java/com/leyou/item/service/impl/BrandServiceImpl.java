package com.leyou.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.po.Brand;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    /**
     * 品牌列表分页查询
     */
    @Override
    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%" + key + "%")
                    .orEqualTo("letter", key);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);

    }


    /**
     * 保存品牌信息
     */
    @Override
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {

        //insertSelective编译sql语句时只插入有值的字段
        brandMapper.insertSelective(brand);
        //维护品牌商品中间表
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }


    /**
     * 根据id查询品牌信息
     */
    @Override
    public Brand queryByBid(Long id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }


    /**
     * 修改品牌信息
     */
    @Override
    @Transactional
    public void editBrand(Brand brand, List<Long> categories) {

        //insertSelective编译sql语句时只插入有值的字段
        brandMapper.updateByPrimaryKey(brand);

        //维护品牌商品中间表
        brandMapper.deleteCategoryBrandByBrandId(brand.getId());
        for (Long cid : categories) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }

    }


    /**
     * 删除品牌
     */
    @Override
    @Transactional
    public void deleteBrand(Long id) {
        brandMapper.deleteByPrimaryKey(id);
        brandMapper.deleteCategoryBrandByBrandId(id);
    }


    /**
     * 根据分类id查品牌
     */
    @Override
    public List<Brand> queryByCategoryId(Long id) {
        return brandMapper.queryByCategoryId(id);
    }


}
