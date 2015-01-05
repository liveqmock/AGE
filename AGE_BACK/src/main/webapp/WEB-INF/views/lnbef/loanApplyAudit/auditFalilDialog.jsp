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
		if(document.getElementById('factReason').value.length>200){
			alert("内部原因不能超过200字符");
		}
			$.ajax( {
				url : "${ctx}/lnbef/loanApplyAudit/auditFalil",
				dataType : "json",
				type : "post", 
				data:{
					id:$("#id").val(),
					noPassResean:$("#noPassResean").val(),
					factReason:$("#factReason").val()
				},
				success : function(data) {
					ownerDialog.doReturn(data);
				}
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
		<tr>
			<td >未通过原因：</td>
			<td >
				<select name="noPassResean" id="noPassResean"  >
					<option value ="0">申请信息不符合</option>
					<option value ="1">贷款信息不符合</option>
					<option value ="99">其他</option>
				</select>
			</td> 
		</tr>
		<tr>
			<td >内部原因：</td>
			<td ><textarea name="factReason" id="factReason" cols="48" rows="8" ></textarea></td> 
		</tr>
		<tr>
			<td ><input name="submitBtn" class="button" type="button" onclick="submitInfo()" value="提交" /></td>
			<td ><input name="cancelBtn" class="button" type="button" onclick="cancel()" value="取消" /></td> 
		</tr>  
	</table>
	</form> 
</body>
</html>
