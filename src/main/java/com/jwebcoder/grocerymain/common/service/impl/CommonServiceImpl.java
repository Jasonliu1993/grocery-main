package com.jwebcoder.grocerymain.common.service.impl;

import com.jwebcoder.grocerymain.common.entity.NavigatationMenu;
import com.jwebcoder.grocerymain.common.dto.NavigatationMenuDTO;
import com.jwebcoder.grocerymain.common.repository.NavigatationMenuRepository;
import com.jwebcoder.grocerymain.common.service.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private NavigatationMenuRepository navigatationMenuRepository;

    @Cacheable("nav-menu")
    @Override
    public List<NavigatationMenuDTO> getNavMenu() {
        List<NavigatationMenu> navigatationMenus = navigatationMenuRepository.findAll();
        if (CollectionUtils.isNotEmpty(navigatationMenus)) {
            List<NavigatationMenuDTO> navigatationMenuDTOS = new ArrayList<>();
            navigatationMenus.sort(Comparator.comparing(NavigatationMenu::getOrderNumber));
            navigatationMenus.parallelStream().forEachOrdered(navigatationMenu -> {
                NavigatationMenuDTO navigatationMenuDTO = new NavigatationMenuDTO();
                BeanUtils.copyProperties(navigatationMenu, navigatationMenuDTO);
                navigatationMenuDTOS.add(navigatationMenuDTO);
            });
            return navigatationMenuDTOS;
        }
        return null;
    }
}
