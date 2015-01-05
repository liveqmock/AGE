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
		if(isEmpty($("#flowNo").val())){  
		 	Dialog.alert('请输入编号！');return;
		 }
		if(isEmpty($("#name").val())){
		 	Dialog.alert('请输入名称！');return;
		 }
		if(isEmpty($("#displayName").val())){
		 	Dialog.alert('请输入显示名称！');return;
		 }
		if($("#pageType").val()=='edit'){
			if(isEmpty($("#orderNo").val()) || !isDigit($("#orderNo").val())){
			 	Dialog.alert('顺序号应为数字！');return;
			}
		}
		Dialog.confirm("确定要保存吗？",function(){
			 $.ajax( {
				url : "${ctx}/sysmng/sysTempFlow/save",
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
<input id="id" name="id" type="hidden" value="${sysTempFlow.id}" />
<div class="form_title" >流程模板</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">编号：</td>
		<td style="width:30%;"><input id="flowNo" name="flowNo" type="text" value="${sysTempFlow.flowNo}" style="width:90%;" maxlength="25"/></td> 
	</tr>   
	<tr>
		<td style="width:15%;">名称：</td>
		<td style="width:30%;"><input id="name" name="name" type="text" value="${sysTempFlow.name}" style="width:90%;" maxlength="200"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">显示名称：</td>
		<td style="width:30%;"><input id="displayName" name="displayName" type="text" value="${sysTempFlow.displayName}" style="width:90%;" maxlength="200"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">备注：</td>
		<td style="width:30%;"><input id="remark" name="remark" type="text" value="${sysTempFlow.remark}" style="width:90%;" maxlength="500"/></td> 
	</tr>  
 
	<c:if test="${pageType=='edit'}">    
	<tr>
		<td style="width:15%;">顺序号：</td>
		<td style="width:30%;"><input id="orderNo" name="orderNo" type="text" value="${sysTempFlow.orderNo}" style="width:90%;" maxlength="8"/></td> 
	</tr>  
	 </c:if>
	 <tr>
		<td >账号状态：</td>
		<td>
			<input type="radio" name="state" ${sysTempFlow.state=='1' || sysTempFlow.state == null ?'checked':''} value="1"/>可用
			<input type="radio" name="state" ${sysTempFlow.state=='0'?'checked':''} value="0"/>禁用 
		</td> 
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
