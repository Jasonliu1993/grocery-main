package com.jwebcoder.grocerymain.photography.utils;

import java.util.Base64;
import java.util.UUID;

public class PhotoUtil {

    public static String encryptPhoto(byte[] photo) {
        return Base64.getEncoder().encodeToString(photo);
    }

    public static byte[] decryptPhoto(String photo) {
        return Base64.getDecoder().decode(photo);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
