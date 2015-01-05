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
		 if(isEmpty($("#oldPassword").val())){
		 	Dialog.alert('请输入原密码！');return;
		 }
		 if(isEmpty($("#newPassword").val())){
		 	Dialog.alert('请输入新密码！');return;
		 }
		 if(isEmpty($("#newPassword1").val())){
		 	Dialog.alert('请输入新密码确认！');return;
		 }
		 
		 if($("#newPassword").val() != $("#newPassword1").val()){
		 	Dialog.alert('新密码确认不正确！');return;
		 }
		 Dialog.confirm("确定要保存吗？",function(){
			 $.ajax( {
				url : "${ctx}/sysmng/sysUser/saveNewPwd",
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
<div class="form_title" >输入密码</div>  
<table class="table" style="width: 100%"> 
	<tr>
		<td style="width:15%;">原密码：</td>
		<td style="width:30%;"><input id="oldPassword" name="oldPassword" type="password" value="" style="width:90%;"/></td> 
	</tr>   
	<tr>
		<td style="width:15%;">新密码：</td>
		<td style="width:30%;"><input id="newPassword" name="newPassword" type="password" value="" style="width:90%;"/></td> 
	</tr>   
	<tr>
		<td style="width:15%;">新密码确认：</td>
		<td style="width:30%;"><input id="newPassword1" name="newPassword1" type="password" value="" style="width:90%;"/></td> 
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
