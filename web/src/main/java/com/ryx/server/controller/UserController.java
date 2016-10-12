package com.ryx.server.controller;

import com.alibaba.fastjson.JSON;
import com.ryx.server.Msg.Result;
import com.ryx.server.bean.User;
import com.ryx.server.service.PushService;
import com.ryx.server.service.UserService;
import com.ryx.server.util.RedisClientTemplate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

/**
 * Created by ryx on 2016/10/10.
 */
@Controller
public class UserController {
    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    public static Random random = new Random();
    @Resource
    private UserService userService;

    @Resource(name="userPushService")
    private PushService userPushService;

    @Resource(name="newsPushService")
    private PushService newsPushService;

    @Resource(name="clientPushService")
    private PushService clientPushService;

    @Resource
    private RedisClientTemplate redisClientTemplate;
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

    @RequestMapping(value = "/setKey")
    @ResponseBody
    public String setKey(String name){
        String result =""+Math.random();
        String redisResult = redisClientTemplate.set("lff",result);
        logger.info("set key "+result);
        return redisResult;
    }


    /**
     * 用户推送
     * @param info
     * @return
     * @author Administrator
     * @create 2016-8-10 下午4:22:28
     */
    @RequestMapping(value="/user")
    @ResponseBody
    public String userPush(User info){
        Result result = new Result();
        info.setName("user "+ UUID.randomUUID());
        try {
            userPushService.push(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(info);
    }

    /**
     * 新闻推送
     * @param info
     * @return
     * @author Administrator
     * @create 2016-8-10 下午4:22:38
     */
    @RequestMapping(value="/news")
    @ResponseBody
    public String newsPush(User info){
        Result result = new Result();
        info.setName("news "+ UUID.randomUUID());
        try {
            newsPushService.push(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(info);
    }
    /**
     * 客户推送
     * @param info
     * @return
     * @author Administrator
     * @create 2016-8-10 下午4:22:48
     */
    @RequestMapping(value="/client")
    @ResponseBody
    public String clientPush(User info){
        Result result = new Result();
        try {
            info.setName("client "+ UUID.randomUUID());
            clientPushService.push(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(info);
    }






    public static void main(String[] args) {
        System.out.println(random.nextInt(3));
    }
}
