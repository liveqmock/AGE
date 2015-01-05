<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <%@ include file="/common/meta.jsp" %>   
   <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>  
</head> 
<script type="text/javascript">
//审核通过
function auditPass(){
		var diag = new Dialog();
		diag.Title = "";
		diag.Width = 500;
		diag.Height = 400;
		diag.URL = "${ctx}/lnbef/loanApplyAudit/auditPassDialog?id="+$('#id').val()+"&clmsUserId="+$('#clmsUserId').val();
		diag.doReturn = function(data){
			diag.close();
			$("#passBtn").hide();
			$("#failBtn").hide();
			Dialog.alert(data.message);
		}; 
		diag.show();
}

//审核未通过
function auditFalil(){
		var diag = new Dialog();
		diag.Title = "未通过原因";
		diag.Width = 500;
		diag.Height = 200;
		diag.URL = "${ctx}/lnbef/loanApplyAudit/auditFalilDialog?id="+$('#id').val();
		diag.doReturn = function(data){
			diag.close();
			$("#passBtn").hide();
			$("#failBtn").hide();
			Dialog.alert(data.message);
		}; 
		diag.show(); 
}

//审核未通过
function downLoad(id,fileType){
	$("#form1").attr("action","${ctx}/lnbef/loanApplyAudit/downLoad?id="+id+"&fileType="+fileType);
	$("#form1").submit();
}

//返回前一页
function backPrePage(){
	$("#form1").attr("action","${ctx}/lnbef/loanApplyAudit/list");
	$("#form1").submit();
}
</script>
<body class="mainBody"> 
<form id="form1" name="form1" method="post" >
<div class="form_title" ></div> 
<%--贷款申请id--%>
<input type="hidden" id="id" name="id" value="${clmsLoanApplyVO.id}">
<%--贷款用户id--%>
<input type="hidden" id="clmsUserId" name="clmsUserId" value="${clmsLoanApplyVO.clmsUserId}">


<input id="passBtn" name="passBtn" class="button" type="button" onclick="auditPass()" value="审核通过" />
<input id="failBtn" name="failBtn" class="button" type="button" onclick="auditFalil()" value="审核未通过" />
<input id="backBtn" name="backBtn" class="button" type="button" onclick="backPrePage()" value="返回" />
 
<table class="table" style="width: 100%;margin-top:10px;" >
	<tr>
		<td style="width:125px;">申请信息验证：</td>
		<td style="width:200px;<c:if test="${clmsLoanApplyVO.applyCheckType=='1'}">color:red;</c:if><c:if test="${clmsLoanApplyVO.applyCheckType=='0'}">color:green;</c:if>" >
		<b>${clmsLoanApplyVO.applyCheckTypeCN}</b>
		</td>
		<td style="width:100px;">企业名称：</td>
		<td style="width:200px;">${clmsLoanApplyVO.entName}</td> 
	</tr>  
	<tr>
		<td style="width:125px;">申请信息验证描述：</td>
		<td style="width:200px;">${clmsLoanApplyVO.applyCheckResult}</td> 
		<td style="width:100px;">申贷编号：</td>
		<td style="width:200px;">${clmsLoanApplyVO.applyNo}</td> 
	</tr>  
	<tr>
		<td style="width:125px;">贷款类型：</td>
		<td style="width:200px;">${clmsLoanApplyVO.productName}</td> 
		<td style="width:100px;">招标编号：</td>
		<td style="width:200px;">${clmsLoanApplyVO.tenderNo}</td> 
	</tr>  
	<tr>
		<td style="width:125px;">申请日期：</td>
		<td style="width:200px;">${clmsLoanApplyVO.applyTime}</td> 
		<td style="width:100px;">项目编码：</td>
		<td style="width:200px;">${clmsLoanApplyVO.proNo}</td> 
	</tr>
	<tr>
		<td style="width:125px;">项目名称：</td>
		<td style="width:200px;">${clmsLoanApplyVO.proName}</td> 
		<td style="width:100px;">标段编码：</td>
		<td style="width:200px;">${clmsLoanApplyVO.bidNo}</td> 
	</tr>
	<tr>
		<td style="width:125px;">标段名称：</td>
		<td style="width:200px;">${clmsLoanApplyVO.bidName}</td> 
		<td style="width:100px;">招标资审方式：</td>
		<!-- 资审类型：0=资格预审；1=资格后审 -->
		<td style="width:200px;">${clmsLoanApplyVO.bidCheckTypeCN}</td> 
	</tr>
	<tr>
		<td style="width:125px;">招标类型：</td>
		<!-- 招标类型：0=施工；1=监理；2=勘察设计；3=试验检测；4=货物采购 -->
		<td style="width:200px;">${clmsLoanApplyVO.bidTypeCN}</td> 
		<td style="width:100px;">招标代理：</td>
		<td style="width:200px;">${clmsLoanApplyVO.tenderAgent}</td> 
	</tr>
	<tr>
		<td style="width:125px;">招标单位：</td>
		<td style="width:200px;">${clmsLoanApplyVO.tenderComp}</td> 
		<td style="width:100px;">投标截止日期：</td>
		<td style="width:200px;">${clmsLoanApplyVO.bidFinishDate}</td> 
	</tr>
	<tr>
		<td style="width:125px;">贷款意向金：</td>
		<td style="width:200px;">${clmsLoanApplyVO.loanMoney}元</td> 
		<td style="width:100px;">备注：</td>
		<td style="width:200px;">${clmsLoanApplyVO.remark}</td> 
	</tr>
</table> 	
</form>
</body>
</html>
