package com.quicker.serviceImp;

import com.quicker.dao.FormDao;
import com.quicker.database.ExcelAndTable;
import com.quicker.database.ExcelInfo;
import com.quicker.database.StuExcel;
import com.quicker.database.StudentInfo;
import com.quicker.entity.json.BasicJson;
import com.quicker.entity.json.Errmsg;
import com.quicker.service.FormService;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Nanguoyu on 2016/7/7.
 */
@Service
public class FormServiceImpl implements FormService {


    @Autowired
    private FormDao excelDao;


    //将excel表导出
    @Override
    public BasicJson excelOutputService(String[] excelName) throws IOException {
        String excelPath = null;
        List<ExcelAndTable> excelAndTableList = null;
        BasicJson basicJson = new BasicJson();
//        try {
            List<String> tableName = new ArrayList<String>();
            List<String> excelPathName = new ArrayList<String>();
            //获取excelName对应的:tableName
            excelAndTableList = excelDao.getTables();
            for (int m = 0; m < excelName.length; m++) {
                for (int n = 0; n < excelAndTableList.size(); n++) {
                    if (excelAndTableList.get(n).getExcelName().equals(excelName[m])) {
                        tableName.add(excelAndTableList.get(n).getTableName());
                        excelAndTableList.remove(n);
                        break;
                    }
                }
            }

            //获取tableName表的所有字段信息
            List<List<Map<String, String>>> someTablesInfoList = excelDao.getSomeFinishedInfo(tableName);


            //获取所有excel名字和excel路径映射的关系:excelPathName
            List<ExcelInfo> excelInfoList = excelDao.excelGet();
            for (int m = 0; m < excelName.length; m++) {
                for (int n = 0; n < excelInfoList.size(); n++) {
                    if (excelInfoList.get(n).getExcelName().equals(excelName[m])) {
                        excelPathName.add(excelInfoList.get(n).getExcelPath());
                        excelInfoList.remove(n);
                        break;
                    }
                }
            }


            XSSFWorkbook wb = null;

            FileOutputStream out = null;

			/*
                    生成xlsx文件：
					XSSFWorkbook wb = new XSSFWorkbook();
					XSSFSheet sheet = wb.createSheet(num);
					XSSFROW row = sheet.createRow(num);
					XSSFFCELL cell = row.createCell(num);

					生成xls文件，则需要将XSSF系列换成HSSF即可

					 */
            for (int k = 0; k < excelName.length; k++) {
                out = new FileOutputStream(new File(excelPathName.get(k)));
                wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet();
                for (int a = 0; a < someTablesInfoList.get(k).size(); a++) {
                    XSSFRow row = sheet.createRow(a);
                    Map<String, String> map = someTablesInfoList.get(k).get(a);
                    for (int n = 0; n < map.size() - 1; n++) {
                        XSSFCell cell = row.createCell(n);
                        String key = "flag" + (n + 1);

                        //构造flag系列，来将对应数据库里边的数据都给取出来。
                        cell.setCellValue(new XSSFRichTextString(map.get(key)));
                    }
                }
                wb.write(out);
                out.close();

            }
            basicJson.setStatus(true);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            basicJson.setStatus(false);
//            Errmsg errmsg = new Errmsg();
//            errmsg.setDescription("导出失败");
//            basicJson.setErrorMsg(errmsg);
//        }
        return basicJson;

    }


    //将填写内容excelList存储到数据库
    @Override
    public boolean excelWrite(List<String> excelList, String stuId) {

        String title = excelList.get(excelList.size() - 1);
        String table = excelDao.getTableNameByExcel(title);
        if (table != null) {
            excelDao.excelWrite(excelList, table, stuId);
            excelDao.excelChangeToWrited(table, stuId);
            return true;
        } else {
            return false;
        }
    }

    //获取未填写的表格列表
    @Override
    public List<String> getUnfinishedForms(String id) {
        List<StuExcel> stuExcelList = excelDao.getUnfinishedForms(id);
        List<String> excelUnfinishedName = new ArrayList<String>();
        for (int i = 0; i < stuExcelList.size(); i++) {
            excelUnfinishedName.add(stuExcelList.get(i).getExcelName());
        }
        return excelUnfinishedName;
    }

    //获取已完成表格列表
    @Override
    public List<String> getFinishedForms(String id) {
        List<StuExcel> stuExcelList = excelDao.getFinishedForms(id);
        List<String> excelFinishedName = new ArrayList<String>();
        for (int i = 0; i < stuExcelList.size(); i++) {
            excelFinishedName.add(stuExcelList.get(i).getExcelName());
        }
        return excelFinishedName;
    }


    //获得收藏表格列表
    @Override
    public List<String> getCollectedForms(String id) {
        List<StuExcel> stuExcelList = excelDao.getCollectedForms(id);
        List<String> excelCollectedName = new ArrayList<String>();
        for (int i = 0; i < stuExcelList.size(); i++) {
            excelCollectedName.add(stuExcelList.get(i).getExcelName());
        }
        return excelCollectedName;
    }

    //获取我的提醒（未处理）表格列表
    @Override
    public List<String> getNotFinishedFormNotice(String id) {
        List<StuExcel> stuExcelList = excelDao.getNotFinishedFormNotice(id);
        List<String> excelName = new ArrayList<String>();
        for (int i = 0; i < stuExcelList.size(); i++) {
            excelName.add(stuExcelList.get(i).getExcelName());
        }
        return excelName;
    }

    //获取我的提醒（已处理处理）表格列表
    @Override
    public List<String> getFinishedFormNotice(String id) {
        List<StuExcel> stuExcelList = excelDao.getFinishedFormNotice(id);
        List<String> excelName = new ArrayList<String>();
        for (int i = 0; i < stuExcelList.size(); i++) {
            excelName.add(stuExcelList.get(i).getExcelName());
        }
        return excelName;
    }


    //将excel表的状态置为“已收藏”
    @Override
    public BasicJson excelChangeToCollected(String stuId, String formTitle) {
        String table = excelDao.getTableNameByExcel(formTitle);
        BasicJson basicJson = new BasicJson();
        Errmsg errmsg = new Errmsg();
        boolean status = excelDao.excelChangeToCollected(stuId, table);
        if (status) {
            basicJson.setStatus(true);
            errmsg.setDescription("收藏成功");
        } else {
            basicJson.setStatus(false);
            errmsg.setDescription("收藏失败");
        }
        return basicJson;
    }


    //获取一张未填表格（包含标题和各个字段）
    @Override
    public BasicJson getOneUnfinishedForm(String formTitle) {
        BasicJson basicJson = new BasicJson();
        String table = excelDao.getTableNameByExcel(formTitle);
        List<Map<String, String>> map = excelDao.getUnfinishedOrAllInfo(table);
        List<String> list = new ArrayList<String>();
        System.out.println("map的大小为：" + map.size());
        for (int i = 0; i < map.get(0).size() - 2; i++) {
            String key = "flag" + (i + 1);
            list.add(map.get(0).get(key));
        }
        basicJson.setStatus(true);
        basicJson.setJsonString(list);
        return basicJson;
    }


    //获取一张已填写的表格
    @Override
    public BasicJson getOneFinishedForm(String stuId, String formTitle) {
        BasicJson basicJson = new BasicJson();
        String table = excelDao.getTableNameByExcel(formTitle);
        List<List<Map<String, String>>> fieldAndValue = excelDao.getFinishedInfo(table, stuId);
        List<List<String>> fieldAndValueList = new ArrayList<List<String>>();
        fieldAndValueList.add(new ArrayList<String>());
        fieldAndValueList.add(new ArrayList<String>());

        //将字段属性和值封装到fieldAndValueList中
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < fieldAndValue.get(i).get(0).size() - 2; j++) {
                System.out.println(fieldAndValue.get(i).get(0).size() - 2);
                String key = "flag" + (j + 1);
                String fieldOrValue = fieldAndValue.get(i).get(0).get(key);
                System.out.println(fieldOrValue);
                fieldAndValueList.get(i).add(fieldOrValue);
            }
        }
        basicJson.setStatus(true);
        basicJson.setJsonString(fieldAndValueList);
        System.out.println("接口测试结束！！！");
        return basicJson;
    }


    //统计填写表格的所有学生（包括班级，人数等）
    @Override
    public List<List<Map<String, String>>> getStudentInfoByForm() {

        List<List<Map<String, String>>> formInfo = excelDao.getStudentInfoByForm();
        return formInfo;
    }


    //对传入的已填某个表格的学生判断其所属班级
    @Override
    public String JudgeBelongToClass(StudentInfo studentInfo) {
        Map<String, String> classStuMap = new HashMap<String, String>();
        int[] stuNum = new int[22];
        int num;
        String classs = studentInfo.getClasss();

        if (classs.equals("0101")) {
            return "1";
        } else if (classs.equals("0102")) {
            return "2";
        } else if (classs.equals("0103")) {
            return "3";
        } else if (classs.equals("0104")) {
            return "4";
        } else if (classs.equals("0105")) {
            return "5";
        } else if (classs.equals("0201")) {
            return "6";
        } else if (classs.equals("0202")) {
            return "7";
        } else if (classs.equals("0203")) {
            return "8";
        } else if (classs.equals("0301")) {
            return "9";
        } else if (classs.equals("0302")) {
            return "10";
        } else if (classs.equals("0303")) {
            return "11";
        } else if (classs.equals("0304")) {
            return "12";
        } else if (classs.equals("0305")) {
            return "13";
        } else if (classs.equals("0401")) {
            return "14";
        } else if (classs.equals("0402")) {
            return "15";
        } else if (classs.equals("0403")) {
            return "16";
        } else if (classs.equals("0501")) {
            return "17";
        } else if (classs.equals("0502")) {
            return "18";
        } else if (classs.equals("0601")) {
            return "19";
        } else if (classs.equals("0701")) {
            return "20";
        } else if (classs.equals("0901")) {
            return "21";
        } else {
            return "22";
        }

    }


    /*
    测试，判断object类型
     */
    public static void judgeObjectType(Object param) {
        if (param instanceof Integer) {
            int value = ((Integer) param).intValue();
            System.out.println(value);
        } else if (param instanceof String) {
            String s = (String) param;
            System.out.println(s);
        } else if (param instanceof Double) {
            double d = ((Double) param).doubleValue();
            System.out.println(d);
        } else if (param instanceof Float) {
            float f = ((Float) param).floatValue();
            System.out.println(f);
        } else if (param instanceof Long) {
            long l = ((Long) param).longValue();
            System.out.println(l);
        } else if (param instanceof Boolean) {
            boolean b = ((Boolean) param).booleanValue();
            System.out.println(b);
        } else if (param instanceof Date) {
            Date d = (Date) param;
            System.out.println(d);
        } else {
            System.out.println("该object什么类型都不属于~~~");
        }
    }

}
