package com.jwebcoder.grocerymain.photography.controller;

import com.jwebcoder.grocerymain.common.dto.ResponseMessage;
import com.jwebcoder.grocerymain.common.exception.StatusCode;
import com.jwebcoder.grocerymain.common.utils.ResponseMessageBuilder;
import com.jwebcoder.grocerymain.photography.service.PhotographyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jason on 12/10/2017.
 */

@RestController
@RequestMapping("/photography")
public class PhotographyController {

    private static final Logger logger = LoggerFactory.getLogger(PhotographyController.class);

    @Autowired
    private PhotographyService photographyService;

    @GetMapping("/detail/{id}")
    public ResponseMessage photographyDetail(@PathVariable("id") String id) {
        return ResponseMessageBuilder.success(photographyService.getPhotographyDetailById(id));
    }

    public ResponseMessage uploadPhoto(@RequestBody String photo) {
        try {

            photographyService.savePhoto(photo);
            return ResponseMessageBuilder.success();
        } catch (Exception e) {
            logger.error("PhotographyController/uploadPhoto has error", e);
            return ResponseMessageBuilder.failed(StatusCode.FAILED);

        }
    }

}
