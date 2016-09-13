package com.quicker.appControl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quicker.database.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicker.entity.json.BasicJson;
import com.quicker.service.LoginInService;
import com.quicker.util.GsonUtil;

@Controller
@RequestMapping("/app")
public class AppLoginInController {

	@Autowired
	private LoginInService loginInService;

	//app端学生登陆接口
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + password);
		BasicJson json;
		json = loginInService.appLoginIn(username, password);
		String jsonString = GsonUtil.toJson(json);

		Activity activity = new Activity();


		System.out.println(activity.getClass().getName());


		return jsonString;
	}
}
















