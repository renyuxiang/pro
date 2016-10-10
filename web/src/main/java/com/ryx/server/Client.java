package com.ryx.server;

import com.ryx.server.bean.User;

/**
 * Created by ryx on 2016/10/10.
 */
public class Client {
    public static void main(String[] args) {
        User user = new User();
        user.setId("123");
        System.out.println(user.getId());
    }
}
