<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dormitory_update.jsp' starting page</title>
    
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
	
		//window.location.href = "${pageContext.request.contextPath}/index.jsp";
		window.history.back();
	
}
</script>
  </head>
  
  <body>
    <div class="" align="center" style="margin-top: 50px;">
  
			<h2 style="margin-bottom: 30px;">宿舍信息修改:</h2>
		<form id="regForm" style="width: 700px;" class="form-horizontal form-inline" method="post" action="DormitoryServlet.action?flag=updatedormitory">
			<font id="error" color="red"></font>
			
			<div class="control-group">
				<label class="control-label" for="id">宿舍号：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="id" name="id"  value="${dormitory.id}"  />
					<span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="tel">联系电话：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="tel" name="tel"  value="${dormitory.tel}" />
				</div>
			</div>
			
			
			
			<div class="control-group">
				<label class="control-label" for="number">人数：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="number" name="number"  value="${dormitory.number}" />
				</div>
			</div>
		
			<div class="control-group">
				<label class="control-label" for="remarks">备注：</label>
				<div class="controls">
					<input class="text input-block-level" type="text" id="remarks" name="remarks"  value="${dormitory.remarks}" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
		
			<div style="margin-left: 10px; margin-top:15px">
				    <button type="submit" tabindex="5">确认</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" tabindex="5" onclick="back()">取消</a></button> &nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				</form>
			</div>
  </body>
</html>
