package com.jwebcoder.grocerymain.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GroceryTokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(GroceryTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        logger.info("token++++++++{}", token);
        request.setAttribute("groceryToken", token);
        return true;
    }
}
