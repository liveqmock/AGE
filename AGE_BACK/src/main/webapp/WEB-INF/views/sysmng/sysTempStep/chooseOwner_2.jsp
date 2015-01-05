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
			title:'人员信息列表',
			url:'${ctx}/sysmng/sysUser/getList2', 
			nowrap: false,
			rownumbers: true,
			pageSize:10,
			pagination:true,
			animate:true,
			collapsible:false,
			fitColumns:true,
			idField:'id',
			singleSelect:true,
			frozenColumns:[[ 
	               {field:'ck',checkbox:true},
	               {field:'id',title:'id',hidden:true} ,
			]],
			columns:[[   
	                {field:'userAccount',title:'用户账号',width:100,align:'center'},
					{field:'userName',title:'用户名称',width:100,align:'center'},
					{field:'accountType',title:'账号类型',width:100,align:'center',
							formatter:function(val,row){
							if(val == 0){
								return '贷款用户';
							}else if(val == 1){
								return '信贷人员';
							}else{
								return val;
							}
						}}
	              	]],
			toolbar:
			[{ 
				id:'btn_add',
				text:'确定',
				iconCls:'icon-ok', 
				handler:function(){
					ok(); 
				}
			}] 
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
    <p>用户账号：
    <input name="userAccount" class="easyui-textbox" style="width:50%;height:25px">
    </p>
    <p>名称：
    <input name="userName" class="easyui-textbox" style="width:30%;height:25px">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="query()">查询</a>
    </p>
	</form>
	<table id="grid"></table>
</body>
</html>
