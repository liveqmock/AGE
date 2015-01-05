<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <%@ include file="/common/meta.jsp" %>   
   <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>  
</head> 
<body class="mainBody"> 
<div class="form_title" >角色信息</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">角色代码：</td>
		<td style="width:30%;">${sysRole.roleNo}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">角色名称：</td>
		<td style="width:30%;">${sysRole.roleName}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">描述：</td>
		<td style="width:30%;">${sysRole.roleDesc}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">顺序号：</td>
		<td style="width:30%;">${sysRole.orderNo}</td> 
	</tr>   
</table> 	
</body>
</html>
