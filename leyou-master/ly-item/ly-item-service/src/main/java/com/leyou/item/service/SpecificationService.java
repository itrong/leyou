package com.leyou.item.service;


import com.leyou.item.po.Specification;

/**
 * 规格参数
 * @author lujianrong
 */
public interface SpecificationService {


    /**
     * 根据分类id查询规格参数
     */
    Specification querySpecificationByCategoryId(Long id);


    /**
     * 编辑规则参数
     */
    void editSpecification(Specification spec);


    /**
     * 新增规则参数
     */
    void saveSpecification(Specification spec);
}
