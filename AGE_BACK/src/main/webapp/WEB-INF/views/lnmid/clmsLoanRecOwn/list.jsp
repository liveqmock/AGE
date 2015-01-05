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
			title:'待发起列表',
			url:'${ctx}/lnmid/clmsLoanRecOwn/getList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true, 
			singleSelect:true,
			idField:'id',
			//fitColumns:true,
			//fit:true,
			frozenColumns:[[ 
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[
					{field:'entName',title:'贷款企业',width:150,align:'center'},
					{field:'loanNo',title:'贷款关联号',width:150,align:'center'},
					{field:'loanMoney',title:'贷款金额',width:100,align:'center'},
					{field:'operate1',title:'项目明细',width:100,align:'center',formatter : function(val,row){
						return "<a href='javascript:void(0)' onclick=\"view('"+row.id+"','"+1+"')\">查询</a>"}
					},
					{field:'operate2',title:'账户明细',width:100,align:'center',formatter : function(val,row){ 
						return "<a href='javascript:void(0)' onclick=\"view('"+row.id+"','"+2+"')\">查询</a>"}
					},
					{field:'baseSettleTime',title:'基本户到账时间',width:130,align:'center'},
					{field:'returnStatus',title:'自行还贷状态',width:130,align:'center'},
					{field:'returnOrderNo',title:'还贷订单号',width:130,align:'center'},
					{field:'returnPayTime',title:'还贷支付时间',width:130,align:'center'},
					{field:'returnSettleTime',title:'还贷到账时间',width:130,align:'center'},
				]],
			onLoadSuccess:function(data){
				$.parser.parse();
			}
		});
	}  
	
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
	}
	
	function createDialog(){
		var diag = new Dialog();
		diag.Width = 800;
		diag.Height = 500;
		diag.Title = "";
		return diag
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
	
	function view(id,type){
		var diag = createDialog();
		diag.URL = "${ctx}/lnmid/clmsLoanRecSearch/detail?id="+id+"&type="+type;
		diag.show();
	}
</script>
<body>  
	<form id="queryForm">
	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>
	<div style="margin: 10px 0;"></div>
		<table class="table">
			<tr>
				<td>贷款企业：</td>
				<td><input name="query_loanEnterpriseName" type="text"/></td>
				<td>贷款金额：</td>
				<td><input name="query_loanAmount" type="text"/></td>
			</tr>
			<tr>
				<td>贷款关联号：</td>
				<td><input name="query_loanNo" type="text"/></td>
				<td>自行还贷状态：</td>
				<td>
					<select name="query_returnStatus" id="query_returnStatus">
			          <option value="">全部</option>
			          <option value="2">已还贷</option>
			          <option value="0">未还贷</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>还贷订单号：</td>
				<td><input name="query_returnOrderNo" type="text"/></td>
				<td><input type="button" onclick="query()" class="button" value="查询"></td>
			</tr>
		</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
