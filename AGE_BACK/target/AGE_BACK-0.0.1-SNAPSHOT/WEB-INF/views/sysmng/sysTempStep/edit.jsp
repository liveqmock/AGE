<%@ page language="java" import="java.util.*,com.drcgw.clms.common.Constants" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>
	<script type="text/javascript" src="${ctx}/scripts/util/validate.js"></script>
	<style type="text/css">
	.td-even{text-align:right;width: 30%;}
	.td-odd{text-align:left;width: 70%;}
	</style>
	<% 
		request.setAttribute("ownerType",Constants.ownerType);
	%>
</head> 
<script>
	function save(){ 
		if(isEmpty($("#stepName").val())){
		 	Dialog.alert('请输入名称！');return;
		 }
		if(isEmpty($("#displayName").val())){
		 	Dialog.alert('请输入显示名称！');return;
		 }
		if(isEmpty($("#operateUrl").val())){
		 	Dialog.alert('请输入操作界面！');return;
		 }
		
		/** if($("#ownerType").val()==''){ 
		 	Dialog.alert('请选择类型！');return;
		 }
		if($("#ownerName").val() == ''){
		 	Dialog.alert('拥有者不能为空！');return;
		 }*/
		if($("#pageType").val()=='edit'){
			if(isEmpty($("#orderNo").val()) || !isDigit($("#orderNo").val())){
			 	Dialog.alert('顺序号应为数字！');return;
			}
		}
		if(isEmpty($("#stepNo").val())){  
		 	Dialog.alert('请输入环节编号！');return;
		 }
		Dialog.confirm("确定要保存吗？",function(){
			 $.ajax( {
				url : "${ctx}/sysmng/sysTempStep/save",
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
	function chooseOwner(){
		var type = $('#ownerType').val()
		if(type == 'undefined' || type==''){
			Dialog.alert('先选择类型');
			return;
		}
		var dialog = new Dialog();
		dialog.Width = 750;
		dialog.Height = 520;
		dialog.Title = "选择拥有者";
		dialog.URL = '${ctx}/sysmng/sysTempStep/chooseOwner_'+type;
		dialog.CancelEvent=function(){dialog.close();};
		dialog.doReturn = function(data){
			if(type == 0){
				
			}else if(type == 1){
				$("#ownerId").val(data.id);
				$("#ownerName").val(data.orgName);
			}else if(type == 2){
				$("#ownerId").val(data.id);
				$("#ownerName").val(data.userName);
			}
			
			dialog.close();
		}; 
		dialog.show();
	}
	function clearOwner(){
		$("#ownerName").val("");
		$("#ownerId").val("");
	}
	$(document).ready(function(){
		$("table td:even").addClass("td-even");
		$("table td:odd").addClass("td-odd");
		var ownerType = '${sysTempStep.ownerType}';
		if(ownerType != ''){
			$('#ownerType').val(ownerType);
		}
	});

</script>
<body class="mainBody">
<form id="form">
<%--编辑 edit--%>
<input id="pageType" name="pageType" type="hidden" value="${pageType}" />
<input id="id" name="id" type="hidden" value="${sysTempStep.id}" />
<div class="form_title" >环节模板</div>  
<table class="table" style="width: 100%">
	<tr>
		<td>名称：</td>
		<td><input id="stepName" name="stepName" type="text" value="${sysTempStep.stepName}" style="width:90%;" maxlength="200"/></td> 
	</tr>   
	<tr>
		<td>显示名称：</td>
		<td><input id="displayName" name="displayName" type="text" value="${sysTempStep.displayName}" style="width:90%;" maxlength="200"/></td> 
	</tr>  
	<tr>
		<td>备注：</td>
		<td><input id="remark" name="remark" type="text" value="${sysTempStep.remark}" style="width:90%;" maxlength="500"/></td> 
	</tr>  
	<tr>
		<td>操作界面：</td>
		<td><input id="operateUrl" name="operateUrl" type="text" value="${sysTempStep.operateUrl}" style="width:90%;" maxlength="100"/></td> 
	</tr>  
	<tr>
		<td>拥有者类型：</td>
		<td>
		<select id="ownerType" name="ownerType" onchange="clearOwner()">
			<option value=""></option>
			<c:forEach var="m" items="${requestScope.ownerType}" >
				<option value="${m.key}">${m.value}</option>
			</c:forEach>
		</select>
		</td> 
	</tr>  
	<tr>
		<td>拥有者：</td>
		<td>
		<input id="ownerName" name="ownerName" type="text" value="${sysTempStep.ownerName}" style="width:50%;" readonly="readonly"/>
		<input id="ownerId" name="ownerId" type="hidden" value="${sysTempStep.ownerId}"/>
		<input type="button" onclick="chooseOwner()" value="选择">
		</td> 
	</tr>  
	<c:if test="${pageType=='edit'}">   
	<tr>
		<td>顺序号：</td>
		<td><input id="orderNo" name="orderNo" type="text" value="${sysTempStep.orderNo}" style="width:90%;" maxlength="8"/></td> 
	</tr> 
	 	</c:if>
	<tr>
		<td>环节编号：</td>
		<td><input id="stepNo" name="stepNo" type="text" value="${sysTempStep.stepNo}" style="width:90%;" maxlength="10"/></td> 
	</tr>  
	<tr>
		<td >账号状态：</td>
		<td>
			<input type="radio" name="state" ${sysTempStep.state=='1' || sysTempStep.state == null ?'checked':''} value="1"/>可用
			<input type="radio" name="state" ${sysTempStep.state=='0'?'checked':''} value="0"/>禁用 
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
