package com.ryx.server;

import com.ryx.server.bean.User;
import com.ryx.server.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ryx on 2016/10/10.
 */
public class Client {
    public static Logger logger = LoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath*:application-web.xml");
        UserService service = context.getBean("userProvider",UserService.class);
        User user = new User();
        user.setName("ryx");
        String result = service.insert(user);
        System.out.println(result);
        System.out.println(service.query("1"));
        System.out.println(service.delete("2"));
    }

    @Test
    public void test(){
        logger.info("aasss");

    }

}
