package com.example.demo_hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class DemoHelloApplicationTests {

    @Test
    void contextLoads() {

        // 获取当月的天数（需完善）
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        // 定义当前期间的1号的date对象
        Date date = null;
        try {
            date = dateFormat.parse("201602");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,1);//月增加1天
        calendar.add(Calendar.DAY_OF_MONTH,-1);//日期倒数一日,既得到本月最后一天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String lastDayOfMonth = sdf.format(calendar.getTime()).substring(6,8);
        System.out.println(lastDayOfMonth);
    }

}
