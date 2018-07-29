package com.jwebcoder.grocerymain.common.repository;


import com.jwebcoder.grocerymain.common.entity.NavigatationMenu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavigatationMenuRepository extends CrudRepository<NavigatationMenu, String> {

    List<NavigatationMenu> findAll();

}
