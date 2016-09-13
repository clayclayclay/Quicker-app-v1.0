package com.quicker.service;

import com.quicker.entity.json.BasicJson;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Nanguoyu on 2016/7/10.
 */
public interface NoticeService {

    //获取未读通知列表
    List<String> getNoFinishedCounselorNotices(String id);

    //获取已读通知列表
    List<String> getFinishedCounselorNotice(String id);

    //获取一条通知
    BasicJson getOneCounselorNotice(String stuId,String noticeTitle);

    //辅导员发布通知
    BasicJson postNoticeCounselor(String title,String contents);

}
