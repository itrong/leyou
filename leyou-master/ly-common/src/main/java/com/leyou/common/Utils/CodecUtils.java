package com.leyou.common.Utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lujianrong
 */
public class CodecUtils {


    /**
     * 获取盐
     */
    public static String generateSalt() {
        return BCrypt.gensalt();
    }


    /**
     * 加密
     */
    public static String passwordBcryptEncode(String password, String salt) {

        return new BCryptPasswordEncoder().encode(password + salt);
    }


    /**
     * 比对密码
     */
    public static Boolean passwordConfirm(String rawPassword, String salt, String encodePassword) {
        return new BCryptPasswordEncoder().matches(rawPassword + salt, encodePassword);
    }

}
