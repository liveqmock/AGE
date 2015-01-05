<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <%@ include file="/common/meta.jsp" %>   
   <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>
</head> 
<style >
table {width:100%; font-size:12px;text-align:left;border:1px solid #ccc; border-collapse:collapse; }
table td {border:1px solid #ccc ;line-height:22px;   padding:0px 2px 0px 0px; }
</style>
<body class="mainBody"> 
<table class="table" style="width: 100%">
	<tr>
		<td style="width:5%;">公告标题：</td>
		<td style="width:30%;">${sysBulletin.title}</td> 
	</tr>  
	<tr>
		<td style="width:5%;">公告内容：</td>
		<td style="width:30%;">${sysBulletin.content}</td> 
	</tr>  
</table> 	
</body>
</html>
