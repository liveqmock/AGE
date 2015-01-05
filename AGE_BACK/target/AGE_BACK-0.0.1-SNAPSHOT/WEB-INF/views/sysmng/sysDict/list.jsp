<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>  
    <link rel="stylesheet" type="text/css" href="${ctx}/scripts/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
	<script type="text/javascript" src="${ctx}/scripts/common/common.js"></script>
 </head>
 <script>
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'系统字典列表',
			url:'${ctx}/sysmng/sysDict/getList', 
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
	                   {field:'dictCode',title:'代码',width:180},
	                   {field:'operator',title:'值操作',width:180,
	                		formatter:function(val,rec){   
		                           return "<a href='javascript:void(0)' onclick=\"view('"+rec.id+"')\">查看</a>";   
		                    } 
	                   }
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
	
	
	function view(id){ 
		var diag = new Dialog();
		diag.Width = 600;
		diag.Height = 600;
		diag.Title = "数据字典值信息";
		diag.URL = "${ctx}/sysmng/sysDictValue/list?sysDictId="+id; 
		diag.show(); 
	}
	
	function create(){ 
		var diag = new Dialog();
		diag.Width = 400;
		diag.Height = 200;
		diag.Title = "数据字典信息";
		diag.URL = "${ctx}/sysmng/sysDict/create";
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
					url : "${ctx}/sysmng/sysDict/removeAll?selIds="+ids+GetUrlConNum(),
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
			var diag = new Dialog();
			diag.Width = 600;
			diag.Height = 600;
			diag.Title = "数据字典信息";
			diag.URL = "${ctx}/sysmng/sysDict/edit?id="+rows[0].id;
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
	<table class="table" style="width: 100%;margin: 0 0 5 0;">
		<tr>
			<td style="width:15%;">名称：</td>
			<td style="width:30%;"><input name="dictName" type="text" style="width:90%;"/></td> 
			<td style="width:15%;">代码：</td>
			<td style="width:30%;"><input name="dictCode" type="text" style="width:90%;"/></td>
			<td style="width:10%;"><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td> 
		</tr>  
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
