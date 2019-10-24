<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updatestudent.jsp' starting page</title>
    
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

$(function () {
	$("#face").uploadPreview({ Img: "ImgPr", Width: 220, Height: 220 });
	$("#regForm").validate(
            {
                /*自定义验证规则*/
                rules: {
            	    "id":{ required: true, number:true  },
                	"nickName": { required: true, minlength: 2 },
                	"sex":{ required: true },
                	"faculty":{ required: true, minlength: 4 },
                	"grade":{ required: true, minlength: 4 },
                	"tel":{ required: true,number:true },
                	"password":{ required: true, minlength: 6 },
                	"email": { required: true, email:true },
                	 "type":{ required: true, number:true  },
                	 "dormId":{ required: true, number:true  },
                	"trueName":{ required: true, minlength: 2 }
                },
                /*错误提示位置*/
                errorPlacement: function(error, element) {
                    error.appendTo(element.siblings("span"));
                }
            }
          );
});
function checkid(id){
	 if($("#id").val()==""){
		$("#userErrorInfo").html("学号不为空！");
		$("#id").focus();
		return;
	} 
	$.post("${pageContext.request.contextPath}/UserServlet.action","flag=checkid&id="+id,
			function(result){
				//服务输出的数据是字符串
				if(result=="true"){
					$("#userErrorInfo").html("学号已存在，请重新输入！");
					$("#id").focus();
				}else{
					$("#userErrorInfo").html("");
				}
			}
	);
}


function checkForm(){
	var nickName=$("#nickName").val();
	var sex=$("#sex").val();
	var faculty=$("#faculty").val();
	var grade=$("#grade").val();
	var tel=$("#tel").val();
	var password=$("#password").val();
	var email=$("#email").val();
	var dormId=$("#dormId").val();
	var trueName=$("#trueName").val();
	if (nickName=="") {
		$("#error").html("昵称不能为空！");
		return false;
	}
	if (sex=="") {
		$("#error").html("请选择性别！");
		return false;
	}
	if (faculty=="") {
		$("#error").html("学院不能为空！");
		return false;
	}
	if (grade=="") {
		$("#error").html("专业不能为空！");
		return false;
	}
	if (tel=="") {
		$("#error").html("电话号码不能为空！");
		return false;
	}
	if (password=="") {
		$("#error").html("密码不能为空！");
		return false;
	}
	if (email=="") {
		$("#error").html("邮箱不能为空！");
		return false;
	}
	if (dormId=="") {
		$("#error").html("寝室号不能为空！");
		return false;
	}
	if (trueName=="") {
		$("#error").html("真实姓名不能为空！");
		return false;
	}
	
}
</script>
  </head>
  
  <body>
  <div class="" align="center" style="margin-top: 50px;">
  
			<h2 style="margin-bottom: 30px;">学生信息修改:</h2>
		<form id="regForm" style="width: 700px;" enctype="multipart/form-data"  class="form-horizontal form-inline" method="post" action="StudentServlet.action?flag=upload">
			<font id="error" color="red"></font>
			
			<div class="control-group">
				<label class="control-label" for="id">学号：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="id" name="id"  value="${user.id}" onblur="checkid(this.value)" />
					<span class="pull-left"></span>
				</div>
				<font id="userErrorInfo" class="pull-right" color="red"></font>	
			</div>
			
			<div class="control-group">
				<label class="control-label" for="nickName">昵称：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="nickName" name="nickName"  value="${user.nickName}" onblur="checkForm(this.value)" />
					<span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="sex">性别：</label>
				<div class="controls">
					<label class="radio" style="margin-right: 50px;">
						<select id="sex" name="sex"><option value="">请选择...</option>
						    <option value="男" ${user.sex=='男'?'selected':'' }>男</option>
							<option value="女" ${user.sex=='女'?'selected':'' }>女</option>
						</select> <span class="pull-left"></span>
					</label>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="faculty">学院：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="faculty" name="faculty"  value="${user.faculty}" onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="grade">专业：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="grade" name="grade"  value="${user.grade}" onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="tel">联系电话：</label>
				<div class="controls">
					<input class="text input-block-level" type="text" id="tel" name="tel"  value="${user.tel}" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="password">密码：</label>
				<div class="controls">
					<input class="input-block-level" type="password" id="password" name="password"  value="${user.password}" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="email">电子邮箱：</label>
				<div class="controls">
					<input class="text input-block-level" type="text" id="email"name="email"  value="${user.email}" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
				
			<div class="control-group" id="preDiv" style="width: 700px; height: 120px;margin-left: 80px;">
				<img id="ImgPr" class="pull-left" style="width: 120px; height: 120px;" src="${pageContext.request.contextPath}/${user.face }" />
			</div>
			<div class="control-group">
				<label class="control-label" for="face">头像：</label>
				<div class="controls">
					<input type="file" id="face" name="face" value=<img src="${user.face}"/>>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="dormId">寝室号：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="dormId" name="dormId"   value="${user.dormId}" onblur="checkForm(this.value)" /><br>
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="trueName">真实姓名：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="trueName" name="trueName"  value="${user.trueName}" onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
		    <div class="control-group">
				<label class="control-label" for="type"></label>
				<div class="controls">
					<input class="input-block-level" type="hidden"  id="type" name="type" value="1">
				</div>
			</div>
			
			<div style="margin-left: 10px; margin-top:15px">
				    <button type="submit" tabindex="5">确认</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" tabindex="5" onclick="back()">返回</a></button> &nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				</form>
			</div>
  </body>
</html>
