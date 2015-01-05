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
		 if(isEmpty($("#userAccount").val())){
		 	Dialog.alert('请输入用户账号！');return;
		 }
		 if(isEmpty($("#userName").val())){
		 	Dialog.alert('请输入用户名称！');return;
		 }
		 if($("#pageType").val()=='edit'){
			 if(isEmpty($("#orderNo").val()) || !isDigit($("#orderNo").val())){
			 	Dialog.alert('顺序号应为数字！');return;
			 }
		 }
		 Dialog.confirm("确定要保存吗？",function(){
			 $.ajax( {
				url : "${ctx}/sysmng/sysUser/save",
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
<input id="id" name="id" type="hidden" value="${sysUser.id}" />
<input id="orgId" name="orgId" type="hidden" value="${orgId}" />
<div class="form_title" >用户信息</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">用户账号：</td>
		<td style="width:30%;"><input id="userAccount" name="userAccount" type="text" value="${sysUser.userAccount}" style="width:90%;" maxlength="25"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">用户名称：</td>
		<td style="width:30%;"><input id="userName" name="userName" type="text" value="${sysUser.userName}" style="width:90%;" maxlength="150"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">初始密码：</td>
		<td style="width:30%;"><input id="password" name="password" type="text" value="${sysUser.password}" style="width:90%;" maxlength="50"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">手机：</td>
		<td style="width:30%;"><input id="mobile" name="mobile" type="text" value="${sysUser.mobile}" style="width:90%;" maxlength="30"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">固定电话：</td>
		<td style="width:30%;"><input id="phone" name="phone" type="text" value="${sysUser.phone}" style="width:90%;" maxlength="30"/></td> 
	</tr> 
	 <c:if test="${pageType=='edit'}">
	<tr>
		<td style="width:15%;">顺序号：</td>
		<td style="width:30%;"><input id="orderNo" name="orderNo" type="text" value="${sysUser.orderNo}" style="width:90%;" maxlength="8"/></td> 
	</tr>
	</c:if>
	<tr>
		<td style="width:15%;">账号状态：</td>
		<td style="width:30%;">
			<input type="radio" name="state" ${sysUser.state=='1' || sysUser.state == null ?'checked':''} value="1"/>可用
			<input type="radio" name="state" ${sysUser.state=='0'?'checked':''} value="0"/>禁用 
		</td> 
	</tr>
	<tr>
		<td style="width:15%;">CA：</td>
		<td style="width:30%;"><input id="ca" name="ca" type="text" value="${sysUser.ca}" style="width:90%;" maxlength="30"/></td> 
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
