package com.jwebcoder.grocerymain.common.service.impl;

import com.jwebcoder.grocerymain.common.entity.NavigatationMenu;
import com.jwebcoder.grocerymain.common.repository.NavigatationMenuRepository;
import com.jwebcoder.grocerymain.common.service.CommonService;
import com.jwebcoder.grocerymain.common.utils.JacksonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private NavigatationMenuRepository navigatationMenuRepository;

    @Cacheable
    @Override
    public List<NavigatationMenu> getNavMenu(String path) {
        List<NavigatationMenu> navigatationMenus = navigatationMenuRepository.findAll();

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
