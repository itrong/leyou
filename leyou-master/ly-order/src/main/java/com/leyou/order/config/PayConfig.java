package com.leyou.order.config;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.InputStream;

/**
 * @author lujianrong
 */
@Data
public class PayConfig implements WXPayConfig {
    private String appID; // 公众账号ID

    private String mchID;// 商户号

    private String key;// 生成签名的密钥

    private int httpConnectTimeoutMs; // 连接超时时间

    private int httpReadTimeoutMs;// 读取超时时间

    private String notifyUrl;

    @Override
    public InputStream getCertStream() {
        return null;
    }
}
