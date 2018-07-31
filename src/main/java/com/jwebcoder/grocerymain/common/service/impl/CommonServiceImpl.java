package com.jwebcoder.grocerymain.common.service.impl;

import com.jwebcoder.grocerymain.common.entity.NavigatationMenu;
import com.jwebcoder.grocerymain.common.repository.NavigatationMenuRepository;
import com.jwebcoder.grocerymain.common.service.CommonService;
import com.jwebcoder.grocerymain.common.utils.JacksonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private NavigatationMenuRepository navigatationMenuRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<NavigatationMenu> getNavMenu(String path) {
        List<NavigatationMenu> navigatationMenus = JacksonTools.readValueForList(redisTemplate.opsForValue().get("navigatationMenus"), NavigatationMenu.class);

        if (navigatationMenus == null) {
            navigatationMenus = navigatationMenuRepository.findAll();

            redisTemplate.opsForValue().set("navigatationMenus", JacksonTools.writteObjectToValue(navigatationMenus));
        }

        /**
         * 标识是在哪个页面，将导航条设置成高亮
         */
        for (NavigatationMenu navigatationMenu : navigatationMenus) {

            if (navigatationMenu.getLinkMatching().contains("&")) {
                String[] strings = navigatationMenu.getLinkMatching().split("&");
                for (String str : strings) {
                    if (path.startsWith(str))
                        navigatationMenu.setCustom1("Y");
                }
            } else {
                if (path.startsWith(navigatationMenu.getLinkMatching()))
                    navigatationMenu.setCustom1("Y");
            }
        }

        return navigatationMenus;
    }
}
