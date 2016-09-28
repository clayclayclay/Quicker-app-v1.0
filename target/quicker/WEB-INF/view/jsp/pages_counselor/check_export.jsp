<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
         contentType="text/html; charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%
    String path = request
            .getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>查看通知</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css"
          href="/quicker/view/css/cssreset.css">
    <link rel="stylesheet" type="text/css"
          href="/quicker/view/css/check.css">
    <link rel="stylesheet" type="text/css" media="all"
          href="/quicker/view/css/font-awesome.min.css">
    <script type="text/javascript" src="/quicker/view/jQuery/jquery-2.1.0.min.js"></script>
    <script type="text/javascript" src="/quicker/view/js/check.js"></script>
    <link href="/quicker/view/images/logo_ico.png" rel="shortcut icon" />
</head>

<body>

<div class="header">
    <div class="headtop">
			<span class="headspan">
				<p>
                    <img src="/quicker/view/images/logo.png">Uban<span>和你一起在乎你</span>
                </p>
			</span>
    </div>
    <div class="headbot">
        <div class="navigation">
            <ul>
                <li><a href="post_notice"><i
                        class="icon-edit icon-4x"></i> <span>发放通知</span> </a></li>
                <li><a href="check_export" class="this"><i
                        class="icon-folder-open icon-4x"></i> <span>查看通知</span> </a></li>
                <li><a href="set_agent"><i class="icon-user-md icon-4x"></i>
                    <span>班级管理员设置</span>
                </a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <form action="/quicker/web/download" method="post">
        <div class="upviews">
            <input type = "submit" value = "提交">
            <i id="upview" class="icon-download-alt"></i></div>
        <ul class="onews">

            <li><span class="name">通知名称</span> <span class="timeoftitle">发送日期</span>

            <c:forEach var="excelList" items="${formInfoList}">
                <c:forEach var="excelInfo" items="${excelList}" begin="0" end="0">
            <li>
                <input class="is-check" type="checkbox" name="tableList" value="${excelInfo.get("表格名称")}">

                <a href="javascript:void(0);">${excelInfo.get("表格名称")}</a>
                <span class="time">${excelInfo.get("上传时间")}</span>
                </c:forEach>
                    <%--<span class="number">0/60</span>--%>
                <i class="icon-angle-up icon-1x upmenu"></i>
                <div class="minuview">
                    <div class="rhsyyhqDIV">
                        <div class="content">
                            <table>
                                <tr class="title">
                                    <td class="classview"> 班级</td>
                                    <td class="classview"> 已交人数／总人数</td>
                                    <td class="classview"> 班级</td>
                                    <td class="classview"> 已交人数／总交人数</td>
                                    <td class="classview"> 班级</td>
                                    <td class="classview"> 已交人数／总交人数</td>
                                 </tr>
                                <c:forEach var="num" begin="1" end="${classNums}" step="3" varStatus="loop">

                                        <tr class="bookView">
                                            <c:forEach var="excel" items="${excelList}" begin="${num}" end="${num + 2}">
                                                <td class="classview">
                                                    <a href="./studentGroups.html">${excel.get("班级")}</a>
                                                </td>
                                                <td class="classview">
                                                    <a><p>${excel.get("已填人数")}/${excel.get("总人数")}</p></a>
                                                </td>
                                            </c:forEach>
                                </c:forEach>
                            </table>
                        </div>
                        <s class="up"><i></i></s>
                    </div>
                </div>
            </li>
            </c:forEach>
        </ul>

    </form>
</div>
<div class="footer">
    <center>
        <p>电子科技大学|信息与软件工程学院|择栖团队</p>
    </center>
</div>
</body>
</html>
