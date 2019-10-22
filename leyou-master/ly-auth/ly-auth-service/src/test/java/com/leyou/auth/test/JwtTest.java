package com.leyou.auth.test;

import com.leyou.auth.entity.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author lujianrong
 */
public class JwtTest {

    private static final String pubKeyPath = "E:\\tmp\\rsa.pub";

    private static final String priKeyPath = "E:\\tmp\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU2OTg2OTE3MH0.fwmaGAoFHNiBBy8Jy6lg-GDpL5_nQ8HtpIhA_mg0p5lwufYmO7ilM_P9MyT2KD9qhPwqw-DJXvCj3oJhWb8JNuFcaxajWkcc-OgbxQRDD3GtKrUa-aSQDA3NpSBbiym4056g3lOutMv42tghfk8qCrKsbji4T4mLA4DK6qlD3Sc";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}