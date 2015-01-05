<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
	<script type="text/javascript" src="${ctx}/scripts/jquery/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDialog.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/validate.js"></script>
</head>
<script>
	function save(){
		if(isEmpty($("#dictName").val())){
		 	Dialog.alert('请输入机构名称！');return;
		 }
		
		if(isEmpty($("#dictValue").val())){
		 	Dialog.alert('请输入值！');return;
		}
		Dialog.confirm("确定要保存吗？",function(){
			 $.ajax( {
				url : "${ctx}/sysmng/sysDictValue/save",
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
<input id="id" name="id" type="hidden" value="${sysDictValue.id}" />
<input id="sysDictId" name="sysDictId" type="hidden" value="${sysDictId}" />
<div class="form_title" >系统字典值信息</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">名称：</td>
		<td style="width:30%;"><input id="dictName" name="dictName" type="text" value="${sysDictValue.dictName}" style="width:90%;"  maxlength="15"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">值：</td>
		<td style="width:30%;"><input id="dictValue" name="dictValue" type="text" value="${sysDictValue.dictValue}" style="width:90%;"  maxlength="100"/></td> 
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
