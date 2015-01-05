<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <%@ include file="/common/meta.jsp" %>   
   <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>  
</head> 
<body class="mainBody"> 
<div class="form_title" >机构信息信息</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">部门名称：</td>
		<td style="width:30%;">${sysOrg.orgName}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">部门联系电话：</td>
		<td style="width:30%;">${sysOrg.phone}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">部门地址：</td>
		<td style="width:30%;">${sysOrg.address}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">删除状态(0：未删除；1：已删除)：</td>
		<td style="width:30%;">${sysOrg.isDelete}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">顺序号：</td>
		<td style="width:30%;">${sysOrg.orderNo}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">是否叶子：</td>
		<td style="width:30%;">${sysOrg.isLeaf}</td> 
	</tr>  
</table> 	
</body>
</html>
