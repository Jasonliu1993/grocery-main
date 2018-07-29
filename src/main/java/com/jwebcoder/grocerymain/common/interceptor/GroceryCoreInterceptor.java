package com.jwebcoder.grocerymain.common.interceptor;

import com.jwebcoder.grocerymain.common.entity.NavigatationMenu;
import com.jwebcoder.grocerymain.common.entity.SystemUser;
import com.jwebcoder.grocerymain.common.service.CommonService;
import com.jwebcoder.grocerymain.common.utils.JacksonTools;
import com.jwebcoder.grocerymain.common.utils.PackingInfo;
import com.jwebcoder.grocerymain.config.CustomProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Jason on 14/10/2017.
 */

@Component
public class GroceryCoreInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CustomProperty customProperty;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CommonService commonService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)) {
            SystemUser systemUser = JacksonTools.readValueForObject(redisTemplate.opsForValue().get(token), SystemUser.class);
            if (systemUser != null)
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

        List<NavigatationMenu> navigatationMenus = commonService.getNavMenu(request.getRequestURI());

        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)) {
            SystemUser systemUser = JacksonTools.readValueForObject(redisTemplate.opsForValue().get(token), SystemUser.class);
            if (systemUser != null)
                if ("admin".equals(systemUser.getType())) {
                    request.setAttribute("Nav", PackingInfo.changeData2Message(navigatationMenus));
                } else {
                    navigatationMenus.remove(navigatationMenus.remove(navigatationMenus.size() - 1));
                    request.setAttribute("Nav", PackingInfo.changeData2Message(navigatationMenus));
                }
        } else {
            //去掉管理界面
            navigatationMenus.remove(navigatationMenus.remove(navigatationMenus.size() - 1));
            request.setAttribute("Nav", PackingInfo.changeData2Message(navigatationMenus));
        }

        /*记录用户访问信息*/
        /*if (session != null) {
            indexService.loggingInfo();
        }*/

    }


}
