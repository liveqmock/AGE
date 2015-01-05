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
	
	function GetRandomNum(Min,Max){   
		var Range = Max - Min;   
		var Rand = Math.random();   
		return(Min + Math.round(Rand * Range));   
	} 
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'贷款信息列表',
			url:'${ctx}/lncenter/loanReturnSelf/getLoanList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			frozenColumns:[[ 
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[   
	                   {field:'loanType',title:'贷款类型',width:80,align:'center',
	                	   formatter:function(val,rec){   
	                          if(val=='1'){
	                        	  return '投标保证金'
	                          } 
	                      	}
	                   },   
	                   {field:'proName',title:'贷款项目',width:100,align:'center'}, 
	                   {field:'bidName',title:'贷款标段',width:100,align:'center'}, 
	                   {field:'loanNo',title:'贷款编号',width:100,align:'center'}, 
	                   {field:'orderNo',title:'还贷订单号',width:120,align:'center'}, 
	                   {field:'loanMoney',title:'贷款金额',width:120,align:'center'}, 
	                   {field:'loanBank',title:'贷款银行',width:120,align:'center'}, 
	                   {field:'loadReturnTime',title:'保证金返还日期',width:120,align:'center'}, 
	                   {field:'loanReturnStatus',title:'还贷状态',width:120,align:'center',
	                	   formatter:function(val,rec){   
		                          if(val=='2'){
		                        	  return '已还贷'
		                          }else{
		                        	  return '未还贷'
		                          }
		                      	}
	                   }, 
	                   {field:'operator',title:'操作',width:180,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"loanReturn('"+rec.id+"'\)\">还贷</a>";   
	                      	} 
	                   } 
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
	function loanReturn(id){
		var dialog = new Dialog();
		dialog.Width = 900;
		dialog.Height = 650;
		dialog.URL = "${ctx}/lncenter/loanReturnSelf/createTradeRecord?id=" + id,
		dialog.CancelEvent=function(){refreshGrid();dialog.close();};
		dialog.doReturn = function(data){
			if(data){  
				refreshGrid();
				dialog.close();
				Dialog.alert("中金支付存在延迟，还贷完成后请耐心等待");
		    }
		}; 
		dialog.show();
	}
</script>
<body>  
	<form id="queryForm">
	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>  
	<table class="table" style="width: 100%;margin: 0 0 5 0;">
		<tr>
			<td style="width:15%;">贷款项目：</td>
			<td style="width:30%;"><input name="proName" type="text" style="width:90%;" /></td> 
			<td style="width:15%;">还贷状态：</td>
			<td style="width:30%;"><select name="loanReturnStatus">
					<option value="">全部</option>
					<option value="1">未还贷</option>
					<option value="2">已还贷</option>
				</select></td>
		</tr>  
		<tr>
			<td style="width:15%;">贷款金额：</td>
			<td style="width:30%;">
				<input  type="text" name="loanMoney"   style="width:90%;"  />
			</td> 
			<td style="width:10%;" colspan=2 algin=center><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td>
		</tr>  
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
