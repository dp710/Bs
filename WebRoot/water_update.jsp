<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'water_update.jsp' starting page</title>
    
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
	var id=$("#id").val();
	var month=$("#month").val();
	var price=$("#price").val();
	var weight=$("#weight").val();
	var pay=$("#pay").val();
	var dormId=$("#dormId").val();

	if (id=="") {
		$("#error").html("编号不能为空！");
		return false;
	}
	if (month=="") {
		$("#error").html("月份不能为空！");
		return false;
	}
	if (weight=="") {
		$("#error").html("重量不可以为空");
		return false;
	}
	if (pay=="") {
		$("#error").html("欠费不能为空！");
		return false;
	}
	if (dormId=="") {
		$("#error").html("宿舍号不能为空！");
		return false;
	}
	
}
function back(){
	
		//window.location.href = "${pageContext.request.contextPath}/index.jsp";
		window.history.back();
	
}
</script>
  </head>
  
  <body onload="getMyMonth()">
   <div class="" align="center" style="margin-top: 50px;">
			<h2 style="margin-bottom: 30px;">水表信息修改</h2>
		<form id="regForm" style="width: 700px;" class="form-horizontal form-inline" method="post" onsubmit="return checkForm()" action="WaterServlet.action?flag=updatewater">
			
			<font id="error" color="red"></font>
			
			<div class="control-group">
				<label class="control-label" for="id">编号：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="id" name="id" value="${water.id}" }onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="month">日期：</label>
				<div class="controls">
					<input class="input-block-level" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  type="text" id="month" name="month" value="${water.month}" onblur="checkForm(this.value)"/>
					<span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="price">价格(单位元/吨)：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="price" name="price" value="5" value="${water.price}" onblur="checkForm(this.value)" />			
					<span class="pull-left"></span>
				</div>
			</div>
					
			<div class="control-group">
				<label class="control-label" for="weight">重量（吨）：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="weight" name="weight" value="${water.weight}" onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="pay">欠费（元）：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="pay" name="pay" value="${water.pay}" onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="dormId">宿舍号：</label>
				<div class="controls">
					<input class="text input-block-level" type="text" id="dormId" name="dormId" value="${water.dormId}" onblur="checkForm(this.value)" />
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
