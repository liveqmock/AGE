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
		<td style="width:150px;">招标单位名称：</td>
		<td style="width:200px;">${clmsLoanPro.tenderComp}</td>
		<td style="width:150px;">招标代理单位名称：</td>
		<td style="width:200px;">${clmsLoanPro.tenderAgent}</td> 
	</tr>  
	<tr>
		<td style="width:150px;">基本户银行编号：</td>
		<td style="width:200px;">${clmsLoanPro.tenderCompBank}</td> 
		<td style="width:150px;">基本户银行编号：</td>
		<td style="width:200px;">${clmsLoanPro.tenderAgentBank}</td> 
	</tr>  
	<tr>
		<td style="width:150px;">基本户账户名称：</td>
		<td style="width:200px;">${clmsLoanPro.tenderCompAccName}</td> 
		<td style="width:150px;">基本户账户名称：</td>
		<td style="width:200px;">${clmsLoanPro.tenderAgentAccName}</td> 
	</tr>  
	<tr>
		<td style="width:150px;">基本户帐号：</td>
		<td style="width:200px;">${clmsLoanPro.tenderCompAccNo}</td> 
		<td style="width:150px;">基本户帐号：</td>
		<td style="width:200px;">${clmsLoanPro.tenderAgentAccNo}</td> 
	</tr>
	<tr>
		<td style="width:150px;">基本户分支行名称：</td>
		<td style="width:200px;">${clmsLoanPro.tenderCompBranchname}</td> 
		<td style="width:150px;">基本户分支行名称：</td>
		<td style="width:200px;">${clmsLoanPro.tenderAgentBranchname}</td> 
	</tr>
	<tr>
		<td style="width:150px;">基本户分支行省份：</td>
		<td style="width:200px;">${clmsLoanPro.tenderCompBankprovince}</td> 
		<td style="width:150px;">基本户分支行省份：</td>
		<td style="width:200px;">${clmsLoanPro.tenderAgentBankprovince}</td> 
	</tr>
	<tr>
		<td style="width:150px;">基本户分支行城市：</td>
		<td style="width:200px;">${clmsLoanPro.tenderCompCity}</td> 
		<td style="width:150px;">基本户分支行城市：</td>
		<td style="width:200px;">${clmsLoanPro.tenderAgentCity}</td> 
	</tr>
	<tr align=center><td colspan=4><input id="clearbtn" class="button" type="button" onclick="ownerDialog.close();" value="关闭" /></td></tr>
</table> 	
</body>
</html>
