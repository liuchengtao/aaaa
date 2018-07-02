<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>大成网-用户登录</title>
    <%@include file="/resource.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="/js/ux/login.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/user_login.css">
  </head>
  <form id="loginForm" action="/toLogin.do" method="post">
  <body id="userlogin_body">
    <div></div>
    <div id="user_login">
      <dl>
         <dd id="user_top">
         <ul>
                        <li class=user_top_l></li>
						<li class=user_top_c></li>
						<li class=user_top_r></li>
         </ul>
         </dd>
         <dd id="user_main">
          <ul>
                        <li class=user_main_l>
                        </li>
						<li class=user_main_c>
						<div class=user_main_box>
								<ul>
									<li class=user_main_text>
										用户名：
									</li>
									<li class=user_main_input>
										<input class="txtusernamecssclass easyui-validatebox"  data-options="required:true,validType:'email',missingMessage:'邮箱不能为空.',invalidMessage:'邮箱格式不正确'" name="email"  value="admin@qq.com" maxlength="20">
									</li>
								</ul>
								<ul>
									<li class=user_main_text>
										密 码：
									</li>
									<li class=user_main_input>
										<input class="txtpasswordcssclass easyui-validatebox"   data-options="required:true,missingMessage:'密码不能为空.'" type="password" name="pwd" value="1995">
									</li>
								</ul>
								<ul>
									<li class=user_main_text>
										验证码：
									</li>
									<li class=user_main_input>
										
									</li>
								</ul>
							</div>
						</li>
						<li class=user_main_r>
						<input class="ibtnentercssclass"
								style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px"
								type=image src="images/login/user_botton.gif">
						</li>
          </ul>
         </dd>
         <dd id="user_bottom">
          <ul>
                        <li class=user_bottom_l></li>
						<li class=user_bottom_c>
						<span style="margin-top: 40px">美女图片之家，请点此 <a
								href="http://www.mn606.com">WWWW.MN606.COM</a> 。</span>
						</li>
						<li class=user_bottom_r></li>
          </ul>
         </dd>
      </dl>
    
    
    
    
    </div>
    <div></div>
  </body>
  </form>
</html>
