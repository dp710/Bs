<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updatepassword.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">    
     body{    
        background-image: url(images/common/bg1.jpg);    
        background-size:cover;  
        background-repeat:no-repeat;
     }    
 </style> 
<script type="text/javascript">

function back(){
	if(confirm("退出修改？")){
		//window.location.href = "${pageContext.request.contextPath}/index.jsp";
		window.history.back();
	}
}

</script>
  </head>
  
  <body>
  <div class="" align="center" style="margin-top: 50px;">
  
			<h2 style="margin-bottom: 30px;">密码修改:</h2>
			<p style="color: red">${msg }</p>
		<form id="regForm" style="width: 700px;"  class="form-horizontal form-inline" method="post" action="UserServlet.action?flag=updatepassword">
			<div class="control-group">
			
			<div class="controls">
				<label class="control-label" for="id">输入管理号：</label>
					<input class="input-block-level" type="text" id="id" name="id"  value="${user.id }"/><span class="pull-left"></span>
					<font id="userErrorInfo" class="pull-right" color="red"></font>		
				</div>
				
				<div class="controls">
				<label class="control-label" for="nickName">请输入密码：</label>
					<input class="input-block-level" type="text" id="passWord" name="passWord"  value=""/><span class="pull-left"></span>
					<font id="userErrorInfo" class="pull-right" color="red"></font>		
				</div>
				<div>
				<label class="control-label" for="nickName">输入新密码：</label>
					<input class="input-block-level" type="text" id="passWord1" name="passWord1"  value=""/><span class="pull-left"></span>
					<font id="userErrorInfo" class="pull-right" color="red"></font>
					</div>
					
					<div>
					<label class="control-label" for="nickName">确认新密码：</label>
					<input class="input-block-level" type="text" id="passWord2" name="passWord2" value=""/><span class="pull-left"></span>
					<font id="userErrorInfo" class="pull-right" color="red"></font>
					</div>
					
			<div class="control-group" style="margin: 0px;">
				<div style="margin-left: 130px; margin-top:15px">
				    <button type="submit" tabindex="5">确认</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" tabindex="5" onclick="back()">取消</a></button> &nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				</form>
			</div>
  </body>
</html>
