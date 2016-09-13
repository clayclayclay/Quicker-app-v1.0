<%--
  Created by IntelliJ IDEA.
  User: Nanguoyu
  Date: 2016/7/14
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看通知</title>
  <link rel="stylesheet" type="text/css"
        href="/quicker/view/css/cssreset.css">
  <link rel="stylesheet" type="text/css"
        href="/quicker/view/css/studentCheck.css">
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
                 <p><img src="/quicker/view/images/logo.png">Uban<span>和你一起在乎你</span></p>
                </p>
			</span>
  </div>
  <div class="headbot">
    <div class="navigation">
      <ul>
        <li><a href="post_notice_depart"><i
                class="icon-edit icon-4x"></i> <span>发放通知</span> </a></li>
        <li><a href="./checkNoticeAndExort.html" class="this"><i
                class="icon-folder-open icon-4x"></i> <span>查看通知</span> </a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container">
  <form action="/quicker/view/web/download">
    <div class="upviews"><span>导出</span><i id="upview" class="icon-download-alt"></i></div>
    <ul class="onews">

      <li><span class="name">通知名称</span> <span class="timeoftitle">发送日期</span>
        <span class="numberoftitle">已交人数</span></li>


      <li class="haha">
        <input type="checkbox" name="table" value="${excel.getTableName()}">
        <a href="javascript:void(0);">学生会外联部报名表</a>
        <span class="time">2016/01/12</span>
        <span class="number">10</span>
        <i class="icon-angle-up icon-1x upmenu"></i>
        <div class="minuview">
          <div class="rhsyyhqDIV">
            <div class="content">
              <table>
                <tr class="title">

                  <td class="classview"> 学号</td>
                  <td class="classview"> 性别</td>
                  <td class="classview"> 年级</td>
                  <td class="classview"> 姓名</td>
                </tr>
                <tr class="bookView">

                  <td class="classview">
                    <a href="./studentGroups.html">2014220203013</a>
                  </td>
                  <td class="classview">
                    <a><p>男</p></a>
                  </td>
                  <td class="classview">
                    <a href="./studentGroups.html">2014级</a>
                  </td>
                  <td class="classview">
                    <a><p>月凡</p></a>
                  </td>

                </tr>
                <tr class="bookView">

                  <td class="classview">
                    <a href="./studentGroups.html">2014220203013</a>
                  </td>
                  <td class="classview">
                    <a><p>男</p></a>
                  </td>
                  <td class="classview">
                    <a href="./studentGroups.html">2014级</a>
                  </td>
                  <td class="classview">
                    <a><p>月凡</p></a>
                  </td>

                </tr>
                <tr class="bookView">

                  <td class="classview">
                    <a href="./studentGroups.html">2014220203013</a>
                  </td>
                  <td class="classview">
                    <a><p>男</p></a>
                  </td>
                  <td class="classview">
                    <a href="./studentGroups.html">2014级</a>
                  </td>
                  <td class="classview">
                    <a><p>月凡</p></a>
                  </td>

                </tr>
                <tr class="bookView">

                  <td class="classview">
                    <a href="./studentGroups.html">2014220203013</a>
                  </td>
                  <td class="classview">
                    <a><p>男</p></a>
                  </td>
                  <td class="classview">
                    <a href="./studentGroups.html">2014级</a>
                  </td>
                  <td class="classview">
                    <a><p>月凡</p></a>
                  </td>

                </tr>
                <tr class="bookView">

                  <td class="classview">
                    <a href="./studentGroups.html">2014220203013</a>
                  </td>
                  <td class="classview">
                    <a><p>男</p></a>
                  </td>
                  <td class="classview">
                    <a href="./studentGroups.html">2014级</a>
                  </td>
                  <td class="classview">
                    <a><p>月凡</p></a>
                  </td>

                </tr>
                <tr class="bookView">

                  <td class="classview">
                    <a href="./studentGroups.html">2014220203013</a>
                  </td>
                  <td class="classview">
                    <a><p>男</p></a>
                  </td>
                  <td class="classview">
                    <a href="./studentGroups.html">2014级</a>
                  </td>
                  <td class="classview">
                    <a><p>月凡</p></a>
                  </td>

                </tr>
                <tr class="bookView">

                  <td class="classview">
                    <a href="./studentGroups.html">2014220203013</a>
                  </td>
                  <td class="classview">
                    <a><p>男</p></a>
                  </td>
                  <td class="classview">
                    <a href="./studentGroups.html">2014级</a>
                  </td>
                  <td class="classview">
                    <a><p>月凡</p></a>
                  </td>

                </tr>

              </table>

            </div>
            <s class="up"><i></i></s>
          </div>
        </div>
      </li>
    </ul>
  </form>
  <div class="ochange">
    <ul class="change">
      <li class="thispage"><a class="#">1</a></li>
    </ul>
  </div>
</div>
<div class="footer">
  <center>
    <p>电子科技大学|信息与软件工程学院|择栖团队</p>
  </center>
</div>
</body>
<script type="text/javascript">
  $().ready(function(){
    $('.content tr').hover(function(){

      $(this).children().css({"background-color":'#85B3F2',"color":"#ffffff"});

      $(this).children().children('a').css({"background-color":'#85B3F2',"color":"#ffffff"});

    }).mouseleave(function(){
      $(this).children().css({"background-color":'',"color":''});
      $(this).children().children('a').css({"background-color":'',"color":""});
    })
    $('.bookView:even').addClass('eve');
    $('.bookView:odd').addClass('odd');

    $(".onews a").click(function(){
      $(this).nextAll(".upmenu").css({
        display: 'inline'
      });
      $(this).nextAll(".minuview").slideDown('400', function() {
      });
    });
    $(".upmenu").click(function(event) {
      $(this).css({
        display: 'none'
      });
      $(this).next().slideUp(400);

    });

  });
</script>
  </html>
