package com.leyou.item.service.impl;

import com.leyou.item.mapper.SpecificationMapper;
import com.leyou.item.po.Specification;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 规格参数
 * @author lujianrong
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    /**
     * 根据分类id查询规格参数
     */
    @Override
    public Specification querySpecificationByCategoryId(Long id) {
        return specificationMapper.selectByPrimaryKey(id);
    }


    /**
     * 编辑规则参数
     */
    @Override
    public void editSpecification(Specification spec) {

        specificationMapper.updateByPrimaryKey(spec);
    }


    /**
     * 保存规则参数
     */
    @Override
    public void saveSpecification(Specification spec) {
        specificationMapper.insert(spec);
    }
}
