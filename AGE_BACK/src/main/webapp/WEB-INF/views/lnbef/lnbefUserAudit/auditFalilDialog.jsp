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
		if(document.getElementById('remark').value.length>500){
			Dialog.alert("未通过原因字数不能超过500字符");
			return;
		}
		Dialog.confirm('确认审核不通过？',function(){
		$.ajax( {
			url : "${ctx}/lnbef/lnbefUserAudit/auditFalil",
			dataType : "json",
			type : "post", 
			data:{
				id:$("#id").val(),
				remark:$("#remark").val()
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
		<tr>
			<td >未通过原因：</td>
			<td ><textarea name="remark" id="remark" cols="48" rows="8" ></textarea></td> 
		</tr>
		<tr>
			<td ><input name="submitBtn" class="button" type="button" onclick="submitInfo()" value="提交" /></td>
			<td ><input name="cancelBtn" class="button" type="button" onclick="cancel()" value="取消" /></td> 
		</tr>  
	</table>
	</form> 
</body>
</html>
