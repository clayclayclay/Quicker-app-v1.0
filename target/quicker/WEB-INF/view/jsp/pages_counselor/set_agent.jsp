<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>班长</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<link rel="stylesheet" type="text/css" href= "<%=basePath %>view/css/cssreset.css">
  	<link rel="stylesheet" type="text/css" href= "<%=basePath %>view/css/monitor.css">
  	<link rel="stylesheet" type="text/css"  media="all" href="<%=basePath %>view/css/font-awesome.min.css">
	  <link href="/quicker/view/images/logo_ico.png" rel="shortcut icon" />
  </head>

  <body>
  <div class="header">
	  <div class="headtop">
			<span class="headscan">
				<p><img src="/quicker/view/images/logo.png">Uban<span>和你一起在乎你</span></p>
				</p>
				</span>
	  </div>
	  <div class="headbot">
		  <div class="navigation">
			  <ul>
				  <li>
					  <a href="post_notice" ><i class="icon-edit icon-4x"></i>
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
	  <div class="con-one"><span>已有班长:</span></div>
	  <div class="con-two">
		  <p>年级:
			  <select class="年级">
				  <option value="大一">大一</option>
				  <option value="大二">大二</option>
				  <option value="大三">大三</option>
				  <option value="大四">大四</option>
			  </select>
		  </p>
		  <p>专业:
			  <select class="专业" style="height:25px;width:100px;">
				  <option value="信息工程">信息工程</option>
				  <option value="软件技术工程">软件技术工程</option>
				  <option value="嵌入式系统">嵌入式系统</option>
				  <option value="大型主机">大型主机</option>
				  <option value="网络安全">网络安全</option>
				  <option value="信息获取">信息获取</option>
				  <option value="计算机产品与工业辅助设计">计算机产品与工业辅助设计</option>
				  <option value="数字动漫">数字动漫</option>
			  </select>
		  </p>
		  <p><button>确定</button></p>

		  <table>
			  <tr class="tr1">
				  <td class="td1">班级</td>
				  <td class="td2">姓名</td>
				  <td class="td3">学号</td>
			  </tr>
			  <tr class="tr2">
				  <td class="td1">信工1班</td>
				  <td class="td2">尹怀可</td>
				  <td class="td3">2014220101001</td>
			  </tr>
			  <tr class="tr3">
				  <td class="td1">信工2班</td>
				  <td class="td2">邓锦</td>
				  <td class="td3">2014220102001</td>
			  </tr>
			  <tr class="tr4">
				  <td class="td1">信工3班</td>
				  <td class="td2">吕笑千</td>
				  <td class="td3">2014220103001</td>
			  </tr>
			  <tr class="tr5">
				  <td class="td1">信工4班</td>
				  <td class="td2">王杰</td>
				  <td class="td3">2014220104001</td>
			  </tr>
			  <tr class="tr6">
				  <td class="td1">信工5班</td>
				  <td class="td2">郝萌</td>
				  <td class="td3">2014220105001</td>
			  </tr>
		  </table>
	  </div>
	  <div class="con-three"><span>更改班长:</span></div>
	  <div class="con-four">
		  <div class="con-four-one">
			  <p>年级:
				  <select class="grade">
					  <option value="大一">大一</option>
					  <option value="大二">大二</option>
					  <option value="大三">大三</option>
					  <option value="大四">大四</option>
				  </select>
			  </p>
			  <p>专业:
				  <select class="major" style="height:25px;width:100px;">
					  <option value="信息工程">信息工程</option>
					  <option value="软件技术工程">软件技术工程</option>
					  <option value="嵌入式系统">嵌入式系统</option>
					  <option value="大型主机">大型主机</option>
					  <option value="网络安全">网络安全</option>
					  <option value="信息获取">信息获取</option>
					  <option value="计算机产品与工业辅助设计">计算机产品与工业辅助设计</option>
					  <option value="数字动漫">数字动漫</option>
				  </select>
			  </p>
			  <p>班级:
				  <select class="oclass">
					  <option value="一班">一班</option>
					  <option value="二班">二班</option>
					  <option value="三班">三班</option>
					  <option value="四班">四班</option>
					  <option value="五班">五班</option>
				  </select>
			  </p>
			  <p>姓名: <input type="text" name="fname" style="height:25px;width:200px;" /></p>
			  <p>学号: <input type="text" name="lname" style="height:25px;width:200px;"/></p>
			  <p><button>更改</button></p>
		  </div>
	  </div>
  </div>
  <div class="footer">
	  <center>
		  <p>
			  电子科技大学|信息与软件工程学院|择栖团队
		  </p>
	  </center>
  </div>
  </body>
</html>
