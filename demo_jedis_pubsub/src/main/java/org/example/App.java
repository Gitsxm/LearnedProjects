package org.example;

import org.example.jedismode.Publisher;
import org.example.jedismode.SubThread;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 测试订阅模式
 */
public class App {
    public static void main(String[] args) {
        // 连接redis服务端
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", "127.0.0.1", 6379));
        SubThread subThread = new SubThread(jedisPool);  //订阅者
        subThread.start();
        Publisher publisher = new Publisher(jedisPool);    //发布者
        publisher.start();
    }
}
