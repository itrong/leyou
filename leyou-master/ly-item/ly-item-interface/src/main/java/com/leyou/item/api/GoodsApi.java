package com.leyou.item.api;

import com.leyou.common.dto.CartDTO;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.po.Sku;
import com.leyou.item.po.SpuDetail;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/goods")
public interface GoodsApi {

    /**
     * 分页查询SPU
     *
     * @param page 当前页
     * @param rows 内页行数
     * @param key  搜索条件
     */
    @GetMapping("/spu/page")
    PageResult<SpuBo> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "key") String key,
            @RequestParam(value = "saleable", defaultValue = "false") Boolean saleable);


    /**
     * 查询SpuDetail
     *
     * @param id spuId
     */
    @GetMapping("/spu/detail/{id}")
    SpuDetail querySpuDetailById(@PathVariable("id") Long id);


    /**
     * 查询sku
     *
     * @param
     * @return
     */
    @GetMapping("sku/list")
    List<Sku> querySkuBySpuId(@RequestParam("id") Long id);

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @GetMapping("/spu/{id}")
    SpuBo queryGoodsById(@PathVariable("id") Long id);


    /**
     * 根据id查sku
     */
    @GetMapping("sku/{id}")
    Sku querySkuById(@PathVariable("id")Long id);


    @PostMapping("stock/decrease")
    void decreaseStock(@RequestBody List<CartDTO> cartDTOS);


    /**
     * 根据id列表查sku
     */
    @GetMapping("sku/list/ids")
    List<Sku> querySkuByIds(@RequestParam List<Long> ids);


}