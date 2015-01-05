<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
 </head>
 <script>
	 
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'贷款申请进度查询列表',
			url:'${ctx}/lncenter/loanApplySchedule/getList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			
			columns:[[   
	                   {field:'loanType',title:'贷款类型',width:180,align:'center'},   
	                   {field:'proName',title:'项目名称',width:180,align:'center'},
	                   {field:'loanMoney',title:'贷款意向金',width:180,align:'center'},
	                   {field:'applyNo',title:'申贷编号',width:180,align:'center'},
	                   {field:'applyTime',title:'申请日期',width:180,align:'center'},
	                   {field:'applyLoanTime',title:'申请放款时间',width:180,align:'center'},//申请放款时间 add 20141203
	                   {field:'auditStatusCN',title:'审核进度',width:180,align:'center'},
	                   {field:'noPassReseanCN',title:'备注',width:180,align:'center'}
	        ]] 
		});
	}  
	
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
	}

	function query(){ 
		var params = {};
		var fields =$('#queryForm').serializeArray();
		$.each( fields, function(i, field){                        
			params[field.name] = field.value; 
		});  
		$('#grid').datagrid('options').queryParams = params;
		$('#grid').datagrid('options').pageNumber = 1;
		refreshGrid();
	}
</script>
<body>  
	<%--grid页码--%>
	<form id="queryForm"  method="post">

	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>  
	<table class="table" style="width: 100%;margin: 0 0 5 0;">
		<tr>
			<td style="width:15%;">项目名称：</td>
			<td style="width:30%;"><input name="proName" type="text" style="width:90%;" /></td> 
			<td style="width:15%;">贷款意向金：</td>
			<td style="width:30%;"><input  type="text" name="loanMoney"   style="width:90%;"  /></td>
		</tr>  
		<tr>
			<td style="width:15%;">审核进度：</td>
			<td style="width:30%;">
				<select name="auditStatus">
					<option value="">全部</option>
					<option value="1">审核中</option>
					<option value="2">审核通过</option>
					<option value="3">审核未通过</option>
				</select>
			</td> 
			<td style="width:10%;" colspan=2 algin=center><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td>
		</tr>  
		
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
