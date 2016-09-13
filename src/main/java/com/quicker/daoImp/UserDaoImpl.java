package com.quicker.daoImp;

import java.util.List;

import com.quicker.database.ClassInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quicker.dao.UserDao;
import com.quicker.database.CounselorInfo;
import com.quicker.database.StudentInfo;

@Repository("loginInDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {

		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	//app端学生登陆
	public List<StudentInfo> appLogin(String username) {
		Session session = getSession();
		String hql = "from StudentInfo s where s.username=:username";
		List list = session.createQuery(hql).setString("username", username)
				.list();
		return list;
	}

	//we端 辅导员/学生组织 登陆
	public List<CounselorInfo> webLogin(String username) {
		Session session = getSession();
		String hql = "from CounselorInfo c where c.username=:username";
		List<CounselorInfo> list = session.createQuery(hql).setString("username", username)
				.list();
		return list;
	}



	@Override
	public List<StudentInfo> getAllUser() {
		Session session = getSession();
		String hql = "FROM StudentInfo";
		List<StudentInfo> studentList = session.createQuery(hql).list();
		return studentList;
	}
}


