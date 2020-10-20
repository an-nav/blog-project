package com.anvna.blog.util;

import org.springframework.util.DigestUtils;

/**
 * @ClassName MD5Utils
 * @Description MD5 加密
 * @Author an_vna
 * @Date 2020/10/20 20:28
 * @Version V1.0
 **/
public class MD5Utils {

    public static String encodeByMd5(String password) {
        String  salt= "qwerty234567-=+hjgiorewj";
        String saltPassword=salt+"/"+password;
        return DigestUtils.md5DigestAsHex(saltPassword.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(encodeByMd5("w1084612201"));
    }
}
