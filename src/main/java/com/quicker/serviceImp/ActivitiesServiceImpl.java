package com.quicker.serviceImp;

import com.quicker.dao.ActivitiesDao;
import com.quicker.database.Activity;
import com.quicker.database.StuDepartment;
import com.quicker.entity.json.BasicJson;
import com.quicker.entity.json.Errmsg;
import com.quicker.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Nanguoyu on 2016/7/13.
 */
@Service
public class ActivitiesServiceImpl implements ActivitiesService {


    @Autowired
    private ActivitiesDao activitiesDao;

    //获取我的关注
    @Override
    public BasicJson getMyActivities(String id) {
        BasicJson basicJson = new BasicJson();

        if (activitiesDao.getMyActivities(id) != null) {
            basicJson.setStatus(true);
            List<String> activitiList = activitiesDao.getMyActivities(id);
            basicJson.setJsonString(activitiList);
        } else {
            basicJson.setStatus(false);
            Errmsg errmsg = new Errmsg();
            errmsg.setDescription("获取我的关注失败");
            basicJson.setErrorMsg(errmsg);
        }
        return basicJson;
    }

    //获取热门活动(按照点击量)
    @Override
    public BasicJson getHotActivities() {
        BasicJson basicJson = new BasicJson();
        List<String> hotActivitiList;
        if ((hotActivitiList = activitiesDao.getHotActivities()) != null) {
            basicJson.setStatus(true);
            basicJson.setJsonString(hotActivitiList);
        } else {
            basicJson.setStatus(false);
            Errmsg errmsg = new Errmsg();
            errmsg.setDescription("获取热门活动失败");
            basicJson.setErrorMsg(errmsg);
        }
        return basicJson;
    }

    //获取最新活动（按照发布时间排序）
    @Override
    public BasicJson getNewstActivities() {
        BasicJson basicJson = new BasicJson();
        List<String> newstActivitiList;
        if ((newstActivitiList = activitiesDao.getNewstActivities()) != null) {
            basicJson.setStatus(true);
            basicJson.setJsonString(newstActivitiList);

        } else {
            basicJson.setStatus(false);
            Errmsg errmsg = new Errmsg();
            errmsg.setDescription("获取最新活动失败");
            basicJson.setErrorMsg(errmsg);
        }
        return basicJson;
    }

    //将某活动设置为自己的关注
    @Override
    public BasicJson setActivityAsMy(String stuId, String activityTitle) {
        BasicJson basicJson = new BasicJson();
        if (activitiesDao.setActivityAsMy(stuId, activityTitle)) {
            basicJson.setStatus(false);
            Errmsg errmsg = new Errmsg();
            errmsg.setDescription("操作失败");
            basicJson.setErrorMsg(errmsg);
        } else {
            basicJson.setStatus(true);
        }
        return basicJson;
    }

    //查看某具体的活动信息
    @Override
    public BasicJson getOneActivity(String activityTitleGB2312) {
        BasicJson basicJson = new BasicJson();
        Map<String, String> activityInfo;
        if ((activityInfo = activitiesDao.getOneActivity(activityTitleGB2312)) != null) {
            basicJson.setStatus(true);
            basicJson.setJsonString(activityInfo);
        } else {
            basicJson.setStatus(false);
            Errmsg errmsg = new Errmsg();
            errmsg.setDescription("获取失败");
            basicJson.setErrorMsg(errmsg);
        }
        return basicJson;
    }

    //查看所有的学生组织
    @Override
    public BasicJson getAllDepartments() {
        BasicJson basicJson = new BasicJson();
        List<StuDepartment> stuDepartmentList;
        if ((stuDepartmentList = activitiesDao.getAllDepartments()) != null) {
            List<String> departmentList = new ArrayList<String>();
            for (int i = 0; i < stuDepartmentList.size(); i++) {
                departmentList.add(stuDepartmentList.get(i).getDepartmentName());
            }
            basicJson.setStatus(true);
            basicJson.setJsonString(departmentList);
        } else {
            basicJson.setStatus(false);
            Errmsg errmsg = new Errmsg();
            errmsg.setDescription("获取失败");
            basicJson.setErrorMsg(errmsg);
        }
        return basicJson;
    }

    //获取某个部门组织的活动通知列表
    @Override
    public BasicJson getDepartmentActivities(String department) {
        BasicJson basicJson = new BasicJson();
        List<Activity> activityList;
        if ((activityList = activitiesDao.getDepartmentActivities(department)) != null) {
            List<String> someDepActiList = new ArrayList<String>();
            for (int i = 0; i < activityList.size(); i++) {
                someDepActiList.add(activityList.get(i).getTitle());
            }
            basicJson.setStatus(true);
            basicJson.setJsonString(someDepActiList);
        } else {
            basicJson.setStatus(false);
            Errmsg errmsg = new Errmsg();
            errmsg.setDescription("获取失败");
            basicJson.setErrorMsg(errmsg);
        }
        return basicJson;
    }

    //学生组织发送活动等信息
    @Override
    public BasicJson departSendActivity(String title, String contents) {
        BasicJson basicJson = new BasicJson();
        Activity activity = new Activity();
        activity.setTitle(title);
        activity.setContent(contents);
        activity.setPublishTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        activity.setClickNum(0);
        boolean isSucceed;

        //暂时写死
        activity.setBelongDepar("学生会体育部");

        if ((isSucceed = activitiesDao.departSendActivity(activity))) {
            basicJson.setStatus(isSucceed);
        }
        else {
            basicJson.setStatus(false);
            Errmsg errmsg = new Errmsg();
            errmsg.setDescription("发送失败");
            basicJson.setErrorMsg(errmsg);
        }
        return basicJson;
    }
}
