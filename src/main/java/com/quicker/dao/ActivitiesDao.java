package com.quicker.dao;

import com.quicker.database.Activity;
import com.quicker.database.StuDepartment;

import java.util.List;
import java.util.Map;

/**
 * Created by Nanguoyu on 2016/7/13.
 */
public interface ActivitiesDao {


    //获取我的关注活动
    List<String> getMyActivities(String id);


    //获取热门活动
    List<String> getHotActivities();

    //获取最新活动（按照发布时间排序）
    List<String> getNewstActivities();

    //将某活动设置为我的关注
    boolean setActivityAsMy(String stuId, String activityTitle);

    //查看某具体的活动信息
    Map<String,String> getOneActivity(String activityTitleGB2312);

    // //查看所有的学生组织
    List<StuDepartment> getAllDepartments();

    //获取某个部门组织的活动通知列表
    List<Activity> getDepartmentActivities(String department);

    //学生组织发送活动等信息
    boolean departSendActivity(Activity activity);
}
