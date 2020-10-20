package com.example.excel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.example.excel.domain.Member;
import com.example.excel.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 创建excel，写入数据，然后下载到本地
 *
 * @author baiyu
 */
public class DownloadExcel {

    public static void main(String[] args) {
        List<List<String>> datas = getDatas();
        String[] strArray = excelTitle();
        ExcelUtil.createExcel(datas, strArray);
    }

    /**
     * 初始化数据，将会被存储到excel表格中
     *
     * @return
     * @throws Exception
     */
    private static List<List<String>> getDatas() {
        List<Member> list = new ArrayList<>();
        List<List<String>> datas = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        try {
            Member user1 = new Member(1, "mgg", 24, df.parse("1993-08-28"));
            Member user2 = new Member(2, "kkk", 23, df.parse("1994-08-19"));
            Member user3 = new Member(3, "dsf", 24, df.parse("1983-11-22"));

            list.add(user1);
            list.add(user2);
            list.add(user3);

            for (int i = 0; i < list.size(); i++) {
                ArrayList<String> members = new ArrayList<String>();
                members.add(list.get(i).getCode() + "");
                members.add(list.get(i).getName());
                members.add(list.get(i).getAge() + "");
                members.add(df.format(list.get(i).getBirth()));
                datas.add(members);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datas;
    }

    /**
     * 创建excel title
     */
    public static String[] excelTitle() {
        String[] strArray = {"学号", "姓名", "年龄", "生日"};
        return strArray;
    }
}