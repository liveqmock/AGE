<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <%@ include file="/common/meta.jsp" %>   
   <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>  
</head> 
<body class="mainBody"> 
<div class="form_title" >用户信息信息</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">用户账号：</td>
		<td style="width:30%;">${sysUser.userAccount}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">用户名称：</td>
		<td style="width:30%;">${sysUser.userName}</td> 
	</tr>   
	<tr>
		<td style="width:15%;">手机：</td>
		<td style="width:30%;">${sysUser.mobile}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">固定电话：</td>
		<td style="width:30%;">${sysUser.phone}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">顺序号：</td>
		<td style="width:30%;">${sysUser.orderNo}</td> 
	</tr>   
	<tr>
		<td style="width:15%;">账号状态：</td>
		<td style="width:30%;">${sysUser.stateCN}</td> 
	</tr>  
</table> 	
</body>
</html>
