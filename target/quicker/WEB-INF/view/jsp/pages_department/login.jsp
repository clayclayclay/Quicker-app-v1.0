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
    <title>学生组织登陆</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel="stylesheet" type="text/css" href="/quicker/view/css/cssreset.css" />
  <link rel="stylesheet" type="text/css" href="/quicker/view/css/student.css" />
  <link rel="stylesheet" type="text/css" media="all"
        href="/quicker/view/css/font-awesome.min.css" />
  <script src="/quicker/view/jQuery/jquery-2.1.0.min.js"></script>
  <link href="/quicker/view/images/logo_ico.png" rel="shortcut icon" />
</head>
<body>
<div class="header">
  <div class="smahead">
			<span class="headspan">
				<p>
                  <p><img src="/quicker/view/images/logo.png">Uban<span>和你一起在乎你</span></p>
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
      <form method="post" action="departLogin">
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
  $().ready(function(){
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


    });
    var heigh = $(window).height();
    $("body").css({
      height: heigh
    });

  });



</script>
</body>

</html>
