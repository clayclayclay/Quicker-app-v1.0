package com.quicker.webControl;

/**
 * Created by Nanguoyu on 2016/7/10.
 */

import com.quicker.entity.json.BasicJson;
import com.quicker.service.NoticeService;
import com.quicker.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/web")
public class WebNoticeController {

    @Autowired
    private NoticeService noticeService;


    //辅导员发送通知
    @RequestMapping(value = "/postNoticeCounselor", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String postNoticeCounselor(HttpServletRequest request) {
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        System.out.println("title的值为:" + title);
        System.out.println("contents的值为:" + contents);
        BasicJson basicJson;
        basicJson = noticeService.postNoticeCounselor(title,contents);
        String json = GsonUtil.toJson(basicJson);
        System.out.println("postNoticeCounselor接口测试返回json为:" + json);
        return json;
    }
}
