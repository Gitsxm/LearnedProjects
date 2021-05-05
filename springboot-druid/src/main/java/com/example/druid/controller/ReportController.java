package com.example.druid.controller;

import com.example.druid.domain.FrReportheader;
import com.example.druid.mapper.FrReportheaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/11 11:46
 */
@Controller
public class ReportController {

    @Autowired
    private FrReportheaderMapper mapper;

    /**
     * 海洋经济直报用,将 xls 打包为 zip 格式下载。
     * @param beginTime
     * @param endTime
     * @param response
     */
    @GetMapping("/htmlExport")
    public void export(String beginTime, String endTime, HttpServletResponse response) {
        OutputStream toClient = null;
        ZipOutputStream zipOut = null;
        ByteArrayOutputStream bos = null;
        InputStream inputStream = null;
        try {
            toClient = new BufferedOutputStream(response.getOutputStream());
            zipOut = new ZipOutputStream(toClient);
            List<FrReportheader> list = mapper.exportData(beginTime, endTime);
            response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("海洋经济直报" + ".zip", "utf-8"));
            response.setContentType("application/octet-stream ");
            response.setHeader("Accept-Ranges", "bytes");// 告诉客户端允许断点续传多线程连接下载
            response.setCharacterEncoding("UTF-8");
            if (0 < list.size()) {
                Integer count =1;
                for (FrReportheader frReportheader : list) {
                    //大字段处理
                    Clob clob = (Clob) frReportheader.getHtml();
                    Reader inreader = clob.getCharacterStream();
                    BufferedReader br = new BufferedReader(inreader);
                    String s = br.readLine();
                    bos = new ByteArrayOutputStream();
                    while (null != s) {
                        bos.write(s.getBytes());
                        s = br.readLine();
                    }
                    byte[] brray = bos.toByteArray();
                    inputStream = new ByteArrayInputStream(brray);
                    //打包 zip，名字里加 count 是因为有重复名称。
                    ZipEntry entry = new ZipEntry(count.toString()+frReportheader.getName() + frReportheader.getReportunit() + frReportheader.getReportno() + frReportheader.getTaskperiod() + ".xls");
                    count++;
                    zipOut.putNextEntry(entry);
                    int temp = 0;
                    while ((temp = inputStream.read()) != -1) {
                        zipOut.write(temp);
                    }
                }
                zipOut.flush();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream)
                    inputStream.close();
                if (null != bos)
                    bos.close();
                if (null != zipOut)
                    zipOut.close();
                if (null != toClient)
                    toClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
