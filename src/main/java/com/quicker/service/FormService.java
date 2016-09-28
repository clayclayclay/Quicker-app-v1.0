package com.quicker.service;

import com.quicker.database.StudentInfo;
import com.quicker.entity.json.BasicJson;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nanguoyu on 2016/7/7.
 */
public interface FormService {

    //将excel表导出到服务器，生成文件
    BasicJson excelOutputService(String[] excelName, HttpServletResponse response) throws IOException;

    //用户通过浏览器下载excel表
    void downloadExcel(List<String> excelPath,  HttpServletResponse response);


    //将填写内容excelList存储到数据库
    boolean excelWrite(List<String> excelList,String stuId);

    //获取未填写的表格列表
    List<String> getUnfinishedForms(String id);

    //获取已完成表格列表
    List<String> getFinishedForms(String id);

    //获取已收藏表格列表
    List<String> getCollectedForms(String id);

    //获取我的提醒（未处理）表格列表
    List<String> getNotFinishedFormNotice(String id);

    //获取我的提醒（已处理处理）表格列表
    List<String> getFinishedFormNotice(String id);

    //将某人所对应表格的状态置为“已收藏”
    BasicJson excelChangeToCollected(String stuIdId,String formTitle);

    //获取一张未填表格（包含标题和各个字段）
    BasicJson getOneUnfinishedForm(String formTitle);


    //获取一张已填表格（包含标题和各个字段，值）
    BasicJson getOneFinishedForm(String stuId, String formTitle);

    //统计填写表格的所有学生（包括基本信息，人数等）
    List<List<Map<String,String>>>  getStudentInfoByForm();

    //判断该学生其所属班级
    String JudgeBelongToClass(StudentInfo studentInfo);

}
