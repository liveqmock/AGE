<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>  
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>
    <style type="text/css">
	.td-even{text-align:right;}
	.td-odd{text-align:left;}
	</style>
 </head>
 <script>
	 
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:"${bizType eq '0'?'放贷':bizType eq '1'?'还贷':'异常'}列表",
			url:'${ctx}/lnmid/clmsLoanMoney/getList?bizType=${bizType}', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			frozenColumns:[[ 
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[
					{field:'orderNo',title:'订单号',width:130,align:'center'},
					{field:'startTime',title:'发送时间',width:130,align:'center'},
					{field:'endTime',title:'结束时间',width:130,align:'center'},
					{field:'tradeNo',title:'交易流水号',width:130,align:'center'},
					{field:'loanNo',title:'贷款流水号',width:130,align:'center'},
					{field:'loadOutMoney',title:'交易金额',width:100,align:'center'},
					{field:'bankAccName',title:'银行账户名称',width:100,align:'center'},
					{field:'userId',title:'贷款用户',width:100,align:'center'},
					{field:'tradeType',title:'业务类型',width:80,align:'center',formatter:tradeType},
					{field:'flowStatus',title:'交易状态',width:80,align:'center',formatter:flowStatus},
					{field:'remark',title:'备注',width:100,align:'center'},
					{field:'recCreateTime',title:'创建时间',width:130,align:'center'}
	                    
	              	]]
		
		});
	}  
	
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
	}
	function tradeType(val,row){
		if(val == '1')
			return '支付'
		else if(val == '2')
			return '结算';
		else if(val == '3')
			return '代扣';
		else
			return '异常';
	}
	function flowStatus(val, row){
		if(val == '0')
			return '初始';
		else if(val == '1')
			return '处理中'
		else if(val == '2')
			return '处理成功';
		else if(val == '3')
			return '处理失败';
		else
			return '异常';
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
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',title:'查询条件',split:true,border:false" >
	<form id="queryForm">
	<table style="width: 80%;margin: 0 0 5 0;font-size:12px;">
		<tr>
			<td class="td-even">贷款流水号：</td>
			<td class="td-odd"><input class="easyui-textbox" name="loanNo" type="text"  style="width:200px"/></td> 
			<td class="td-even">交易金额：</td>
			<td class="td-odd"><input class="easyui-textbox" name="loadOutMoney" type="text" style="width:200px"/></td>
			 
		</tr>  
		<tr>
			<td class="td-even">订单号：</td>
			<td class="td-odd"><input class="easyui-textbox" name="orderNo" type="text" /> &nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" onclick="query()">查询</a></td> 
			<td class="td-even">业务类型：</td>
			<td class="td-odd">
				<select class="easyui-combobox" name="tradeType" data-options="panelHeight: 'auto'" style="width:100px;">
					<option></option>
					<option value="1">支付</option>
					<option value="2">结算</option>
					<option value="3">代扣</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交易状态:
				<select class="easyui-combobox" name="flowStatus" data-options="panelHeight: 'auto'" style="width:100px;">
					<option></option>
					<option value="0">初始</option>
					<option value="1">处理中</option>
					<option value="2">处理成功</option>
					<option value="3">处理失败</option>
				</select>
			</td>
			
		</tr>  
	</table>
	</form>
	
	<table id="grid"></table>
	</div>
</body>
</html>
