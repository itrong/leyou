package com.leyou.search.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.po.Spu;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.SearchResult;
import com.leyou.search.vo.SearchRequest;

import java.io.IOException;

public interface SearchService {

    Goods buildGoods(Spu spu) throws IOException;

    SearchResult<Goods> search(SearchRequest request);
}
