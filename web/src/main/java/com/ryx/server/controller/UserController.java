package com.ryx.server.controller;

import com.ryx.server.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by ryx on 2016/10/10.
 */
@Controller
public class UserController {
    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    public static Random random = new Random();
    @Resource
    private UserService userService;

    public static String[] args = {
            "18957141081",
            "18957141082",
            "18957141083",
    };
    @RequestMapping(value = "/queryUser")
    @ResponseBody
    public String queryUser(String name){

        logger.info("queryUser");
        String result = userService.query(args[random.nextInt(3)]);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(random.nextInt(3));
    }
}
