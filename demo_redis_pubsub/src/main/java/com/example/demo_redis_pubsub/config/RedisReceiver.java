package com.example.demo_redis_pubsub.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisReceiver implements MessageListener {

    /**
     * RedisReceiver可以是普通类或者实现 MessageListener，普通类接收的时候只接收到消息，没有频道名,实现 MessageListener，就能拿到消息体和频道名。
     * @param message
     * @param bytes
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("频道："+new String(message.getChannel()));
        System.out.println("消息: "+new String(message.getBody()));
    }
}