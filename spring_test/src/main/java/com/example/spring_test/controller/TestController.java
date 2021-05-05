package com.example.spring_test.controller;

import com.example.spring_test.service.RoomService;
import com.example.spring_test.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private RoomService service;

    @GetMapping("/do")
    @ResponseBody
    private String make() throws InterruptedException {
        return service.personDo();
    }
    /**
     * 下载
     *
     * @param request
     * @return
     */
    @GetMapping("/down")
    public ResponseEntity<InputStreamResource> downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<List<String>> datas = new ArrayList<>();
        List<String> data1= new ArrayList<>();
        List<String> data2= new ArrayList<>();
        data1.add("kangkang");
        data1.add("10");
        data2.add("jane");
        data2.add("10");
        datas.add(data1);
        datas.add(data2);
        String[] title = {"姓名","年龄"};
        HSSFWorkbook wb = ExcelUtil.createExcel(datas,title);
        Object object = new Object();
        String name = "中文.xls";
        ByteArrayOutputStream bos = null;
        InputStream is = null;
        try {
            bos = new ByteArrayOutputStream();
            wb.write(bos);
            byte[] brray = bos.toByteArray();
            is = new ByteArrayInputStream(brray);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            is.close();
            bos.close();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(name, "utf-8") + "\"")
                .body(new InputStreamResource(is));
    }
}
