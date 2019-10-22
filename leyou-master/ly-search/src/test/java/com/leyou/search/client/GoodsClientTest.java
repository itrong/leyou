package com.leyou.search.client;

import com.leyou.item.po.Sku;
import com.leyou.search.LySearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LySearchService.class)
public class GoodsClientTest {

    @Autowired
    private GoodsClient goodsClient;

    @Test
    public void testQuery(){
        List<Sku> skus = goodsClient.querySkuBySpuId(12L);
        skus.forEach(System.out::println);
    }


}
