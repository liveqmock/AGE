<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>  
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
    <link rel="stylesheet" type="text/css" href="${ctx}/scripts/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/scripts/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="${ctx}/scripts/jquery/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery-easyui/locale/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDialog.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDrag.js"></script>
 </head>
 <script>
	 
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'系统字典值列表',
			url:'${ctx}/sysmng/sysDictValue/getList?sysDictId='+$("#sysDictId").val(), 
			pageSize:10, 
			pagination:true,
			rownumbers:true, 
			idField:'id',
			frozenColumns:[[ 
	               {field:'ck',checkbox:true},
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[   
	                   {field:'dictName',title:'名称',width:180},   
	                   {field:'dictValue',title:'值',width:180} 
	              		]],
			toolbar:
			[{ 
				id:'btn_add',
				text:'新增',
				iconCls:'icon-add', 
				handler:function(){
					create(); 
				}
			},{ 
				id:'btn_cut',
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
					removeAll(); 
				}
			},{ 
				id:'btn_eidt',
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					edit(); 
				}
			}] 
		});
	}  
	
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
	}
	
	function createDialog(){ 
		var diag = new Dialog();
		diag.Width = 500;
		diag.Height = 200;
		diag.Title = "";
		return diag
	}
	
	function create(){ 
		var diag = createDialog();
		diag.URL = "${ctx}/sysmng/sysDictValue/create?sysDictId="+$("#sysDictId").val();
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
	
	function removeAll(){ 
		var ids = [];
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].id);
			}
			Dialog.confirm('您确认删除所选的数据项吗？',function(){
				$.ajax( {
					url : "${ctx}/sysmng/sysDictValue/removeAll?selIds="+ids,
					dataType : "json",
					success : function(data) {
						  if(data.isSuccess){ 
							   refreshGrid();
						   }else{
							   Dialog.alert(data.message); 
						   }
					}
				});
			}); 
		}else{
			Dialog.alert('请选择要删除的数据项!');
		} 
	}
	 
	function edit(){  
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 1){ 
			var diag = createDialog();
			diag.URL = "${ctx}/sysmng/sysDictValue/edit?id="+rows[0].id+"&sysDictId="+$("#sysDictId").val();
			diag.doReturn = function(data){
				if(data.isSuccess){  
					refreshGrid();
					diag.close();
			    }else{
				    Dialog.alert(data.message); 
			    }
			}; 
			diag.show(); 
		}else{
			Dialog.alert('请选择一条要修改的数据项!');
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
	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>  
	<input id="sysDictId" name="sysDictId" type="hidden" value="${sysDictId}" />
	<table class="table" style="width: 100%;margin: 0 0 5 0;">
		<tr>
			<td style="width:15%;">名称：</td>
			<td style="width:30%;"><input id="dictName" name="dictName" type="text" style="width:90%;"/></td> 
			<td style="width:15%;">值：</td>
			<td style="width:30%;"><input id="dictValue" name="dictValue" type="text" style="width:90%;"/></td>
			<td style="width:10%;"><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td> 
		</tr>  
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
