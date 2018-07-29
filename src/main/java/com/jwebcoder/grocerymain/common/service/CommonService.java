package com.jwebcoder.grocerymain.common.service;

import com.jwebcoder.grocerymain.common.entity.NavigatationMenu;

import java.util.List;

public interface CommonService {

    List<NavigatationMenu> getNavMenu(String path);

}
