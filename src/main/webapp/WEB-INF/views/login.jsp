<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
  <head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="../css/login_style.css" />
    
        
    <!-- JavaScript includes -->
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script src="../js/login.js"></script>
  </head>

  <body>
	<div id="formContainer">
		<form id="login" method="post" action="${ctx}/login">
			<a href="#" id="flipToRecover" class="flipLink">Forgot?</a>
			<input type="text" name="username" id="username" placeholder="用户名"/>
			<input type="password" name="password" id="password" placeholder="密码" />
			<input type="submit" name="submit" value="Login" />
		</form>
		<form id="recover" method="post" action="./">
			<a href="#" id="flipToLogin" class="flipLink">Forgot?</a>
			<input type="text" name="recoverEmail" id="recoverEmail" placeholder="Your Email" />
			<input type="submit" name="submit" value="Recover" />
		</form>
	</div>
  </body>
</html>