<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Addfix.jsp' starting page</title>
    
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
<script
			src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
         <script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">    
     body{    
        background-image: url(images/common/bg1.jpg);    
        background-size:cover;  
        background-repeat:no-repeat;
     }    
 </style> 
<script type="text/javascript">

function checkForm(){
	var goods=$("#goods").val();
	var date=$("#date").val();
	var remarks=$("#remarks").val();
	var dormId=$("#dormId").val();
	
	if (goods=="") {
		$("#error").html("损坏物品不能为空！");
		return false;
	}
	if (date=="") {
		$("#error").html("日期不可以为空");
		return false;
	}
	if (remarks=="") {
		$("#error").html("备注不能为空！");
		return false;
	}
	if (dormId=="") {
		$("#error").html("宿舍号不能为空！");
		return false;
	}
	
}
function back(){
	if(confirm("退出修改？")){
		//window.location.href = "${pageContext.request.contextPath}/index.jsp";
		window.history.back();
	}
}
</script>
  </head>
  
  <body onload="getMyMonth()">
   <div class="" align="center" style="margin-top: 50px;">
			<h2 style="margin-bottom: 30px;">维修信息添加</h2>
		<form id="regForm" style="width: 700px;" class="form-horizontal form-inline" method="post" onsubmit="return checkForm()" action="FixServlet.action?flag=addfix">
			
			<font id="error" color="red"></font>
			
			
			<div class="control-group">
				<label class="control-label" for="goods">损坏物品：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="goods" name="goods"  onblur="checkForm(this.value)" />			
					<span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="date">日期：</label>
				<div class="controls">
					<input class="input-block-level" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  type="text" id="date" name="date" onblur="checkForm(this.value)"/>
					<span class="pull-left"></span>
				</div>
			</div>
			
			
					
			<div class="control-group">
				<label class="control-label" for="remarks">备注：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="remarks" name="remarks" onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
		
			<div class="control-group">
				<label class="control-label" for="dormId">宿舍号：</label>
				<div class="controls">
					<input class="text input-block-level" type="text" id="dormId" name="dormId"  onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			
			
		<div class="control-group" style="margin: 0px;">
				<div style="margin-left: 10px; margin-top:15px">
				    <button type="submit" tabindex="5">确认</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" tabindex="5" onclick="back()">取消</a></button> &nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			
			<font id="error" color="red"></font>
			
		</form>
	</div>
  </body>
</html>
