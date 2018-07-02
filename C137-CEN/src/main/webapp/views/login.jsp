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
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-easyui-1.3.2"/>
    <script type="text/javascript" src="js/ux/login.js"></script>
  </head>
  <form id="loginForm" action="toLogin.do" method="post">
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
						<li class=user_main_c></li>
						<li class=user_main_r></li>
          </ul>
         </dd>
         <dd id="user_bottom">
          <ul>
                        <li class=user_bottom_l></li>
						<li class=user_bottom_c></li>
						<li class=user_bottom_r></li>
          </ul>
         </dd>
      </dl>
    
    
    
    
    </div>
    <div></div>
  </body>
  </form>
</html>
