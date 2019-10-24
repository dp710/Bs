<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
		$("#error").html("昵称不能为空!");
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
			<h2 style="margin-bottom: 30px;">新生信息录入</h2>
		<form id="regForm" style="width: 700px;" enctype="multipart/form-data" class="form-horizontal form-inline" method="post" action="UserServlet.action?flag=upload">
			
			<font id="error" color="red"></font>
			
			<div class="control-group">
				<label class="control-label" for="id">学号：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="id" name="id" onblur="checkid(this.value)" />
					<span class="pull-left"></span>
				</div>
				<font id="userErrorInfo" class="pull-right" color="red"></font>	
			</div>
			
			
			<div class="control-group">
				<label class="control-label" for="nickName">昵称：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="nickName" name="nickName" onblur="checkForm(this.value)" />
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
					<input class="input-block-level" type="text" id="faculty" name="faculty" onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="grade">专业：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="grade" 
					name="grade" onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="tel">联系电话：</label>
				<div class="controls">
					<input class="text input-block-level" type="text" id="tel"name="tel" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="password">初始密码：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="password"name="password" value="666666" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="email">电子邮箱：</label>
				<div class="controls">
					<input class="text input-block-level" type="text" id="email"name="email" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group" id="preDiv" style="width: 700px; height: 120px;margin-left: 80px;">
				<img id="ImgPr" class="pull-left" style="width: 120px; height: 120px;" src="${pageContext.request.contextPath}/${user.face }" />
			</div>
			<div class="control-group">
				<label class="control-label" for="face">上传相片：</label>
				<div class="controls">
					<input type="file" id="face" name="face">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="dormId">寝室号：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="dormId" name="dormId" onblur="checkForm(this.value)" /><br>
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="trueName">真实姓名：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="trueName" name="trueName" onblur="checkForm(this.value)" />
					<br> <span class="pull-left"></span>
				</div>
			</div>
			
		    <div class="control-group">
				<label class="control-label" for="type"></label>
				<div class="controls">
					<input class="input-block-level" type="hidden" name="type" value="1">
				</div>
			</div>
			
			<div style="margin-left: 10px; margin-top:15px">
				    <button type="submit" tabindex="5">确认</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" tabindex="5" onclick="back()">取消</a></button> &nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			
			<font id="error" color="red"></font>
			
		</form>
	</div>
</body>
</html>