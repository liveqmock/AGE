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
		if(isEmpty($("#roleNo").val())){
		 	Dialog.alert('请输入角色代码！');return;
		 }
		if(isEmpty($("#roleName").val())){
		 	Dialog.alert('请输入角色名称！');return;
		 }
		if($("#pageType").val()=='edit'){
			if(isEmpty($("#orderNo").val()) || !isDigit($("#orderNo").val())){
			 	Dialog.alert('顺序号应为数字！');return;
			}
		}
		
		Dialog.confirm("确定要保存吗？",function(){
			 $.ajax( {
				url : "${ctx}/sysmng/sysRole/save",
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
		});
	}
</script>
<body class="mainBody">
<form id="form">
<%--编辑 edit--%>
<input id="pageType" name="pageType" type="hidden" value="${pageType}" />
<input id="id" name="id" type="hidden" value="${sysRole.id}" />
<div class="form_title" >角色信息</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">角色代码：</td>
		<td style="width:30%;"><input id="roleNo" name="roleNo" type="text" value="${sysRole.roleNo}" style="width:90%;" maxlength="15"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">角色名称：</td>
		<td style="width:30%;"><input id="roleName" name="roleName" type="text" value="${sysRole.roleName}" style="width:90%;" maxlength="15"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">描述：</td>
		<td style="width:30%;"><input id="roleDesc" name="roleDesc" type="text" value="${sysRole.roleDesc}" style="width:90%;" maxlength="50"/></td> 
	</tr>  
	<c:if test="${pageType=='edit'}">   
	<tr>
		<td style="width:15%;">顺序号：</td>
		<td style="width:30%;"><input id="orderNo" name="orderNo" type="text" value="${sysRole.orderNo}" style="width:90%;" maxlength="8"/></td> 
	</tr>  
	</c:if>
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
