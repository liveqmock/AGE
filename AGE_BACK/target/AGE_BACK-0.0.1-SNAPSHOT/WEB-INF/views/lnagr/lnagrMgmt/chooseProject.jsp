<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>  
 </head>
 <script type="text/javascript">
	 
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:"列表",
			url:'${ctx}/lnagr/lnagrMgmt/projectList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
	            {field:'id',title:'id',hidden:true}   
			]],
			columns:[[
					{field:'entName',title:'贷款企业',width:100,align:'center'},
					{field:'proName',title:'项目',width:100,align:'center'},
					{field:'bidName',title:'标段',width:100,align:'center'},
					{field:'bidFinishDate',title:'截止时间',width:100,align:'center'},
					{field:'loanNo',title:'关联号',width:180,align:'center'},
					{field:'applyNo',title:'申贷编号',width:180,align:'center'}
	                    
	              	]],
	       toolbar:[{ 
       				id:'btn_ok',
       				text:'确定',
       				iconCls:'icon-ok', 
       				handler:function(){
       					ok();
       				}
       			}
       		]
		
		});
	}
	
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
	}
	
	function ok(){
		var row = $('#grid').datagrid('getSelected');
		if(row){
			ownerDialog.doReturn(row);
		}else{
			$.messager.alert("提示","选择一行");
		}
	}
	
	function query(){
		//console.debug('q');
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
    <p>贷款企业名称：
    <input name="entName" class="easyui-textbox" style="width:30%;height:25px">
    </p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目：
    <input name="proName" class="easyui-textbox" style="width:30%;height:25px">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="query()">查询</a>
    </p>
	</form>
	<table id="grid"></table>
</body>
</html>
