package com.jwebcoder.grocerymain.common.utils;

import org.apache.commons.lang.StringUtils;

public class TokenUtil {

    public static String getToken(String authorization) {
        if (StringUtils.isNotEmpty(authorization)) {
            return authorization.replace("Bearer ", "");
        }
        return null;
    }

}
