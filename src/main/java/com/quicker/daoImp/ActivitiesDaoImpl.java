package com.quicker.daoImp;

import com.quicker.dao.ActivitiesDao;
import com.quicker.database.*;
import com.quicker.util.CompareUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Nanguoyu on 2016/7/13.
 */
@Repository
public class ActivitiesDaoImpl implements ActivitiesDao {


    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    //获取我的关注（活动模块中）
    @Override
    public List<String> getMyActivities(String id) {
        Session session = getSession();
        String hql = "From StuActivity s WHERE s.stuId=:id and s.isCollected=1";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List<StuActivity> stuActivityList = query.list();
            List<String> activityList = new ArrayList<String>();
            for (int i = 0; i < stuActivityList.size(); i++) {
                activityList.add(stuActivityList.get(i).getActivityTitle());
            }
            return activityList;
        } catch (Exception e) {
            return null;
        }
    }

    //获取热门信息
    @Override
    public List<String> getHotActivities() {
        Session session = getSession();
        String hql = "FROM Activity";
        try {
            List<Activity> activityList = session.createQuery(hql).list();
            Integer[] clickNums = new Integer[activityList.size()];
            for (int i = 0; i < activityList.size(); i++) {
                clickNums[i] = activityList.get(i).getClickNum();
            }
            Arrays.sort(clickNums);
            List<String> hotActivityList = new ArrayList<String>();
            for (int j = activityList.size() - 1; j >= 0; j--) {
                for (int n = 0; n < activityList.size(); n++) {
                    if (activityList.get(n).getClickNum() == clickNums[j]) {
                        hotActivityList.add(activityList.get(n).getTitle());
                        activityList.remove(n);
                        break;
                    }
                }
            }
            return hotActivityList;
        } catch(Exception e) {
            return null;
        }
    }

    //获取最新活动（按照发布时间排序）
    @Override
    public List<String> getNewstActivities() {
        Session session = getSession();
        String hql = "FROM Activity";
        try {
            List<Activity> activityList = session.createQuery(hql).list();
            System.out.println("activitieList大小为" + activityList.size());
            List<Date> timeList = new ArrayList<Date>();
            List<String> timeActivityList = new ArrayList<String>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < activityList.size(); i++) {
                timeList.add(sdf.parse(activityList.get(i).getPublishTime()));
            }

            Collections.sort(timeList,new CompareUtil());

            System.out.println("经过collections的排序：========");
            for (int j = 0; j < timeList.size(); j++) {
                System.out.println(timeList.get(j));
            }

            for (int m = timeList.size() - 1; m >= 0; m--) {
                String date = sdf.format(timeList.get(m));
                for (int n = 0; n < activityList.size(); n++) {
                    if (date.equals(activityList.get(n).getPublishTime())) {
                        timeActivityList.add(activityList.get(n).getTitle());
                        activityList.remove(n);
                        break;
                    }
                }
            }
            return timeActivityList;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //将某活动设置为我的关注
    @Override
    public boolean setActivityAsMy(String stuId, String activityTitle) {
        Session session = getSession();
        try {
            String hql = "FROM StuActivity s WHERE s.activityTitle=:activityTitle and s.stuId=:stuId";
            Query query = session.createQuery(hql);
            query.setParameter("activityTitle",activityTitle);
            query.setParameter("stuId",stuId);
            List<StuActivity> stuActivityList = new ArrayList<StuActivity>();
            stuActivityList.get(0).setIsCollected(1);
            session.saveOrUpdate(stuActivityList.get(0));
            session.flush();
            return true;
        } catch(Exception e) {
            return false;
        }
    }


    //查看某具体的活动信息
    @Override
    public Map<String, String> getOneActivity(String activityTitleGB2312) {
        Session session = getSession();
        Map<String,String> activityInfo = new HashMap<String, String>();
        try {
            String hql ="FROM Activity a WHERE a.title=:activityTitleGB2312";
            Query query = session.createQuery(hql);
            query.setParameter("activityTitleGB2312",activityTitleGB2312);
            List<Activity> activityList = query.list();
            activityInfo.put("标题",activityList.get(0).getTitle());
            activityInfo.put("内容",activityList.get(0).getContent());
            activityInfo.put("发布组织",activityList.get(0).getBelongDepar());
            activityInfo.put("点击量",String.valueOf(activityList.get(0).getClickNum()));
            activityInfo.put("发布时间",activityList.get(0).getPublishTime());
            activityList.get(0).setClickNum(activityList.get(0).getClickNum() + 1);
            return activityInfo;
        } catch (Exception e) {
            return null;
        }
    }

    //查看所有的学生组织
    @Override
    public List<StuDepartment> getAllDepartments() {
        Session session = getSession();
        try {
            String hql = "FROM StuDepartment";
            List<StuDepartment> stuDepartmentList = session.createQuery(hql).list();
            return stuDepartmentList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //获取某个部门组织的活动通知列表
    @Override
    public List<Activity> getDepartmentActivities(String department) {
        Session session = getSession();
        Activity activity = new Activity();
        try {
            String hql = "FROM Activity a WHERE a.belongDepar=:department";
            Query query = session.createQuery(hql);
            query.setParameter("department",department);
            List<Activity> activitieList = query.list();
            return activitieList;
        } catch (Exception e) {
            return null;
        }
    }


    //学生组织发送活动等信息
    @Override
    public boolean departSendActivity(Activity activity) {
        Session session = getSession();
        try {

            //存储活动
            session.save(activity);

            //记录活动与同学的关系
            String hql1 = "FROM StudentInfo";
            List<StudentInfo> studentInfos = session.createQuery(hql1).list();
            StuActivity stuActivity;
            System.out.println("studentInfos的大小为:" + studentInfos.size());
            for (int i = 0; i < studentInfos.size(); i++) {
                stuActivity = new StuActivity();
                stuActivity.setIsCollected(0);
                stuActivity.setActivityTitle(activity.getTitle());
                stuActivity.setStuId(studentInfos.get(i).getUsername());
                session.save(stuActivity);
            }
            session.flush();
            return true;
        } catch(Exception e) {
            return false;
        }
    }


}
