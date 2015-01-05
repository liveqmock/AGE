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
		$('#grid').treegrid({
			title:'机构信息列表',
			url:'${ctx}/sysmng/sysOrg/getTreeGirdList', 
			nowrap: false,
			rownumbers: true,
			animate:true,
			collapsible:false,
			fitColumns:true,
			idField:'id',
			treeField:'orgName',
			singleSelect:true,
			frozenColumns:[[ 
	               {field:'ck',checkbox:true},
	               {field:'id',title:'id',hidden:true} ,
	               {field:'isLeaf',title:'isLeaf',hidden:true} 
			]],
			columns:[[   
	                   {field:'orgName',title:'机构名称',width:180},   
	                   {field:'phone',title:'联系电话',width:180},
	                   {field:'orderNo',title:'顺序号',width:180}
	              	]],
			onBeforeExpand:function(row){ 
		         var url = "${ctx}/sysmng/sysOrg/getTreeGirdList?parentId="+row.id;   
		         $("#grid").treegrid("options").url = url;  
		         return true;      
			},
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
    <p>编号：
    <input name="flowNo" class="easyui-textbox" style="width:50%;height:25px">
    </p>
    <p>名称：
    <input name="name" class="easyui-textbox" style="width:30%;height:25px">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="query()">查询</a>
    </p>
	</form>
	<table id="grid"></table>
</body>
</html>
