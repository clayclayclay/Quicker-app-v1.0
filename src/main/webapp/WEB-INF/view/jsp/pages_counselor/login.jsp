<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>辅导员登录界面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/quicker/view/css/cssreset.css" />
<link rel="stylesheet" type="text/css" href="/quicker/view/css/teacher.css" />
<link rel="stylesheet" type="text/css" media="all"
	href="/quicker/view/css/font-awesome.min.css" />
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
	<div class="cf">
		<div class="enter">
				<span class="enterspan"> <img src="/quicker/view/images/logo.png" />
					<p>登录窗口</p>
				</span>
			<form method="post" action="./index">
				<div class="spand">
					<span><i class="icon-user"></i></span> <input type="text"
																  name="username" placeholder=" 用户名" />
				</div>
				<div class="spand">
					<span><i class="icon-lock"></i></span> <input type="password"
																  name="passwd" placeholder=" 密码" />
				</div>

				<button id="submit">登录</button>
				<a href="#">忘记密码？</a>
			</form>
		</div>
	</div>
	<div class="background"></div>
</div>

<script type="text/javascript">

	$("#submit").click(function() {
		var username = $("#username").val();
		var passwd = $("#passwd").val();
		if (username == "") {
			alert("输入用户名吧，亲！");
			return false;
		}
		else if (passwd == "") {
			alert("输入密码吧，亲！");
			return false;
		}

	})
</script>
</body>


</html>