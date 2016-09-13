package com.quicker.serviceImp;

import com.quicker.dao.NoticeDao;
import com.quicker.database.Activity;
import com.quicker.database.Notice;
import com.quicker.database.StuNotice;
import com.quicker.entity.json.BasicJson;
import com.quicker.entity.json.Errmsg;
import com.quicker.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nanguoyu on 2016/7/10.
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    //获取未读通知列表
    @Override
    public List<String> getNoFinishedCounselorNotices(String id) {
        List<StuNotice> stuNoticeList = noticeDao.getNoFinishedCounselorNotices(id);
        List<String> noticeTitleList = new ArrayList<String>();
        for (int i = 0; i < stuNoticeList.size(); i++) {
            noticeTitleList.add(stuNoticeList.get(i).getNoticeTitle());
        }
        return noticeTitleList;
    }

    //获取已读通知列表
    @Override
    public List<String> getFinishedCounselorNotice(String id) {
        List<StuNotice> stuNoticeList = noticeDao.getFinishedCounselorNotice(id);
        List<String> noticeTitleList = new ArrayList<String>();
        for (int i = 0; i < stuNoticeList.size(); i++) {
            noticeTitleList.add(stuNoticeList.get(i).getNoticeTitle());
        }
        return noticeTitleList;
    }

    //获取一条通知
    @Override
    public BasicJson getOneCounselorNotice(String stuId,String noticeTitle) {
        BasicJson basicJson = new BasicJson();
        List<Notice> stuNoticeList = noticeDao.getOneCounselorNotice(stuId,noticeTitle);
        Notice notice = stuNoticeList.get(0);
        String content = notice.getContent();
        basicJson.setStatus(true);
        basicJson.setJsonString(content);
        return basicJson;
    }

    //辅导员发布通知
    @Override
    public BasicJson postNoticeCounselor(String title, String contents) {
        BasicJson basicJson = new BasicJson();
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(contents);
        boolean isSucceed;
        if ((isSucceed = noticeDao.postNoticeCounselor(notice))) {
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
