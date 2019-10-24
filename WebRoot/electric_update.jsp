<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'electric_update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
	var month=$("#month").val();
	var price=$("#price").val();
	var amount=$("#amount").val();
	var pay=$("#pay").val();
	var dormId=$("#dormId").val();
	
	if (month=="") {
		$("#error").html("月份不能为空！");
		return false;
	}
	if (amount=="") {
		$("#error").html("用电量不可以为空");
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
  
  <body>
   <body onload="getMyMonth()">
   <div class="" align="center" style="margin-top: 50px;">
			<h2 style="margin-bottom: 30px;">电表信息修改</h2>
		<form id="regForm" style="width: 700px;" class="form-horizontal form-inline" method="post" onsubmit="return checkForm()" action="ElectricServlet.action?flag=updateelectric">
			
			<font id="error" color="red"></font>
			
			
			<div class="control-group">
				<label class="control-label" for="id">编号：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="id" name="id" value="${electric.id}" } onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="month">当前时间：</label>
				<div class="controls">
					<input class="input-block-level" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  type="text" id="month" name="month" value="${electric.month}" } onblur="checkForm(this.value)"/>
					<span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="price">价格(单位元/度)：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="price" name="price" value="1" value="${electric.price}" } onblur="checkForm(this.value)" />			
					<span class="pull-left"></span>
				</div>
			</div>
					
			<div class="control-group">
				<label class="control-label" for="amount">用电量（度）：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="amount" name="amount" value="${electric.amount}" } onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="pay">欠费（元）：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="pay" name="pay" value="${electric.pay}" } onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="dormId">宿舍号：</label>
				<div class="controls">
					<input class="text input-block-level" type="text" id="dormId" name="dormId"  value="${electric.dormId}" onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			
			
		<div class="control-group" style="margin: 0px;">
				<div style="margin-left: 10px; margin-top:15px">
				    <button type="submit" tabindex="5">确认</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" tabindex="5" onclick="back()">返回</a></button> &nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			
			<font id="error" color="red"></font>
			
		</form>
	</div>
  </body>
</html>
