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
	Dialog.confirm('您确认审核通过吗？',function(){
			$.ajax( {
			url : "${ctx}/lnbef/lnbefUserAudit/auditPass",
			dataType : "json",
			type : "post", 
			data:{
				id:$("#id").val()
			},
			success : function(data) {
				Dialog.alert(data.message, function(){
					window.location.href="${ctx}/lnbef/lnbefUserAudit/list";
				});
			}
		});
	}); 
}

//审核未通过
function auditFalil(){
		var diag = new Dialog();
		diag.Title = "未通过原因";
		diag.Width = 500;
		diag.Height = 200;
		diag.URL = "${ctx}/lnbef/lnbefUserAudit/auditFalilDialog?id="+$('#id').val();
		diag.doReturn = function(data){
			diag.close();
			Dialog.alert(data.message, function(){
				window.location.href="${ctx}/lnbef/lnbefUserAudit/list";
			});
		}; 
		diag.show(); 
}

//审核未通过
function downLoad(id,fileType){
	window.open ("${ctx}/lnbef/lnbefUserAudit/downLoad?id="+id+"&fileType="+fileType);
}

//返回前一页
function backPrePage(){
	$("#form1").attr("action","${ctx}/lnbef/lnbefUserAudit/list");
	$("#form1").submit();
}
</script>
<body class="mainBody"> 
<form id="form1" name="form1" method="post" >
<div class="form_title" ></div> 
<%--贷款注册用户id--%>
<input type="hidden" id="id" name="id" value="${clmsUserReg.id}">

<input name="passBtn" id="passBtn" class="button" type="button" onclick="auditPass()" value="审核通过" />
<input name="failBtn" id="failBtn" class="button" type="button" onclick="auditFalil()" value="审核未通过" />
<input name="backBtn" id="backBtn" class="button" type="button" onclick="backPrePage()" value="返回" />
 
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">企业名称：</td>
		<td style="width:30%;">${clmsUserReg.entName}</td>
		<td style="width:15%;">经办人：</td>
		<td style="width:30%;">${clmsUserReg.entHandleName}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">法人代表：</td>
		<td style="width:30%;">${clmsUserReg.entLegalName}</td> 
		<td style="width:15%;">经办人邮箱：</td>
		<td style="width:30%;">${clmsUserReg.userName}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">组织机构代码：</td>
		<td style="width:30%;">${clmsUserReg.entOrgNo}</td> 
		<td style="width:15%;">经办人手机号码：</td>
		<td style="width:30%;">${clmsUserReg.entHandleMobile}</td> 
	</tr>  
	<tr>
		<td style="width:15%;">营业执照：</td>
		<td style="width:30%;"><a href="javascript:void(0)" onclick="downLoad($('#id').val(),'1')">下载</a></td> 
		<td style="width:15%;">组织机构代码证：</td>
		<td style="width:30%;"><a href="javascript:void(0)" onclick="downLoad($('#id').val(),'2')">下载</a></td>
	</tr>  
	<tr>
		<td style="width:15%;">税务登记证：</td>
		<td style="width:30%;"><a href="javascript:void(0)" onclick="downLoad($('#id').val(),'3')">下载</a></td> 
		<td style="width:15%;">银行开户许可证：</td>
		<td style="width:30%;"><a href="javascript:void(0)" onclick="downLoad($('#id').val(),'4')">下载</a></td> 
	</tr>  
	<tr>
		<td style="width:15%;">申请日期：</td>
		<td style="width:30%;">${clmsUserReg.applyTime}</td> 
	</tr>  
</table> 	
</form>
</body>
</html>
