package com.example.demo_redis_pubsub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/redis")
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate template;

    /**
     * 消息接口
     * @param msg
     * @return
     */
    @RequestMapping("/sendMessage")
    public String sendMessage(String msg) {
        template.convertAndSend("channel:mgg1", String.format("%s-%tT", msg,new Date()));
        template.convertAndSend("channel:mgg2", String.format("%s-%tT", msg,new Date()));
        template.convertAndSend("channel:mgg3", String.format("%s-%tT", msg,new Date()));
        return "接收成功";
    }

}