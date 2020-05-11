package com.example.demo02.controller;/**
 * @ClassName TestController
 * @Description TODO
 * @Author MGG
 * @Date 2018/12/19 0019 20:52
 * @Version 1.0
 */

import com.example.demo02.domain.Jgjwd;
import com.example.demo02.domain.Jgjwd0;
import com.example.demo02.domain.People;
import com.example.demo02.service.JgjwdService;
import com.example.demo02.service.PeopleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author MGG
 * @Date 2018/12/19 0019 20:52
 * @Version 1.0
 */
@Controller
public class TestController {
    private Logger logger = LogManager.getLogger(TestController.class);
    @Resource
    private PeopleService ps;
    @Resource
    private JgjwdService js;

    /**
     * 测试查询 和线程
     *
     * @param p
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/getPerson", method = RequestMethod.GET)
    @ResponseBody
    public List<People> findOne(People p) throws InterruptedException {
        System.out.println("前台查询...");
        System.out.println(Thread.currentThread() + new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
        //Thread.sleep(5000);//延迟五秒
        List<People> list = ps.findAllById(p.getId());
        System.out.println("查询完成...");
        return list;
    }

    /**
     * 测试存储和事务支持
     *
     * @param people
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int inserInto(People people) {
        return ps.insertIntoTable(people);
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model model) {
        return "index";
    }

    /**
     * 测试地图
     *
     * @return
     */
    @RequestMapping("/show")
    @ResponseBody
    public List<Jgjwd0> getDatas() {
        return js.findALl();
    }

    @RequestMapping("/toShowgd")
    public String toShow() {
        return "show";
    }

    @RequestMapping("/toShowbd")
    public String toShowBD() {
        return "bdrlt";
    }
}
