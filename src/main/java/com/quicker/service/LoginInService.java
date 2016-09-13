package com.quicker.service;

import com.quicker.entity.json.BasicJson;

public interface LoginInService {

	//app端登陆接口
	BasicJson appLoginIn(String username,String password);

	//web端登陆接口
	BasicJson webLoginIn(String username,String password);
}
