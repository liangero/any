package com.any.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by avaio on 2016/12/24.
 */
public class EncryptUtil {

    public static String encryptByMd5(String str){
        return Md5Hash.toString(str.getBytes());
    }
}
