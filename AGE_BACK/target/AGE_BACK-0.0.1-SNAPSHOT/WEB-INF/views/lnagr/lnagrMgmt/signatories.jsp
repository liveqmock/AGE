<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <%@ include file="/common/meta.jsp" %>   
   <%@ include file="/common/taglibs.jsp" %>
   <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>  
</head> 
<script type="text/javascript">
//关闭对话框
	function cancel(){
		ownerDialog.close();
	}
</script>
<body class="mainBody"> 
<form id="form1" name="form1" method="post" >
<div class="form_title" ></div>
<table class="table" style="width: 100%">
<c:forEach items="${orgList}" var="org" varStatus="s">
	<tr>
		<td style="width:15%;">${org.parent}：</td>
		<td style="width:30%;">${org.children}</td>
	</tr>
</c:forEach>
	<!-- <tr>
		<td style="width:15%;">平台：</td>
		<td style="width:30%;">${signatoriesVo.platform}</td>
	</tr>  
	<tr>
		<td style="width:15%;">通道：</td>
		<td style="width:30%;">${signatoriesVo.channel}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">贷款银行：</td>
		<td style="width:30%;">${signatoriesVo.loanBankName}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">贷款企业：</td>
		<td style="width:30%;">${signatoriesVo.loanEnterprise}</td> 
	</tr> 
	<tr> -->
		<td colspan="2" style="text-align:center;"><input class="button" type="button" onclick="cancel()" value="关闭"/></td>
	</tr> 
</table> 	
</form>
</body>
</html>
