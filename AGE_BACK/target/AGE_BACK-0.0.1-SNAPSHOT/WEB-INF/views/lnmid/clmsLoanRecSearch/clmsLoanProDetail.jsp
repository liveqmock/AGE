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
		<td style="width:100px;">申贷编号：</td>
		<td style="width:200px;">${clmsLoanPro.applyNo}</td>
		<td style="width:100px;">申贷成功日期：</td>
		<td style="width:200px;">${loanBeginTime}</td> 
	</tr>  
	<tr>
		<td style="width:100px;">招标编号：</td>
		<td style="width:200px;">${clmsLoanPro.tenderNo}</td> 
		<td style="width:100px;">招标资审方式：</td>
		<!-- 资审类型：0=资格预审；1=资格后审 -->
		<td style="width:200px;">${clmsLoanPro.bidCheckType}</td> 
	</tr>  
	<tr>
		<td style="width:100px;">货款类型：</td>
		<td style="width:200px;">${productName}</td> 
		<td style="width:100px;">招标类型：</td>
		<!-- 招标类型：0=施工；1=监理；2=勘察设计；3=试验检测；4=货物采购 -->
		<td style="width:200px;">${clmsLoanPro.bidType}</td> 
	</tr>  
	<tr>
		<td style="width:100px;">项目编码：</td>
		<td style="width:200px;">${clmsLoanPro.proNo}</td> 
		<td style="width:100px;">项目名称：</td>
		<td style="width:200px;">${clmsLoanPro.proName}</td> 
	</tr>
	<tr>
		<td style="width:100px;">标段编码：</td>
		<td style="width:200px;">${clmsLoanPro.bidNo}</td> 
		<td style="width:100px;">标段名称：</td>
		<td style="width:200px;">${clmsLoanPro.bidName}</td> 
	</tr>
	<tr>
		<td style="width:100px;">投标截止日期：</td>
		<td style="width:200px;">${clmsLoanPro.bidFinishDate}</td> 
		<td style="width:100px;">备注：</td>
		<td style="width:200px;">${clmsLoanPro.remark}</td> 
	</tr>
	<tr align=center><td colspan=4><input id="clearbtn" class="button" type="button" onclick="ownerDialog.close();" value="关闭" /></td></tr>
</table> 	
</body>
</html>
