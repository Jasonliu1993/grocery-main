package com.jwebcoder.grocerymain.common.controller;

import com.jwebcoder.grocerymain.common.dto.ResponseMessage;
import com.jwebcoder.grocerymain.common.entity.NavigatationMenu;
import com.jwebcoder.grocerymain.common.entity.SystemUser;
import com.jwebcoder.grocerymain.common.repository.SystemUserRepository;
import com.jwebcoder.grocerymain.common.service.CommonService;
import com.jwebcoder.grocerymain.common.utils.JacksonTools;
import com.jwebcoder.grocerymain.common.utils.PackingInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SystemUserRepository systemUserRepository;

    @GetMapping("/nav")
    public ResponseMessage<List<NavigatationMenu>> getNav(@RequestAttribute(required = false) String userid, @RequestParam(name = "token", required = false) String token, String uri) {
        List<NavigatationMenu> navigatationMenus = commonService.getNavMenu(uri);

        if (StringUtils.isNotEmpty(token)) {
            SystemUser systemUser = JacksonTools.readValueForObject(redisTemplate.opsForValue().get(token), SystemUser.class);
            if (systemUser != null)
                if ("admin".equals(systemUser.getType())) {
                    return PackingInfo.changeData2Message(navigatationMenus);
                } else {
                    navigatationMenus.remove(navigatationMenus.remove(navigatationMenus.size() - 1));
                    return PackingInfo.changeData2Message(navigatationMenus);
                }
        } else {
            //去掉管理界面
            navigatationMenus.remove(navigatationMenus.remove(navigatationMenus.size() - 1));
            return PackingInfo.changeData2Message(navigatationMenus);
        }
        return null;
    }

}
