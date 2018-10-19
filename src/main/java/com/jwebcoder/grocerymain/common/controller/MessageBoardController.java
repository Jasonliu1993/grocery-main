package com.jwebcoder.grocerymain.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 15/10/2017.
 */

@RestController
@RequestMapping("/messageBoard")
public class MessageBoardController {

    @PostMapping("/leaveMessage")
    public String sendMessage(String id) {
        return id + " Hello World";
    }


}
