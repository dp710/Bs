<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upadtepassword.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
<link href="bootstrap/css/docs.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadPreview.min.js"></script>
	
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

function checkname(nickName){
	 if($("#nickName").val()==""){
			$("#userErrorInfo").html("昵称不为空！");
			$("#nickName").focus();
			return;
		} 
	$.post("${pageContext.request.contextPath}/UserServlet.action","flag=checkname&nickName="+nickName,
			function(result){
				//服务输出的数据是字符串
				if(result=="true"){
					$("#userErrorInfo").html("该昵称已存在，请重新输入！");
					$("#nickName").focus();
				}else{
					$("#userErrorInfo").html("");
				}
			}
	);
}
</script>

</head>
<body>
	<div class="" align="center" style="margin-top: 50px;">
  
			<h2 style="margin-bottom: 30px;">个人信息修改:</h2>
		<form id="regForm" style="width: 700px;" class="form-horizontal form-inline" method="post" action="UserServlet.action?flag=updateuser">
			<div class="control-group">
				<div class="controls">
				<label class="control-label" for="nickName">用户名：</label>
					<input class="input-block-level" type="text" id="nickName" name="nickName" value="${sessionScope.user.nickName}" onblur="checkname(this.value)" /><span class="pull-left"></span>
					<font id="userErrorInfo" class="pull-right" color="red"></font>		
				</div>
				<div>
				<label class="control-label" for="nickName">性&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<input class="input-block-level" type="text" id="sex" name="sex"  value="${user.sex}"/><span class="pull-left"></span>
					<font id="userErrorInfo" class="pull-right" color="red"></font>
					</div>
					
					<div>
					<label class="control-label" for="tel">手机号：</label>
					<input class="input-block-level" type="text" id="tel" name="tel" value="${user.tel }"/><span class="pull-left"></span>
					<font id="userErrorInfo" class="pull-right" color="red"></font>
					</div>
					
					<div>
					<label class="control-label" for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
					<input class="input-block-level" type="text" id="email" name="email" value="${user.email}"/><span class="pull-left"></span>
					<font id="userErrorInfo" class="pull-right" color="red"></font>
					</div>
					
					<div>
				<label class="control-label" for="nickName"></label>
					<input class="input-block-level" type="hidden" id="id" name="id"  value="${user.id}"/><span class="pull-left"></span>
					<font id="userErrorInfo" class="pull-right" color="red"></font>
					</div>
					
			<div class="control-group" style="margin: 0px;">
				<div style="margin-left: 100px; margin-top:15px">
				    <button type="submit" tabindex="5">确认</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" tabindex="5" onclick="back()">取消</a></button> &nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				</form>
			</div>
			
			
</body>
</html>
