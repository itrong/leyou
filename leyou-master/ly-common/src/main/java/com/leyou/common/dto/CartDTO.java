package com.leyou.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lujianrong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long skuId;  // 商品skuId
    private Integer num; // 购买数量
}
