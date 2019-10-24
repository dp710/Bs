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
    
    <title>My JSP 'find_dormitory.jsp' starting page</title>
    
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

<<script type="text/javascript">

function updateDormitory(dormitoryId){
	
		window.location.href = "${pageContext.request.contextPath}/DormitoryServlet.action?flag=findDormitoryById&dormitoryId="+dormitoryId;	
	
}

</script>
  </head>
  <body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<form action="${pageContext.request.contextPath}/DormitoryServlet.action?flag=list&pageNumber=1" method="post" class="form-search">
			<table cellpadding="5px;">
				<tr>
					<td>按人数查询:</td>
					<td>
						<select name="number" style="width: 195px;"><option value="-1">全部</option>
							<option value="1" ${number==1?'selected':'' }>已满</option>
							<option value="0" ${number==0?'selected':'' }>未满</option>
						</select>
					</td>
					<td>寝室号:</td>
					<td><input name="id" type="text" class="input-medium search-query" placeholder="输入寝室号..." style="width: 165px;"/></td>
					<td>
						<button type="submit" class="btn btn-primary" title="Search">查询&nbsp;<i class="icon  icon-search"></i></button>
					</td>
				</tr>
			</table>
			</form>
		</div>
  
       
              <div>
				<table class="table" border="1px solid black" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8;">
						<tr class="danger">
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							寝室号
							
						</th>
						<th style="text-align: center;vertical-align: middle;width:50px">
							寝室联系电话
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							寝室人数 	
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							备注
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							操作
						</th>
					</tr>
			<c:forEach items="${requestScope.dormitorylist}" var="dormitory">
				<tr class="success">
					<td>
						${dormitory.id }
					</td>
					<td>
						${dormitory.tel}
					</td>
					<td>
						${dormitory.number}
					</td>
					<td>
						${dormitory.remarks}
					</td>
					<td>
						<button class="btn btn-info"  onclick="javascript:updateDormitory(${dormitory.id})">修改</button>
					</td>
				</tr>
			</c:forEach>
					</table>
			</div>  		
			
		<div class="pagination alternate pull-right" align="center" style="margin: 20px;">
			<ul class="clearfix">${pageCode}</ul>
		</div>	
  </body>
</html>
