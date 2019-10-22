package com.leyou.search.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author lujianrong
 */
@Data
public class SearchRequest {

    private String key;// 搜索条件
    private Integer page;// 当前页
    private String sortBy;// 排序字段
    private Boolean descending;// 是否降序
    private Map<String,String> filter;// 过滤条件

    private static final Integer DEFAULT_SIZE = 20;// 每页大小，不从页面接收，而是固定大小
    private static final Integer DEFAULT_PAGE = 1;// 默认页

    public Integer getSize() {
        return this.DEFAULT_SIZE;
    }

    public Integer getPage() {
        return this.page == null ? this.DEFAULT_PAGE : this.page;
    }


}
