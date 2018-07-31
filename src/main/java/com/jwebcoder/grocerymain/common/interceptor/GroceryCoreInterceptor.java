package com.jwebcoder.grocerymain.common.interceptor;

import com.jwebcoder.grocerymain.common.entity.SystemUser;
import com.jwebcoder.grocerymain.common.utils.JacksonTools;
import com.jwebcoder.grocerymain.config.CustomProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jason on 14/10/2017.
 */

@Component
public class GroceryCoreInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CustomProperty customProperty;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)) {
            SystemUser systemUser = JacksonTools.readValueForObject(redisTemplate.opsForValue().get(token), SystemUser.class);
            if (systemUser != null) {
                request.setAttribute("userId", systemUser.getId());
                return true;
            }
        }
        //获取导航栏的可以直接通过
        if ("/common//nav".contains(request.getRequestURI())) {
            return true;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /**
         * 导航条
         */



        /*记录用户访问信息*/
        /*if (session != null) {
            indexService.loggingInfo();
        }*/

    }


}
