package com.quicker.appControl;

import com.quicker.entity.json.BasicJson;
import com.quicker.service.NoticeService;
import com.quicker.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nanguoyu on 2016/7/10.
 */
@Controller
@RequestMapping("/app")
public class AppNoticeController {


    @Autowired
    private NoticeService noticeService;


    //获取未读通知列表
    public List<String> getNoFinishedCounselorNotices(String id) {
        List<String> noticeTitle = noticeService.getNoFinishedCounselorNotices(id);
        return noticeTitle;
    }


    //获取已读通知列表
    public List<String> getFinishedCounselorNotice(String id) {
        List<String> noticeTitle = noticeService.getFinishedCounselorNotice(id);
        return noticeTitle;
    }

    //获取两种状态（未读，已读）的通知列表
    @RequestMapping(value = "/getAllStageNotice/{id}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllStageNotice(@PathVariable String id) {
        List<String> noticeNo = getNoFinishedCounselorNotices(id);
        List<String> noticeDone = getFinishedCounselorNotice(id);
        List<List<String>> noticeList = new ArrayList<List<String>>();
        noticeList.add(noticeNo);
        noticeList.add(noticeDone);
        BasicJson basicJson = new BasicJson();
        basicJson.setStatus(true);
        basicJson.setJsonString(noticeList);
        String json = GsonUtil.toJson(basicJson);
        System.out.println("getAllStageNotice接口返回的json信息为：" + json);
        return json;
    }

    //获取一条通知
    @RequestMapping(value = "/getOneCounselorNotice",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getOneCounselorNotice(HttpServletRequest request) {
        String stuId = request.getParameter("id");

        String noticeTitle = request.getParameter("noticeTitle");
        try {
            String noticeTitleGB2312 = new String(noticeTitle.getBytes("GB2312"),"GB2312");
            BasicJson basicJson = new BasicJson();
            basicJson = noticeService.getOneCounselorNotice(stuId,noticeTitle);
            String json = GsonUtil.toJson(basicJson);
            System.out.println("getOneCounselorNotice:" + json);
            return json;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
