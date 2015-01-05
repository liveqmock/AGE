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
		if(isEmpty($("#orgName").val())){
		 	Dialog.alert('请输入机构名称！');return;
		 }
		
		if($("#pageType").val()=='edit'){
			if(isEmpty($("#orderNo").val()) || !isDigit($("#orderNo").val())){
			 	Dialog.alert('顺序号应为数字！');return;
			}
		}
		Dialog.confirm("确定要保存吗？",function(){
			 $.ajax( {
				url : "${ctx}/sysmng/sysOrg/save",
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
<input id="id" name="id" type="hidden" value="${sysOrg.id}" />
<input id="parentId" name="parentId" type="hidden" value="${parentId}" />
<div class="form_title" >机构信息信息</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">机构名称：</td>
		<td style="width:30%;"><input id="orgName" name="orgName" type="text" value="${sysOrg.orgName}" style="width:90%;" maxlength="25"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">机构联系电话：</td>
		<td style="width:30%;"><input id="phone" name="phone" type="text" value="${sysOrg.phone}" style="width:90%;" maxlength="20"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">机构地址：</td>
		<td style="width:30%;"><input id="address" name="address" type="text" value="${sysOrg.address}" style="width:90%;" maxlength="50"/></td> 
	</tr>
	<c:if test="${pageType=='edit'}">   
	<tr>
		<td style="width:15%;">顺序号：</td>
		<td style="width:30%;"><input id="orderNo" name="orderNo" type="text" value="${sysOrg.orderNo}" style="width:90%;" maxlength="8"/></td> 
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
