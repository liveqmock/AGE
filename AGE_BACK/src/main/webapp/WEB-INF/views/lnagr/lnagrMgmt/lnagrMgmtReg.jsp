<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
</head>
<style type="text/css">
    
</style>
<script>
$(document).ready(function(){	
	if($("#message").val()!=""){
		Dialog.alert($("#message").val());
	}
})
function back(){
	window.location.href = "${ctx}/lnagr/lnagrMgmt/lnagrMgmtList";	
}
function chooseAgreement(){
	var dialog = new Dialog();
		dialog.Width = 850;
		dialog.Height = 520;
		dialog.Title = "选择协议";
		dialog.URL = '${ctx}/lnagr/lnagrMgmt/chooseAgreement';
		dialog.CancelEvent=function(){dialog.close();};
		dialog.doReturn = function(data){
			//Dialog.alert(data.id);
			//Dialog.alert(data.name);
			//流程模版id
			$("#tempFlowId").val(data.id);
			//协议名称
			$("#tempFlowName").val(data.name);
			dialog.close();
		}; 
		dialog.show();
}
function chooseProject(){
	var dialog = new Dialog();
		dialog.Width = 860;
		dialog.Height = 620;
		dialog.Title = "选择关联号";
		dialog.URL = '${ctx}/lnagr/lnagrMgmt/chooseProject';
		dialog.CancelEvent=function(){dialog.close();};
		dialog.doReturn = function(data){
			//贷款申请id
			$("#loanApplyId").val(data.id);
			//贷款关联号
			$("#loanNo").val(data.loanNo);
			//贷款企业名称
			$("#entName").val(data.entName);
			dialog.close();
		}; 
		dialog.show();
}



//贷款关联号 和 贷款企业 任何一个改变 设置空
function changeLoan(){
	//贷款申请id
	$("#loanApplyId").val("");
	//贷款关联号
	$("#loanNo").val("");
	//贷款企业名称
	$("#entName").val("");
}
//选择协议 改变 设置空
function changeFlow(){
	//流程模版id
	$("#tempFlowId").val("");
	//协议名称
	$("#tempFlowName").val("");
}

//保存
function save(){
	if($("#tempFlowName").val()=='' || $("#tempFlowName").val()==null){
		Dialog.alert("请您选择协议");
		return;
	}else if($("#tempFlowName").val().match("贷款")!=null || $("#tempFlowName").val().match("资金划拨")!=null ){
		if($("#loanNo").val()=='' || $("#loanNo").val()==null){
			Dialog.alert("请您选择贷款关联号");
			return;
		}
	}
		Dialog.confirm('确定要提交吗?',function(){
			$("#form").submit();
		})
}
</script>
<style>
select {
	width:300px;
}
.info{
	width:300px;
}
</style>
<body class="mainBody">
<form id="form" action="${ctx}/lnagr/lnagrMgmt/save" method="post">
<input id="message" name="message" type="hidden" value="${message}"/>
<input id="id" name="id" type="hidden" value="${clmsAgreement.id}"/>
<%--流程模版id--%>
<input id="tempFlowId" name="tempFlowId" type="hidden" value="${clmsAgreement.tempFlowId}"/>
<%--贷款申请id--%>
<input id="loanApplyId" name="loanApplyId" type="hidden" value="${clmsAgreement.loanApplyId}"/>

<div class="form_title" >协议信息填写</div>  
<table class="table" style="width:400px">
	<tr>
		<td colspan="2">
		<input id="submitBtn" class="button" type="button" value="提交" onclick="save()"/>
		<input id="backBtn" class="button" type="button" value="返回" onclick="back()" />
		</td>
	</tr>
	<tr>
		<td>选择协议：</td>
		<td><input id="tempFlowName" name="tempFlowName" type="text" value="${clmsAgreement.tempFlowName}" onchange="changeFlow()"/>
		<input type="button" onclick="chooseAgreement()" value="选择" /> 
		</td>
	</tr>
	<tr>
		<td>贷款关联号：</td>
		<td><input id="loanNo" name="loanNo" type="text" value="${clmsAgreement.loanNo}" onchange="changeLoan()"/>
		<input type="button" onclick="chooseProject()" value="选择"/></td> 
	</tr>  
	<tr>
		<td>贷款企业：</td>
		<td ><input id="entName" name="entName" type="text" value="${entName}" onchange="changeLoan()"/></td> 
	</tr>
	<tr>
		<td>备注：</td>
		<td>
			<textarea id="remark" name="remark" rows="5" cols="20">${clmsAgreement.remark}</textarea>
		</td> 
	</tr>  
</table> 	 
</form>		
</body>
</html>
