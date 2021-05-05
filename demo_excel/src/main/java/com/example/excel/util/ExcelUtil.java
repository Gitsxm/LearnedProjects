package com.example.excel.util;

import com.example.excel.util.excel.NameToField;
import com.example.excel.util.excel.TranslationField;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil<T extends NameToField> {
    private String filePath;
    private static final String EXCEL_2003 = ".xls";
    private static final String EXCEL_2007 = ".xlsx";
    private Workbook workbook;
    private List<Sheet> sheets;
    private List<Row> rows;
    private List<Cell> cells;
    private List<T> models;
    private Class<T> t;
    //表头字段翻译
    private Map<String, String> fieldMap;

    /**
     * @param filePath    文件名称
     * @param inputStream 文件流
     * @param t           excel转Model类型
     * @throws IOException
     */
    public ExcelUtil(String filePath, InputStream inputStream, Class<T> t) throws Exception {
        this.filePath = filePath;
        this.fieldMap = TranslationField.getfieldMap();
        workbook = initWorkBook(filePath, inputStream);
        models = new ArrayList<T>();
        this.t = t;
        init();
    }

    /**
     * 传入表头 map
     *
     * @param filePath
     * @param inputStream
     * @param t
     * @param fieldMap
     * @throws Exception
     */
    public ExcelUtil(String filePath, InputStream inputStream, Class<T> t, Map<String, String> fieldMap) throws Exception {
        this.filePath = filePath;
        this.fieldMap = fieldMap;
        workbook = initWorkBook(filePath, inputStream);
        models = new ArrayList<T>();
        this.t = t;
        init();
    }

    /**
     * 初始化 WorkBook
     *
     * @param filePath
     * @param inputStream
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public Workbook initWorkBook(String filePath, InputStream inputStream) throws IOException, InvalidFormatException {
        Workbook workbook = null;
        if (filePath.endsWith(EXCEL_2003)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (filePath.endsWith(EXCEL_2007)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            //此处只需要这个方法就可以了
            workbook = WorkbookFactory.create(inputStream);
        }
        return workbook;
    }


    /**
     * 初始化数据
     *
     * @throws Exception
     */
    public void init() throws Exception {
        sheets = new ArrayList<Sheet>();
        rows = new ArrayList<Row>();
        cells = new ArrayList<Cell>();
        for (int i = 0, length = workbook.getNumberOfSheets(); i < length; ++i) {
            Sheet sheet = workbook.getSheetAt(i);
            sheets.add(sheet);
            Row firstRow = sheet.getRow(sheet.getFirstRowNum());
            //获取首行标题，并翻译成字段；
            String name;
            for (int j = sheet.getFirstRowNum() + 1; j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                rows.add(row);
                //存储每一行的信息
                Map<String, Object> map = new HashMap<String, Object>();
                for (int k = firstRow.getFirstCellNum(); k < firstRow.getLastCellNum(); k++) {
                    if (row == null) continue;
                    Cell cell = row.getCell(k);
                    cells.add(cell);
                    name = fieldMap.get(firstRow.getCell(k).getStringCellValue());
                    if (name != null) map.put(name, getCellValue(cell));
                }
                T ta = (T) t.newInstance();
                //用来存储行列信息
                ta.setRow(j + 1);
                BeanUtils.populate(ta, map);
                //对于excel可能出现""类似于需要对他进行非空校验，如果有其他特殊比如邮箱正则
                if (ta != null && !ta.isEmpty()) {
                    models.add(ta);
                }
            }
        }
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public List<Row> getRows() {
        return rows;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public List<T> getModels() {
        return models;
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    //由于使用yyyyMMdd时间格式是方便转成int类型进行大小判断
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.#");

    /**
     * 根据Cell源码及官方文档可知每个单元格的值都是根据Cell里的枚举
     *
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        if (cell == null
                || (cell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isEmpty(cell
                .getStringCellValue()))) {
            return null;
        }
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_BLANK:
                return null;
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    return df.format(date);
                } else {
                    return decimalFormat.format(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return null;
        }
    }

    /**
     * @功能：手工构建一个简单格式的Excel
     */
    public static void createExcel(List<List<String>> datas, String[] strArray) {
        // 创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setDefaultColumnWidth(20);// 默认列宽
        // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 添加excel title
        HSSFCell cell = null;
        for (int i = 0; i < strArray.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(strArray[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到,list中字符串的顺序必须和数组strArray中的顺序一致
        for (int rowNo = 0; rowNo < datas.size(); rowNo++) {
            row = sheet.createRow(rowNo + 1);
            List<String> list = datas.get(rowNo);
            // 第四步，创建单元格，并设置值
            for (int j = 0; j < strArray.length; j++) {
                row.createCell(j).setCellValue(list.get(j));
            }
            // 第六步，将文件存到指定位置
            try {
                File file = new File("E:/Members.xls");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(file);
                wb.write(fout);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建一个 xls
     *
     * @param datas
     * @param titleField
     * @param sheetName
     */
    public static HSSFWorkbook createExcel(List<List<String>> datas, List<String> titleField, String sheetName) {
        // 创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);// 默认列宽
        // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(CellStyle.ALIGN_CENTER);
        // 添加excel title
        HSSFCell cell = null;
        for (int i = 0; i < titleField.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(titleField.get(i));
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到,list中字符串的顺序必须和数组strArray中的顺序一致
        for (int rowNo = 0; rowNo < datas.size(); rowNo++) {
            row = sheet.createRow(rowNo + 1);
            List<String> list = datas.get(rowNo);
            // 第四步，创建单元格，并设置值
            for (int j = 0; j < titleField.size(); j++) {
                row.createCell(j).setCellValue(list.get(j));
            }
        }
        return wb;
    }

    /**
     * 创建一个 xls 并且以输入流的形式获得
     *
     * @param datas
     * @param titleField
     * @param sheetName
     * @return
     */
    public static InputStream createExcelInputStream(List<List<String>> datas, List<String> titleField, String sheetName) {
        HSSFWorkbook wb = createExcel(datas, titleField, sheetName);
        ByteArrayOutputStream bos = null;
        InputStream is = null;
        try {
            bos = new ByteArrayOutputStream();
            wb.write(bos);
            byte[] brray = bos.toByteArray();
            is = new ByteArrayInputStream(brray);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
                if (null != bos) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return is;
    }

    /**
     * 创建并下载 xls
     *
     * @param datas
     * @param titleField
     * @param sheetName
     * @return
     */
    public static ResponseEntity<InputStreamResource> downloadExcel(List<List<String>> datas, List<String> titleField, String sheetName) throws UnsupportedEncodingException {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(sheetName + ".xls", "utf-8") + "\"")
                .body(new InputStreamResource(createExcelInputStream(datas, titleField, sheetName)));
    }

}