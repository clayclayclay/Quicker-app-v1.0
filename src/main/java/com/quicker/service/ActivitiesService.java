package com.quicker.service;

import com.quicker.entity.json.BasicJson;

/**
 * Created by Nanguoyu on 2016/7/13.
 */
public interface ActivitiesService {


    //获取我的关注
    BasicJson getMyActivities(String id);

    //获取热门活动(按照点击量排序)
    BasicJson getHotActivities();

    //获取最新活动（按照发布时间排序）
    BasicJson getNewstActivities();

    //将某活动设置为自己的关注
    BasicJson setActivityAsMy(String stuId, String activityTitle);

    //查看某具体的活动信息
    BasicJson getOneActivity(String activityTitleGB2312);

    //查看所有的学生组织
    BasicJson getAllDepartments();

    //获取某个部门组织的活动通知列表
    BasicJson getDepartmentActivities(String department);

    //学生组织发送活动等信息
    BasicJson departSendActivity(String title, String contents);
}
