<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <%@ include file="/common/meta.jsp" %>   
   <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>  
</head> 
<body class="mainBody"> 
<div class="form_title" >银行账户信息</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">
		<c:choose>
			<c:when test="${clmsLoanBank.baseAccFlag=='0'}">贷款户</c:when>
			<c:when test="${clmsLoanBank.baseAccFlag=='1'}">基本户</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>银行编号：</td><!-- 开户行-->
		<td style="width:30%;">${clmsLoanBank.bank}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">
		<c:choose>
			<c:when test="${clmsLoanBank.baseAccFlag=='0'}">贷款户</c:when>
			<c:when test="${clmsLoanBank.baseAccFlag=='1'}">基本户</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>账户名称：</td>
		<td style="width:30%;">${clmsLoanBank.accName}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">
		<c:choose>
			<c:when test="${clmsLoanBank.baseAccFlag=='0'}">贷款户</c:when>
			<c:when test="${clmsLoanBank.baseAccFlag=='1'}">基本户</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>账号：</td><!-- 账户号码 -->
		<td style="width:30%;">${clmsLoanBank.accNo}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">
		<c:choose>
			<c:when test="${clmsLoanBank.baseAccFlag=='0'}">贷款户</c:when>
			<c:when test="${clmsLoanBank.baseAccFlag=='1'}">基本户</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>分支行名称：</td>
		<td style="width:30%;">${clmsLoanBank.branchname}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">
		<c:choose>
			<c:when test="${clmsLoanBank.baseAccFlag=='0'}">贷款户</c:when>
			<c:when test="${clmsLoanBank.baseAccFlag=='1'}">基本户</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>分支行省份：</td>
		<td style="width:30%;">${clmsLoanBank.bankprovince}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">
		<c:choose>
			<c:when test="${clmsLoanBank.baseAccFlag=='0'}">贷款户</c:when>
			<c:when test="${clmsLoanBank.baseAccFlag=='1'}">基本户</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>分支行城市：</td>
		<td style="width:30%;">${clmsLoanBank.city}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">
		<c:choose>
			<c:when test="${clmsLoanBank.baseAccFlag=='0'}">贷款户</c:when>
			<c:when test="${clmsLoanBank.baseAccFlag=='1'}">基本户</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>账户状态</td>
		<td style="width:30%;">
			<c:choose>
				<c:when test="${clmsLoanBank.accFlag=='0'}">停用</c:when>
				<c:when test="${clmsLoanBank.accFlag=='1'}">正常</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</td> 
	</tr>   
</table> 	
</body>
</html>
