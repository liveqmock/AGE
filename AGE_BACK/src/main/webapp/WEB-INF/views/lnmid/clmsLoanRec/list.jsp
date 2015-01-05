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
			title:'待发起列表',
			url:'${ctx}/lnmid/clmsLoanRec/getList', 
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
	                   
					{field:'recCreateTime',title:'创建时间',width:130,align:'center'},
					{field:'applyLoanTime',id:'applyLoanTime',title:'申请放款时间',width:130,align:'center',sortable:true},
					//{field:'loanFinishTime',title:'贷款到期时间',width:130,align:'center'},
					{field:'loanNo',title:'贷款关联号',width:150,align:'center'},
					{field:'entName',title:'贷款企业',width:150,align:'center'},
					{field:'applyNo',title:'申贷编号',width:150,align:'center'},
					{field:'proName',title:'项目名称',width:100,align:'center'},
					{field:'loanMoney',title:'贷款金额',width:100,align:'center'},
					{field:'loanReturnFlag',title:'贷款模式',width:100,align:'center',
						formatter:function(val,rows,index){
							if(val=='0'){
								return '自动还款';
							}else if(val=='1'){
								return '自行还款';
							}
						}
					},
					{field:'operator',title:'操作',width:100,align:'center',formatter:operator}
				]],
			onLoadSuccess:function(data){
				$.parser.parse();
			}
		});
	}  
	function operator(value,row,index){
		if(row.loanReturnFlag=='0'){
			return "<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"startLoan('"+row.id+"',this)\">发起贷款</a>";
		}else if(row.loanReturnFlag=='1'){
			return "<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"confirm('"+row.id+"')\">提交确认</a>";
		}
	}
	
	function confirm(id){
		var diag = createDialog();
		diag.URL = "${ctx}/lnmid/clmsLoanRec/confirm?id="+id;
		diag.doReturn = function(data){
			if(data.isSuccess){
				refreshGrid();
				diag.close();
		    }else{
			    Dialog.alert(data.message);
		    }
		};
		diag.show();
	}
	function startLoan(id,obj){
		Dialog.confirm('您确定发起贷款吗？',function(){
		$(obj).hide();
			$.ajax({
			     url: "${ctx}/lnmid/clmsLoanRec/startLoan",
			     type: "POST",
			     data:{id:id},
			     dataType : "json",
			     success: function(data){
			        var success = data.isSuccess;
			        var message = data.message;
			        if(success){
			        	Dialog.alert(message);
			          	refreshGrid();
			         }else{
			            Dialog.alert(message);
			            refreshGrid();
			           }
			          },
			       error: function(XMLHttpRequest,textStatus,errorThrown){alert(errorThrown);}
			  });
		 });
	}
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
	}
	
	function createDialog(){
		var diag = new Dialog();
		diag.Width = 500;
		diag.Height = 250;
		diag.Title = "";
		return diag
	}
	
	function create(){
		var diag = createDialog();
		diag.URL = "${ctx}/ebms/clmsLoanRec/create";
		diag.doReturn = function(data){
			if(data.isSuccess){
				refreshGrid();
				diag.close();
		    }else{
			    Dialog.alert(data.message);
		    }
		};
		diag.show();
		
	}
	
	function view(id){
		var diag = createDialog();
		diag.URL = "${ctx}/ebms/clmsLoanRec/view?id="+id;
		diag.show();
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
				<td class="td-even">贷款关联号：</td>
				<td class="td-odd"><input class="easyui-textbox" name="loanNo" type="text" /></td>
				<td class="td-even">申贷编号：</td>
				<td class="td-odd"><input class="easyui-textbox" name="applyNo" type="text" /></td>
			</tr>
			<tr>
				<td class="td-even">贷款企业：</td>
				<td class="td-odd"><input class="easyui-textbox" name="entName" type="text"/></td>
				<td class="td-even">申请放款时间：</td>
				<td class="td-odd"><input  type="text" name="applyLoanTime" class="Wdate" onfocus="WdatePicker({lang:'zh',dateFmt:'yyyy-MM-dd'})"  onclick="WdatePicker()" /> &nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" onclick="query()">查询</a></td>
			</tr>
		</table>
	</form> 
	<table id="grid"></table>
	</div>
</body>
</html>
