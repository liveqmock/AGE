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
	<tr>
		<td style="width:15%;">贷款关联号：</td>
		<td style="width:30%;">${flowProjectVo.loanNo}</td>
	</tr>  
	<tr>
		<td style="width:15%;">贷款项目名称：</td>
		<td style="width:30%;">${flowProjectVo.proName}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">贷款标段名称：</td>
		<td style="width:30%;">${flowProjectVo.bidName}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">贷款金额：</td>
		<td style="width:30%;">${flowProjectVo.loanMoney}</td> 
	</tr>  
	<tr>
		<td colspan="2" style="text-align:center;"><input class="button" type="button" onclick="cancel()" value="关闭"/></td>
	</tr> 
</table> 	
</form>
</body>
</html>
