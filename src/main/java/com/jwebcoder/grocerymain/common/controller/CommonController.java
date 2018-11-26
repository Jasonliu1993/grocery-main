package com.jwebcoder.grocerymain.common.controller;

import com.jwebcoder.grocerymain.common.dto.ResponseMessage;
import com.jwebcoder.grocerymain.common.entity.NavigatationMenu;
import com.jwebcoder.grocerymain.common.service.CommonService;
import com.jwebcoder.grocerymain.common.utils.ResponseMessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    @GetMapping("/nav")
    public ResponseMessage<List<NavigatationMenu>> getNav() {
        return ResponseMessageBuilder.success(commonService.getNavMenu());
    }

}
