package com.leyou.item.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.po.Sku;
import com.leyou.item.po.SpuDetail;

import java.util.List;

public interface GoodsService {

    /**
     * 分页查询SPU
     */
    PageResult<SpuBo> querySpuByPageAndSort(Integer page, Integer rows, String key,Boolean saleable);


    /**
     * 新增商品
     */
    void save(SpuBo spuBo);


    /**
     * 查询SpuDetail
     */
    SpuDetail querySpuDetailById(Long id);


    /**
     * 查询sku
     */
    List<Sku> querySkuBySpuId(Long id);


    /**
     * 编辑商品
     */
    void update(SpuBo spuBo);
}
