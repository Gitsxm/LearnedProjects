package com.example.springbootsecurity.stopwatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
class StopWatchTests {

    public void print() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("task-test");
        stopWatch.start();
        System.out.println("method run....");
        Thread.sleep(1000);
        stopWatch.stop();
        //打印格式化后的文本 单位是纳秒 ns
        System.out.println(stopWatch.prettyPrint());
        //获取毫秒
        System.out.println(stopWatch.getTotalTimeMillis()+"ms");
        //获取秒
        System.out.println(stopWatch.getTotalTimeSeconds()+"s");
        //简报 单位 ns
        System.out.println(stopWatch.shortSummary());
    }
    @Test
    void contextLoads() throws InterruptedException {
        print();
    }

}
