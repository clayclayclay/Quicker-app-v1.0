<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>查看班级</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<link rel="stylesheet" type="text/css" href="/quicker/view/css/cssreset.css">
  	<link rel="stylesheet" type="text/css" href="/quicker/view/css/menu.css">
  	<link rel="stylesheet" type="text/css"  media="all" href="/quicker/view/css/font-awesome.min.css">

	  <script type="text/javascript" src="/quicker/view/jQuery/jquery-2.1.0.min.js"></script>
	  <link href="/quicker/view/images/logo_ico.png" rel="shortcut icon" />
  </head>

  <body>
  <div class="header">
	  <div class="smahead">
			<span class="headspan">
				<p>
					<img src="/quicker/view/images/logo.png" />Uban<span>和你一起在乎你</span>
				</p>
			</span>

	  </div>
  </div>
  <div class="container">
	  <a class="smallMenu" href="post_notice">
		  <i class="icon-edit icon-4x"></i>
		  <p>发送通知</p>
	  </a>
	  <a class="smallMenu" href="check_export">
		  <i class="icon-folder-open icon-4x"></i>
		  <p>查看通知</p>
	  </a>
	  <a class="smallMenu" href="set_agent">
		  <i class="icon-user-md icon-4x"></i>
		  <p>班级管理员设置</p>
	  </a>

  </div>
  <div class="footer">
	  <p>
		  电子科技大学|信息与软件工程学院|择栖团队
	  </p>
  </div>

  </body>
  <script type="text/javascript">
	  $().ready(function(){
		  var heigh = $(window).height();
		  if(heigh>327){
			  $(".footer").css({
				  top:heigh-60
			  });
		  };
	  })
  </script>
</html>
