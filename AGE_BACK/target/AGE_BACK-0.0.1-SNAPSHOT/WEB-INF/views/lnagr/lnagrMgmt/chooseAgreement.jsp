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
			url:'${ctx}/lnagr/lnagrMgmt/flowList', 
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
					{field:'flowNo',title:'编号',width:120,align:'center'},
					{field:'name',title:'名称',width:120,align:'center'},
					{field:'displayName',title:'显示名称',width:180,align:'center'},
					{field:'remark',title:'备注',width:120,align:'center'},
					//{field:'ownerId',title:'拥有者',width:50,align:'center'},
					{field:'orderNo',title:'顺序号',width:120,align:'center'},
					{field:'state',title:'状态',width:50,align:'center',
						formatter:function(value,row){
							if(value==0)
								return '禁用';
							else if(value == 1)
								return '可用';
							else
								return value;
						}
					}
	                    
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
    <p>编号：
    <input name="flowNo" class="easyui-textbox" style="width:30%;height:25px">
    </p>
    <p>名称：
    <input name="name" class="easyui-textbox" style="width:30%;height:25px">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="query()">查询</a>
    </p>
	</form>
	<table id="grid"></table>
</body>
</html>
