package com.example.spring_test.util.excel;

import java.util.HashMap;
import java.util.Map;

public final class TranslationField {
    //数据里的字段翻译
    private static final Map<String, String> fieldTran;
    //表头字段翻译
    private static final Map<String, String> fieldMap;

    static {
        //此处有线程危险问题，所以这些map是不对外开放操作的
        fieldTran = new HashMap<String, String>();
        fieldMap = new HashMap<>();
        fieldMap.put("姓名", "name");
        fieldMap.put("年龄", "age");
        fieldMap.put("性别", "gender");
        fieldMap.put("证件", "certificatetype");
        fieldMap.put("生日", "birth");
        //值转换
        fieldTran.put("身份证", "01");
        fieldTran.put("驾驶证", "02");
    }

    public static Map<String, String> getFieldTran() {
        return fieldTran;
    }

    public static Map<String, String> getfieldMap() {
        return fieldMap;
    }
}