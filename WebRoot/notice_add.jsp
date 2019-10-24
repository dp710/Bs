<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'notice_add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   
    <script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
    
    <style type="text/css">    
     body{    
        background-image: url(images/common/bg4.jpg);    
        background-size:cover;  
        background-repeat:no-repeat;
     }    
 </style> 
   <script type="text/javascript">
   function checkForm(){
		var title=$("#title").val();
		var publishTime=$("#publishTime").val();
		var userId=$("#userId").val();
		var content=$("#content").val();
		
		if (title=="") {
			$("#error").html("主题不能为空！");
			return false;
		}
		if (publishTime=="") {
			$("#error").html("发布时间不可以为空");
			return false;
		}
		if (userId=="") {
			$("#error").html("发布人id不能为空！");
			return false;
		}
		if (content=="") {
			$("#error").html("内容不能为空！");
			return false;
		}
		
	}
   </script>
   <style type="text/css">
   .b{
      margin-left: 400px
   }
   </style>
  </head>
  <body>
 <form method="post" style="margin-left: 20px" onsubmit="return checkForm()" action="NoticeServlet.action?flag=addnotice" >
               <h2 align="center">发布公告</h2>
               
            <font id="error" color="red" size="5px" class="b"></font>
            
			<table class="table">
				<tr class="success">
					<td>
						公告主题：
					</td>
					<td>
						<input type="text" name="title" id="title" onblur="checkForm(this.value)">
					</td>
				</tr>
				
				<tr class="danger">
					<td>
						发布时间：
					</td>
					<td>
						<input onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  type="text" name="publishTime" id="publishTime" onblur="checkForm(this.value)">
					</td>
				</tr>
				
				<tr class="warning">
					<td>
						发布者id：
					</td>
					<td>
						<input type="text" name="userId" id="userId" value=${user.id} >
					</td>
				</tr>
				
					
			</table>
			<div class="control-group">
						<label class="control-label" for="content">【公告内容】</label>
						<div class="controls">
							<textarea name="content" id="content" class="ckeditor" cols="50" style="height:200px;width: 800px;" onblur="checkForm(this.value)"></textarea>
						</div>
			</div>	
			<div> 
			     <input type="submit" value="发布" style="margin: 20px">
                 <input type="button" value="取消" style="margin: 20px" onclick="javascript:window.history.back()">
            </div>
  </body>
</html>
