package com.quicker.dao;

import java.util.List;
import java.util.Map;

import com.quicker.database.ExcelAndTable;
import com.quicker.database.StuExcel;

import com.quicker.database.ExcelInfo;

public interface FormDao {

    //上传表格
    void excelUpload(String excelName, String excelPath);

    //得到表格标题，存储位置
    List<ExcelInfo> excelGet();

    //动态生成表格数据表
    String excelCreate(int num);

    //初始化数据表信息（将excel表的各个字段写入到数据表的第一行）
    void excelInit(List<String> excel, String tableName);

    //填写表格信息
    void excelWrite(List<String> excel, String table, String stuId);


    //得到一张未填表格信息
    List<Map<String, String>> getUnfinishedOrAllInfo(String table);

    //获取若干张表格已填信息
    List<List<Map<String,String>>> getSomeFinishedInfo(List<String> tableList);

    //得到一张已填表格所填信息
    List<List<Map<String,String>>> getFinishedInfo(String table, String stuId);

    //设置表格和数据表的映射关系
    void setTableAndExcelName(String excelName, String table);

    //获取表格和数据表的映射关系
    List<ExcelAndTable> getTableAndExcelName();

    //通过excel标题来获得数据表名
    String getTableNameByExcel(String id);

    //获取所有excel的数据表名
    List<ExcelAndTable> getTables();

    //初始化学生与表格之间的关系：1.未填写，2，未收藏，3，未通知。
    void initStuExcelRelation(List<String> excelTitleList, List<String> tableNameList);

    //通过学生id来获取未填写的表格
    List<StuExcel> getUnfinishedForms(String id);

    //获取已完成表格列表
    List<StuExcel> getFinishedForms(String id);

    //学生对应的excel表的状态改变（1.填写了2.收藏了）
    void excelChangeToWrited(String table, String stuId);

    //将相应表格的状态置为“已收藏”
    boolean excelChangeToCollected(String stuId, String tableName);

    //获取已收藏表格列表
    List<StuExcel> getCollectedForms(String id);

    //获取我的提醒（未处理）表格列表
    List<StuExcel> getNotFinishedFormNotice(String id);

    //获取我的提醒（已处理）表格列表
    List<StuExcel> getFinishedFormNotice(String id);


    //测试接口方法
    void getFinishedInfo111(String table, String stuId);

    //统计填写表格的所有学生（包括班级，人数等）
    List<List<Map<String,String>>>  getStudentInfoByForm();

    //获取所有班级信息
    List<Map<String,String>> getAllClassInfo();

}