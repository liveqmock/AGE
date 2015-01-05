<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/taglibs.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
 </head>
 <script>
	 
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'新贷款申请信息列表',
			url:'${ctx}/lnbef/loanApplyAudit/getTreeGirdList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			
			columns:[[   
	                   {field:'entName',title:'申请企业',width:180,align:'center'},   
	                   {field:'applyNo',title:'申贷编号',width:180,align:'center'},
	                   {field:'loanMoney',title:'贷款意向金',width:180,align:'center'},
	                   {field:'applyTime',title:'申请日期',width:180,align:'center'},
	                   {field:'applyLoanTime',title:'申请放款时间',width:180,align:'center'},//申请放款时间 add 20141203
	                   {field:'bidFinishDate',title:'投标截止日期',width:180,align:'center'},
	                   {field:'operator',title:'操作',width:80,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"view('"+rec.id+"')\">审核</a>";   
	                      	} 
	                   }
	        ]] 
		});
	}  
	
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
	}
	
	//显示审核页面
	function view(id){ 
		var pageSize=$('#grid').datagrid('options').pageSize;
		$("#queryForm").attr("action","${ctx}/lnbef/loanApplyAudit/view?id="+id);
		$("#queryForm").submit();
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
 <script>
 $(function(){  
		if($('#message').val()!=null && $('#message').val()!=""){
			alert($('#message').val());
		}
	});	
 </script>
<body>  
	<%--grid页码--%>
	<input type="hidden" id="message" name="message" value="${message}">
	
	<form id="queryForm"  method="post">

	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>  
	<table class="table" style="width: 100%;margin: 0 0 5 0;">
		<tr>
			<td style="width:15%;">企业名称：</td>
			<td style="width:30%;"><input name="query_entName" type="text" style="width:90%;" /></td> 
			<td style="width:15%;">申请日期：</td>
			<td style="width:30%;"><input  type="text" name="query_applyTime" class="Wdate" onfocus="WdatePicker({lang:'zh',dateFmt:'yyyy-MM-dd'})"  style="width:90%;" onclick="WdatePicker()" /></td>
		</tr> 
		<tr>
			<td style="width:15%;">申请编号：</td>
			<td style="width:30%;"><input name="query_loanNo" type="text" style="width:90%;" /></td> 
			<td style="width:10%;"><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td> 
		</tr>  
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
