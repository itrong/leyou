package com.leyou.item.api;

import org.springframework.web.bind.annotation.*;

@RequestMapping("spec")
public interface SpecificationApi {


    /**
     * 根据分类id查询规格参数
     *
     * @param id 分类id
     */
    @GetMapping("{id}")
    String querySpecificationByCategoryId(@PathVariable("id") Long id);


}
