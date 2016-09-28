<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
	<title>发送通知</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="/quicker/view/css/cssreset.css">
	<link rel="stylesheet" type="text/css" href="/quicker/view/css/sendNotice.css">
	<link rel="stylesheet" type="text/css"  media="all" href="/quicker/view/css/font-awesome.min.css">
	<script type="text/javascript" src="/quicker/view/jQuery/jquery-2.1.0.min.js"></script>
	<link href="/quicker/view/images/logo_ico.png" rel="shortcut icon" />
</head>
<body>
<p class="suup smaview">发送成功</p>
<p class="errup smaview" ></p>
<div class="header">
	<div class="headtop">
			<span class="headspan">
				<img src="/quicker/view/images/logo.png">Quiker<span>The Quicker, the richer</span>
			</span>
	</div>
	<div class="headbot">
		<div class="navigation">
			<ul>
				<li>
					<a href="post_notice" class="this"><i class="icon-edit icon-4x"></i>
						<span>发放通知</span>
					</a>
				</li>
				<li>
					<a href="check_export" ><i class="icon-folder-open icon-4x"></i>
						<span>查看通知</span>
					</a>
				</li>
				<li>
					<a href="set_agent"><i class="icon-user-md icon-4x"></i>
						<span>班级管理员设置</span>
					</a>

				</li>
			</ul>
		</div>
	</div>
</div>
</div>
<div class="container">
	<div class="smenu">
		<p class="stitle">发送通知公告</p>
		<i class="icon-angle-up icon-1x upmenu"></i>
		<form class="moreview" id="form2" method="post">
			<p class="stags">
				<span>通知标题</span>
				<input type="text" class="intitle">
			</p>
			<p class="stag">
				<span>通知内容</span>
				<textarea rows="20" cols="100" id="contents"></textarea>
			</p>
			<button id="upviews">发送通知</button>
		</form>
	</div>
	<div class="smenu">
		<p class="stitle">上传填写表格文件</p>
		<i class="icon-angle-up icon-1x upmenu"></i>
		<form id="form1" enctype="multipart/form-data" method="post" class="moreview">
			<div class="row">
				<label for="fileToUpload">选择上传的文件</label>
				<input type="file" name="file" id="fileToUpload" onchange="fileSelected();"/>
				<div class="hybutton">浏览...</div>
			</div>
			<div id="fileName" class="fileview"></div>
			<div id="fileSize" class="fileview"></div>
			<div id="fileType" class="fileview"></div>
			<div class="row">
				<input type="button" onclick="uploadFile()" value="Upload" />
			</div>
			<div id="progressNumber"></div>
		</form>



	</div>



</div>
<div class="footer">

	<p>
		电子科技大学|信息与软件工程学院|择栖团队
	</p>
</div>
<script type="text/javascript" src="/quicker/view/js/upnews.js"></script>
</body>
</html>
