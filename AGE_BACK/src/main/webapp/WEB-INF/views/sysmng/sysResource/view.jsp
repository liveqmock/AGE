<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <%@ include file="/common/meta.jsp" %>   
   <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>  
</head> 
<body class="mainBody"> 
<div class="form_title" >资源信息</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">资源代码：</td>
		<td style="width:30%;">${sysResource.resNo}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">资源名称：</td>
		<td style="width:30%;">${sysResource.resName}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">显示名称：</td>
		<td style="width:30%;">${sysResource.displayName}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">资源类型：</td>
		<td style="width:30%;">${sysResource.typeCN}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">访问地址：</td>
		<td style="width:30%;">${sysResource.url}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">顺序号：</td>
		<td style="width:30%;">${sysResource.orderNo}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">描述：</td>
		<td style="width:30%;">${sysResource.resDesc}</td> 
	</tr>  
</table> 	
</body>
</html>
