<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/taglibs.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
 </head>
 <script>
	
	function submitInfo(){
		if(document.getElementById("loanBankId").value.length==0){
			alert("您还没有创建贷款账户，请先创建账户再来贷款!");
			return false;
		}
		Dialog.confirm('您确认审核通过吗？',function(){
			var loanBankId = "";
			var loanReturnFlag = $("input[name=loanReturnFlag]:checked").val();
			if(loanReturnFlag=='0'){
				loanBankId = $("#loanBankId").val();
			}else if(loanReturnFlag=='1'){
				loanBankId = $("#loanBankId1").val();
			}
				$.ajax( {
				url : "${ctx}/lnbef/loanApplyAudit/auditPass",
				dataType : "json",
				type : "post", 
				data:{
					id:$("#id").val(),
					loanBankId:loanBankId,
					loanReturnFlag:loanReturnFlag
				},
				success : function(data) {
					ownerDialog.doReturn(data);
				}
			});
		}); 
	}
	//关闭对话框
	function cancel(){
		ownerDialog.close();
	}
	
</script>
<body>  
	<form id="form1" id="form1"  method="post">
	<input type="hidden" name="id" id="id" value="${id}"/>
	
	<table class="table" style="width: 100%;">
		<tr style="height:60px">
			<td >企业基本户开户行:</td>
			<td >
			${clmsLoanBank.bank}
			</td> 
		</tr>
		<tr style="height:60px">
			<td colspan=2><input type='radio' checked name="loanReturnFlag" id="loanReturnFlag1" value="0"/>自动代扣模式:</td>
		</tr>
		<tr style="height:60px">
			<td style="width:120px;color:red;" >*选择贷款银行：</td>
			<td >
				<select name="loanBankId" id="loanBankId" >
					<c:forEach items="${loanBankList}" var="o">
						<option value ="${o.id}">${o.branchname}-${o.accNo}</option>
					</c:forEach>
				</select>
			</td> 
		</tr>
		<tr style="height:60px">
			<td colspan=2><input type='radio' name="loanReturnFlag" id="loanReturnFlag2" value="1"/>自行还款模式:</td>
		</tr>
		<tr style="height:60px">
			<td style="width:120px;color:red;" >*选择贷款银行：</td>
			<td >
				<select name="loanBankId1" id="loanBankId1" >
					<c:forEach items="${loanBankList}" var="o">
						<option value ="${o.id}">${o.branchname}-${o.accNo}</option>
					</c:forEach>
				</select>
			</td> 
		</tr>
		<tr align=center>
			<td colspan=2><input name="submitBtn" class="button" type="button" onclick="submitInfo()" value="提交" />
			<input name="cancelBtn" class="button" type="button" onclick="cancel()" value="取消" /></td> 
		</tr>  
	</table>
	</form> 
</body>
</html>
