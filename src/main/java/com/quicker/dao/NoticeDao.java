package com.quicker.dao;

import com.quicker.database.Activity;
import com.quicker.database.Notice;
import com.quicker.database.StuNotice;
import com.quicker.entity.json.BasicJson;

import java.util.List;
import java.util.Map;

/**
 * Created by Nanguoyu on 2016/7/10.
 */
public interface NoticeDao {

    //获取未读通知列表
    List<StuNotice> getNoFinishedCounselorNotices(String id);

    //获取已读通知列表
    List<StuNotice> getFinishedCounselorNotice(String id);

    //获取一条通知
    List<Notice> getOneCounselorNotice(String stuId,String noticeTitle);

    //辅导员发布通知
    boolean postNoticeCounselor(Notice notice);

}
