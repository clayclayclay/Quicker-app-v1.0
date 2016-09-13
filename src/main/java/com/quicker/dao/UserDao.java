package com.quicker.dao;

import java.util.List;

import com.quicker.database.CounselorInfo;
import com.quicker.database.StudentInfo;

public interface UserDao {
	List<StudentInfo> appLogin(String username);
	List<CounselorInfo> webLogin(String username);
	List<StudentInfo> getAllUser();
}
