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
    
    <title>My JSP 'searchelectric.jsp' starting page</title>
    
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
function back(){
	
		//window.location.href = "${pageContext.request.contextPath}/index.jsp";
		window.history.back();
}

</script>
  </head>
  
  <body>
     <h3 align="center">电费信息</h3>
  
     <div class="pagination alternate pull-right" align="center" style="margin: 20px;">
		</div>
              
				<table class="table" border="1px solid black" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8;">
					<tr class="danger">
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							编号
						</th>
						<th style="text-align: center;vertical-align: middle;width:50px">
							日期
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							电费价格（元/度）
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							用电度数(度)
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							欠费(元)
						</th>
						<th style="text-align: center;vertical-align: middle; width: 50px;">
							宿舍号
						</th>
					</tr>
			
				<tr class="success">
					<td>
						${electric.id }
					</td>
					<td>
						${electric.month}
					</td>
					<td>
						${electric.price}
					</td>
					<td>
						${electric.amount}
					</td>
					<td>
						${electric.pay}
					</td>
					<td>
						${electric.dormId}
					</td>
				</tr>
					</table>
					<button  style="margin-left: 500px" class="btn btn-info"  onclick="back()">返回</button>
				</div>
  </body>
</html>
