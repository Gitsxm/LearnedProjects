package com.example.excel;

import com.example.excel.domain.PeopleInfo;
import com.example.excel.util.ExcelUtil;
import com.google.gson.Gson;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.CollectionUtils;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class App {

    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        FileInputStream in = new FileInputStream("D:\\a.xlsx");
        Map<String,String> fieldMap = new HashMap<>();
        fieldMap.put("姓名", "name");
        fieldMap.put("年龄", "age");
        fieldMap.put("性别", "gender");
        fieldMap.put("证件", "certificatetype");
        fieldMap.put("生日", "birth");
        ExcelUtil excelUtil = new ExcelUtil<>("a.xlsx", in, PeopleInfo.class,fieldMap);
        List list = excelUtil.getModels();
        for (Object o : list
        ) {
            System.out.println(o.toString());
        }
    }
}
