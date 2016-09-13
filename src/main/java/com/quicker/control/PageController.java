package com.quicker.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class PageController {
	
	@RequestMapping("/login")
	public String login() {
		return "/pages_counselor/login";
	}

	@RequestMapping("/post_notice")
	public String sendNotice() {
		return "/pages_counselor/post_notice";
	}

	@RequestMapping("/set_agent")
	public String monitor() {
		return "/pages_counselor/set_agent";
	}

	@RequestMapping("/depart")
	public String deaprtLogin(){
		return "/pages_department/login";
	}

	@RequestMapping("/post_notice_depart")
	public String postNoticeDepart() {
		return "/pages_department/post_notice";
	}
}
