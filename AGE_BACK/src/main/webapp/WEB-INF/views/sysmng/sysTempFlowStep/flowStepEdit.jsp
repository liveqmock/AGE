<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
	
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDialog.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/validate.js"></script>
</head> 
<script>
	function save(){ 
		/*if(isEmpty($("#roleNo").val())){ 
		 	Dialog.alert('请输入角色代码！');return;
		 }
		if(isEmpty($("#roleName").val())){
		 	Dialog.alert('请输入角色名称！');return;
		 }
		if(isEmpty($("#orderNo").val()) || !isDigit($("#orderNo").val())){
		 	Dialog.alert('顺序号应为数字！');return;
		}
		*/
		 $.ajax( {
			url : "${ctx}/sysmng/sysTempFlowStep/save",
			dataType : "json",
			type : "post", 
			data:$("#form").serialize(),
			success : function(data) {
				  if(data.isSuccess){ 
					   ownerDialog.doReturn(data);
				   }else{
					   Dialog.alert(data.message); 
				   }
			}
		});
	}
</script>
<body class="mainBody">
<form id="form">
<input id="id" name="id" type="hidden" value="${sysTempStep.id}" />
<div class="form_title" >环节模板</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">名称：</td>
		<td style="width:30%;"><input id="stepName" name="stepName" type="text" value="${sysTempStep.stepName}" style="width:90%;"/></td> 
	</tr>   
	<tr>
		<td style="width:15%;">显示名称：</td>
		<td style="width:30%;"><input id="displayName" name="displayName" type="text" value="${sysTempStep.displayName}" style="width:90%;"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">备注：</td>
		<td style="width:30%;"><input id="remark" name="remark" type="text" value="${sysTempStep.remark}" style="width:90%;"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">操作界面：</td>
		<td style="width:30%;"><input id="operateUrl" name="operateUrl" type="text" value="${sysTempStep.operateUrl}" style="width:90%;"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">拥有者：</td>
		<td style="width:30%;"><input id="ownerName" name="ownerName" type="text" value="${sysTempStep.ownerName}" style="width:90%;"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">顺序号：</td>
		<td style="width:30%;"><input id="orderNo" name="orderNo" type="text" value="${sysTempStep.orderNo}" style="width:90%;"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">环节编号：</td>
		<td style="width:30%;"><input id="stepNo" name="stepNo" type="text" value="${sysTempStep.stepNo}" style="width:90%;"/></td> 
	</tr>  
	<tr>
		<td colspan="4" style="height:35px;" align="center"> 
			<input name="saveBtn" class="button" type="button" onclick="save()" value="保存" />
			<input name="closeBtn" class="button" type="button" onclick="ownerDialog.close();" value="关闭" />
		</td>
	</tr>  
</table> 	 
</form>		
</body>
</html>
