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
		if(isEmpty($("#resName").val())){
		 	Dialog.alert('请输入资源名称！');return;
		}
		if($("#pageType").val()=='edit'){
			if(isEmpty($("#orderNo").val()) || !isDigit($("#orderNo").val())){
			 	Dialog.alert('顺序号应为数字！');return;
			}
		}
		Dialog.confirm("确定要保存吗？",function(){
			 $.ajax( {
				url : "${ctx}/sysmng/sysResource/save",
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
<input id="id" name="id" type="hidden" value="${sysResource.id}" />
<input id="parentId" name="parentId" type="hidden" value="${parentId}" />
<div class="form_title" >资源信息</div>  
<table class="table" style="width: 100%">
	<tr>
		<td style="width:15%;">资源代码：</td>
		<td style="width:30%;"><input id="resNo" name="resNo" type="text" value="${sysResource.resNo}" style="width:90%;"  maxlength="100"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">资源名称：</td>
		<td style="width:30%;"><input id="resName" name="resName" type="text" value="${sysResource.resName}" style="width:90%;" maxlength="15"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">显示名称：</td>
		<td style="width:30%;"><input id="displayName" name="displayName" type="text" value="${sysResource.displayName}" style="width:90%;" maxlength="15"/></td> 
	</tr>  
	<tr>
		<td style="width:15%;">资源类型：</td>
		<td style="width:30%;">
			<input type="radio" name="type" ${sysResource.type=='0' || sysResource.type == null ?'checked':''} value="0"/>模块
			<input type="radio" name="type" ${sysResource.type=='1'?'checked':''} value="1"/>页面 
			<input type="radio" name="type" ${sysResource.type=='2'?'checked':''} value="2"/>操作 
		</td> 
	</tr>  
	<tr>
		<td style="width:15%;">访问地址：</td>
		<td style="width:30%;"><input id="url" name="url" type="text" value="${sysResource.url}" style="width:90%;"  maxlength="100"/></td> 
	</tr>  
	<c:if test="${pageType=='edit'}">   
	<tr>
		<td style="width:15%;">顺序号：</td>
		<td style="width:30%;"><input id="orderNo" name="orderNo" type="text" value="${sysResource.orderNo}" style="width:90%;" maxlength="8"/></td> 
	</tr>  
	</c:if>
	<tr>
		<td style="width:15%;">描述：</td>
		<td style="width:30%;"><input id="resDesc" name="resDesc" type="text" value="${sysResource.resDesc}" style="width:90%;" maxlength="100"/></td> 
	</tr>
	
	<tr>
		<td style="width:15%;">CA验证：</td>
		<td style="width:30%;">
		<input type="radio" name="caFlag" ${sysResource.caFlag==true ? 'checked':''} value="1"/>需要
			<input type="radio" name="caFlag" ${sysResource.caFlag==false || sysResource.caFlag == null ?'checked':''} value="0"/>不需要 
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
