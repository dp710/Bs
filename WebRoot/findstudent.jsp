<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'findstudent.jsp' starting page</title>
    
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
	
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/uniform.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/jquery.ui.custom.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/unicorn.tables.js"></script>
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>


<style type="text/css">    
     body{    
        background-image: url(images/common/bg4.jpg);    
        background-size:cover;  
        background-repeat:no-repeat;
     }    
 </style> 

<script type="text/javascript">

function deleteStudent(userId){
	if(confirm("您确定要删除这条数据吗？")){
		window.location.href = "${pageContext.request.contextPath}/StudentServlet.action?flag=deleteStudent&userId="+userId ;	
	}
}

function updateStudent(userId){
	
		window.location.href = "${pageContext.request.contextPath}/StudentServlet.action?flag=findStudentById&userId="+userId;	
}

</script>
  </head>
  
  <body>
    <div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<form action="${pageContext.request.contextPath}/UserServlet.action?flag=list&pageNumber=1" method="post" class="form-search">
			<table cellpadding="5px;">
				<tr>
					<td>按专业查询:</td>
					<td>
						<select name="grade" style="width: 195px;"><option value="-1">全部</option>
							<option value="0" ${grade==0?'selected':'' }>软件工程</option>
							<option value="1" ${grade==1?'selected':'' }>网络工程</option>
							<option value="2" ${grade==2?'selected':'' }>物联网工程</option>
							<option value="3" ${grade==3?'selected':'' }>计算机科学与技术1班</option>
							<option value="4" ${grade==4?'selected':'' }>计算机科学与技术2班</option>
						</select>
					</td>
					<td>学号:</td>
					<td><input name="id" type="text" class="input-medium search-query" placeholder="输入学号..." style="width: 165px;"/></td>
					<td>
						<button type="submit" class="btn btn-primary" title="Search">查询&nbsp;<i class="icon  icon-search"></i></button>
					</td>
				</tr>
			</table>
			</form>
		</div>
  
    
      
                
				<table class="table" border="1px solid black" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8;">
						<tr class="danger">
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							学号
							
						</th>
						<th style="text-align: center;vertical-align: middle;width:50px">
							昵称
						</th>
						<th style="text-align: center;vertical-align: middle; width: 30px;">
							性别
						</th>
						<th style="text-align: center;vertical-align: middle; width: 60px;">
							学院
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							专业
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							联系电话
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							登录密码
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							相片
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							寝室号
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							真实姓名
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							操作
						</th>
					</tr>
			<c:forEach items="${requestScope.userlist}" var="user">
				<tr class="success">
					<td>
						${user.id }
					</td>
					<td>
						${user.nickName}
					</td>
					<td>
						${user.sex}
					</td>
					<td>
						${user.faculty}
					</td>
					<td>
						${user.grade}
					</td>
					<td>
						${user.tel}
					</td>
					<td>
						${user.password}
					</td>
					<td>
						<img src="${user.face}"/>
					</td>
					<td>
						${user.dormId}
					</td>
					<td>
						${user.trueName}
					</td>
					<td>
					<c:choose>
						<c:when test="${user.type==1}">
						<button class="btn btn-info"  onclick="javascript:updateStudent(${user.id})">修改</button>
						<button class="btn btn-danger" onclick="javascript:deleteStudent(${user.id})">删除</button>
						</c:when>
						<c:otherwise>
							您无权对该用户进行操作
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
					</table>
			
	     <div class="pagination alternate pull-right" align="center" style="margin: 20px;">
			<ul class="clearfix">${pageCode}</ul>
		</div>
  </body>
</html>
