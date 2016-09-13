package com.quicker.webControl;

/**
 * Created by Nanguoyu on 2016/7/14.
 */

import com.quicker.entity.json.BasicJson;
import com.quicker.service.ActivitiesService;
import com.quicker.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/web")
public class WebActivityController {

    @Autowired
    private ActivitiesService activitiesService;

    //学生组织发布活动等信息
    @RequestMapping(value = "/departSendActivity",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String departSendActivity(HttpServletRequest request) {
        System.out.println("收到请求！！！");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        System.out.println("title的值为:" + title);
        System.out.println("contents的值为:" + contents);
        BasicJson basicJson;
        basicJson = activitiesService.departSendActivity(title,contents);
        String json = GsonUtil.toJson(basicJson);
        System.out.println("departSendActivity接口测试返回json为:" + json);
        return json;
    }





}
