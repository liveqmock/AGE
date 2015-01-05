<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <%@ include file="/common/meta.jsp" %>   
   <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>  
</head> 
<body class="mainBody"> 
<div class="form_title" ></div> 
<table class="table" style="width: 100%;margin-top:10px;" >
	<tr>
		<td style="width:150px;">基本户银行编码：</td>
		<td style="width:200px;">${clmsBaseBank.bank}</td>
		<td style="width:150px;">贷款户银行编码：</td>
		<td style="width:200px;">${clmsLoanBank.bank}</td> 
	</tr>  
	<tr>
		<td style="width:150px;">基本户账户名称：</td>
		<td style="width:200px;">${clmsBaseBank.accName}</td> 
		<td style="width:150px;">贷款户账户名称：</td>
		<td style="width:200px;">${clmsLoanBank.accName}</td> 
	</tr>  
	<tr>
		<td style="width:150px;">基本户帐号：</td>
		<td style="width:200px;">${clmsBaseBank.accNo}</td> 
		<td style="width:150px;">贷款户帐号：</td>
		<td style="width:200px;">${clmsLoanBank.accNo}</td> 
	</tr>  
	<tr>
		<td style="width:150px;">基本户分支行名称：</td>
		<td style="width:200px;">${clmsBaseBank.branchname}</td> 
		<td style="width:150px;">贷款户分支行名称：</td>
		<td style="width:200px;">${clmsLoanBank.branchname}</td> 
	</tr>
	<tr>
		<td style="width:150px;">基本户分支行省份：</td>
		<td style="width:200px;">${clmsBaseBank.bankprovince}</td> 
		<td style="width:150px;">贷款户分支行省份：</td>
		<td style="width:200px;">${clmsLoanBank.bankprovince}</td> 
	</tr>
	<tr>
		<td style="width:150px;">基本户分支行城市：</td>
		<td style="width:200px;">${clmsBaseBank.city}</td> 
		<td style="width:150px;">贷款户分支行城市：</td>
		<td style="width:200px;">${clmsLoanBank.city}</td> 
	</tr>
	<tr align=center><td colspan=4><input id="clearbtn" class="button" type="button" onclick="ownerDialog.close();" value="关闭" /></td></tr>
</table> 	
</body>
</html>
