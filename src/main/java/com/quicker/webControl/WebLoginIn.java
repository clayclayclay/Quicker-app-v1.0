package com.quicker.webControl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quicker.daoImp.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.quicker.entity.json.BasicJson;
import com.quicker.service.LoginInService;
import com.quicker.util.GsonUtil;


@Controller
@RequestMapping("/web")
public class WebLoginIn {
	
	@Autowired
	private LoginInService loginInService;

	//web端辅导员登陆接口
	@RequestMapping(value = "/index",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		System.out.println(username);
		String passwd = request.getParameter("passwd");
		System.out.println(passwd);
		BasicJson basicJson;
		basicJson = loginInService.webLoginIn(username, passwd);
		if (basicJson.isStatus()) {
			return "/pages_counselor/index";
		}
		return "/pages_counselor/login_failure";
	}

	//学生组织登陆接口
	@RequestMapping("/departLogin")
	public String departLogin(HttpServletRequest request) {
		String username = request.getParameter("username");
		System.out.println(username);

		String password = request.getParameter("passwd");
		System.out.println(password);

		if (username.equals("123456") && password.equals("123456")) {
			return "/pages_department/check_export";
		}
		return "/pages_department/login_failure";
	}

//	@RequestMapping("/test")
//	public void test()  {
//		UserDaoImpl userDao = new UserDaoImpl();
//		userDao.test();
//	}
}
