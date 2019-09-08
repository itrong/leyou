package com.leyou.item.bo;

import com.leyou.item.po.Sku;
import com.leyou.item.po.Spu;
import com.leyou.item.po.SpuDetail;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author lujianrong
 */
@Data
public class SpuBo extends Spu {

    @Transient
    String cname;// 商品分类名称
    @Transient
    String bname;// 品牌名称
    @Transient
    SpuDetail spuDetail;// 商品详情
    @Transient
    List<Sku> skus;// sku列表
}
