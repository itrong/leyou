package com.leyou.search.pojo;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.po.Brand;
import com.leyou.item.po.Category;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author lujianrong
 */
@Data
public class SearchResult<T> extends PageResult<Goods> {

    private List<Category> categories;// 分类过滤条件

    private List<Brand> brands; // 品牌过滤条件

    private List<Map<String,Object>> specs; // 规格参数过滤条件

    public SearchResult(Long total, Integer totalPage, List<Goods> items, List<Category> categories,
                        List<Brand> brands,List<Map<String,Object>> specs) {
        super(total, totalPage, items);
        this.categories = categories;
        this.brands = brands;
        this.specs=specs;
    }


}
