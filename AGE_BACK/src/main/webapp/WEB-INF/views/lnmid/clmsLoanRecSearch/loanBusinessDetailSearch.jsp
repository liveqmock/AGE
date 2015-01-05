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
			title:'贷款业务明细列表',
			url:'${ctx}/lnmid/clmsLoanRecSearch/getList', 
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
					{field:'loadMoney',title:'贷款保证金',width:150,align:'center'},
					{field:'xmmx',title:'项目明细',width:150,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"openFileDialog('"+rec.id+"','1',700,200,'项目明细')\">查询</a>";  
	                      	} 
					},
					{field:'zhmx',title:'账户明细',width:150,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"openFileDialog('"+rec.id+"','2',800,200,'账户明细')\">查询</a>";  
	                      	} 
					},
					{field:'zbdwmx',title:'招标(代理)单位明细',width:150,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"openFileDialog('"+rec.id+"','3',800,220,'招标(代理)单位明细')\">查询</a>";  
	                      	} 
					},
					{field:'loanStatus',title:'贷款状态',width:100,align:'center'},
					{field:'jdmx',title:'节点明细',width:100,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"openFileDialog('"+rec.id+"','4',800,300,'节点明细')\">查询</a>";  
	                      	} 
					}
				]]
		});
	}  
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
	}
	
	function createDialog(width,height,title){
		var diag = new Dialog();
		diag.Width = width;
		diag.Height = height;
		diag.Title = title;
		return diag
	}
	
	function openFileDialog(id,type,width,height,title){
		var diag = createDialog(width,height,title);
		diag.URL = "${ctx}/lnmid/clmsLoanRecSearch/detail?id="+id+"&type="+type;
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
<body>  
	<form id="queryForm">
	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>
	<div style="margin: 10px 0;"></div>
		<table class="table" style="width: 100%;margin: 0 0 5 0;">
			<tr>
				<td style="width:15%;">贷款关联号：</td>
				<td style="width:30%;"><input name="loanNo" class="easyui-textbox"  style="height: 25px"></td> 
				<td style="width:15%;">贷款状态：</td>
				<td style="width:30%;">
					<select name="loanStatus">
						<option value="">全部</option>
						<option value="0">初始</option>
						<option value="1">放款中</option>
						<option value="2">还款中</option>
						<option value="3">完成</option>
					</select>
				</td>
			</tr>  
			<tr>
				<td style="width:15%;">贷款企业：</td>
				<td style="width:30%;">
					<input name="entName" class="easyui-textbox" style="width: 30%; height: 25px">
				</td> 
				<td>贷款保证金</td>
				<td style="width:10%;" ><input  type="text" name="loadMoney"   style="width:90%;"  /></td>
			</tr> 
			<tr align=center>
				<td colspan=4 ><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td>
			</tr>  
		</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
