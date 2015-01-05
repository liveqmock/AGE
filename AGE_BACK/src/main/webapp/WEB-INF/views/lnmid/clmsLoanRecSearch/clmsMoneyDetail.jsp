<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <%@ include file="/common/meta.jsp" %>   
   <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>  
</head> 
<body > 
<table class="table" style="width: 100%;margin-top:10px;" >
	<tr align=center>
		<td style="width:50px;">序号</td>
		<td style="width:150px;">代扣订单号</td>
		<td style="width:100px;">代扣金额</td>
		<td style="width:100px;">代扣日期</td>
		<td style="width:150px;">代扣时间</td>
		<td style="width:100px;">代扣状态</td>
		<td style="width:100px;">交易流水号</td>
	</tr>
	<c:forEach items="${list}" var="map"  varStatus="status">
	<tr align=center>
		<td >${status.index}</td>
		<td >${map.orderNo}</td> 
		<td >${map.loadOutMoney}</td> 
		<td >${map.startTime}</td> 
		<td >${map.endTime}</td> 
		<td >${map.flowStatus}</td> 
		<td >${map.tradeNo}</td> 
	</tr>  
	</c:forEach>
	<tr align=center><td colspan=4><input id="clearbtn" class="button" type="button" onclick="ownerDialog.close();" value="关闭" /></td></tr>
</table> 	
</body>
</html>