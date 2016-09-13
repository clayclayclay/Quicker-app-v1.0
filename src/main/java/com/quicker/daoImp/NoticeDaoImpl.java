package com.quicker.daoImp;

import com.quicker.dao.NoticeDao;
import com.quicker.database.Notice;
import com.quicker.database.StuNotice;
import com.quicker.database.StudentInfo;
import com.quicker.entity.json.BasicJson;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nanguoyu on 2016/7/10.
 */
@Repository
public class NoticeDaoImpl implements NoticeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }



    //获取未读通知列表
    @Override
    public List<StuNotice> getNoFinishedCounselorNotices(String id) {
        Session session = getSession();
        String hql = "FROM StuNotice s WHERE s.stuId=:id and s.isRead=0";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        List<StuNotice> stuNoticeList = query.list();
        return stuNoticeList;
    }

    //获取已读通知列表
    @Override
    public List<StuNotice> getFinishedCounselorNotice(String id) {
        Session session = getSession();
        String hql = "FROM StuNotice s WHERE s.stuId=:id and s.isRead=1";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        List<StuNotice> stuNoticeList = query.list();
        return stuNoticeList;
    }

    //获取一条通知
    @Override
    public List<Notice> getOneCounselorNotice(String stuId,String noticeTitle) {
        Session session = getSession();
        String hql = "FROM StuNotice s WHERE s.stuId=:id and s.noticeTitle=:noticeTitle";
        Query query = session.createQuery(hql);
        query.setParameter("id",stuId);
        query.setParameter("noticeTitle", noticeTitle);
        List<StuNotice> stuNoticeList = query.list();

        //如果之前没读过，则要标记为读过
        if (stuNoticeList.get(0).getIsRead() == 0) {
            System.out.println("isRead!!!!!!!!!");
            StuNotice stuNotice = stuNoticeList.get(0);
            stuNotice.setIsRead(1);
            session.saveOrUpdate(stuNotice);
            session.flush();
            String hql1 = "FROM Notice n WHERE n.title=:noticeTitle";
            query = session.createQuery(hql1);
            query.setParameter("noticeTitle",noticeTitle);
            List<Notice> notice = query.list();
            return notice;
        }
        else {
            System.out.println("~~~~~~~");
            String hql1 = "FROM Notice n WHERE n.title=:noticeTitle";
            query = session.createQuery(hql1);
            query.setParameter("noticeTitle",noticeTitle);
            List<Notice> notice = query.list();
            return notice;
        }
    }


    //辅导员发布通知(存储通知，记录通知与同学的关系)
    @Override
    public boolean postNoticeCounselor(Notice notice) {
        Session session = getSession();
        try {

            //存储通知
            session.save(notice);

            //记录通知与同学的关系
            String hql1 = "FROM StudentInfo";
            List<StudentInfo> studentInfos = session.createQuery(hql1).list();
            StuNotice stuNotice;
            for (int i = 0; i < studentInfos.size(); i++) {
                stuNotice = new StuNotice();
                stuNotice.setIsRead(0);
                stuNotice.setNoticeTitle(notice.getTitle());
                stuNotice.setStuId(studentInfos.get(i).getUsername());
                session.save(stuNotice);
            }
            session.flush();
            return true;
        } catch(Exception e) {
            return false;
        }
    }


}
