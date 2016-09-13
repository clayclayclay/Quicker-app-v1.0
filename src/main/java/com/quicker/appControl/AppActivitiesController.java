package com.quicker.appControl;

import com.quicker.entity.json.BasicJson;
import com.quicker.entity.json.Errmsg;
import com.quicker.service.ActivitiesService;
import com.quicker.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by Nanguoyu on 2016/7/13.
 */
@Controller
@RequestMapping("/app")
public class AppActivitiesController {

    @Autowired
    private ActivitiesService activitiesService;



    //获取我的关注
    @RequestMapping(value = "/getMyActivities/{id}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMyActivities(@PathVariable String id) {
        BasicJson basicJson;
        basicJson = activitiesService.getMyActivities(id);
        String json = GsonUtil.toJson(basicJson);
        System.out.println("getMyActivities接口返回的json值为:" + json);
        return json;

    }


    //获取热门活动（按照点击量）
    @RequestMapping(value = "/getHotActivities" , produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getHotActivities() {
        BasicJson basicJson;
        basicJson = activitiesService.getHotActivities();
        String json = GsonUtil.toJson(basicJson);
        System.out.println("getHotActivities接口返回的json信息为:" + json);
        return json;
    }


    //获取最新活动（按照发布时间）
    @RequestMapping(value = "/getNewstActivities",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getNewstActivities() {
        BasicJson basicJson;
        basicJson = activitiesService.getNewstActivities();
        String json = GsonUtil.toJson(basicJson);
        System.out.println("getNewstActivities接口返回的json信息为:" + json);
        return json;
    }


    //将某活动信息设置为我的关注
    @RequestMapping(value = "/setActivityAsMy",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String setActivityAsMy(HttpServletRequest request) {
        String stuId = request.getParameter("id");
        String activityTitle = request.getParameter("activityTitle");
        BasicJson basicJson;
        basicJson = activitiesService.setActivityAsMy(stuId,activityTitle);
        String json = GsonUtil.toJson(basicJson);
        System.out.println("setActivityAsMy接口返回的json信息为:" + json);
        return json;
    }

    //查看某具体的活动信息
    @RequestMapping(value = "/getOneActivity/{activityTitle}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getOneActivity(@PathVariable String activityTitle) {
        try {
            String activityTitleGB2312 = new String(activityTitle.getBytes("GB2312"),"GB2312");
            System.out.println("经过编码之后的title为:" + activityTitleGB2312);
            BasicJson basicJson;
            basicJson = activitiesService.getOneActivity(activityTitleGB2312);
            String json = GsonUtil.toJson(basicJson);
            System.out.println("getOneActivity接口返回的json信息为:" + json);
            return json;
        } catch (UnsupportedEncodingException e) {
            BasicJson basicJson = new BasicJson();
            basicJson.setStatus(false);
            Errmsg errmsg = new Errmsg();
            errmsg.setDescription("编码转换失败");
            basicJson.setErrorMsg(errmsg);
            String json = GsonUtil.toJson(basicJson);
            System.out.println("getOneActivity接口返回的json信息为:" + json);
            return json;
        }
    }

    //查看所有的学生组织
    @RequestMapping(value = "/getAllDepartments",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllDepartments() {
        BasicJson basicJson;
        basicJson = activitiesService.getAllDepartments();
        String json = GsonUtil.toJson(basicJson);
        System.out.println("getAllDepartments接口返回的json信息为:" + json);
        return json;
    }


    //获取某个部门组织的活动通知列表
    @RequestMapping(value = "/getDepartmentActivities/{department}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getDepartmentActivities(@PathVariable String department) {
        BasicJson basicJson;
        basicJson = activitiesService.getDepartmentActivities(department);
        String json = GsonUtil.toJson(basicJson);
        System.out.println("getAllDepartments接口返回的json信息为:" + json);
        return json;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Object test() {
        BasicJson basicJson = new BasicJson();
        basicJson.setStatus(true);
        basicJson.setJsonString("hahaha");

        return basicJson;

    }
}
