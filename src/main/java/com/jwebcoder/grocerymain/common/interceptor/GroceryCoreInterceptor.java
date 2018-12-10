package com.jwebcoder.grocerymain.common.interceptor;

import com.jwebcoder.grocerymain.common.entity.SystemUser;
import com.jwebcoder.grocerymain.common.utils.JacksonTools;
import com.jwebcoder.grocerymain.config.CustomProperty;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jason on 14/10/2017.
 */

@Component
public class GroceryCoreInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(GroceryCoreInterceptor.class);

    @Autowired
    private CustomProperty customProperty;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)) {
            SystemUser systemUser = JacksonTools.readValue(redisTemplate.opsForValue().get(token), SystemUser.class);
            if (systemUser != null) {
                logger.info("GroceryCoreInterceptor/preHandle/systemUser{}", JacksonTools.writteObjectToValue(systemUser));
                request.setAttribute("userId", systemUser.getId());
            }
        }
        return true;
    }

}
