<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>  
    <%@ include file="/common/taglibs.jsp" %> 
 </head>
 <script>
	 
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'角色列表',
			url:'${ctx}/sysmng/sysRole/getList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true, 
			idField:'id',
			frozenColumns:[[ 
	               {field:'ck',checkbox:true},
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[   
	                   {field:'roleName',title:'角色名称',width:180, 
	                   	formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"view('"+rec.id+"')\">"+rec.roleName+"</a>";   
	                      	} 
	                   },   
	                   {field:'roleNo',title:'角色代码',width:180},   
	                   {field:'orderNo',title:'顺序号',width:180} 
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
			},{ 
				id:'btn_eidt',
				text:'分配资源',
				iconCls:'icon-edit',
				handler:function(){
					assignResource(); 
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
		diag.URL = "${ctx}/sysmng/sysRole/create";
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
		diag.URL = "${ctx}/sysmng/sysRole/view?id="+id; 
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
					url : "${ctx}/sysmng/sysRole/removeAll?selIds="+ids,
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
			diag.URL = "${ctx}/sysmng/sysRole/edit?id="+rows[0].id;
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
	
	function assignResource(){
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 1){ 
			var diag = new Dialog();
			diag.Width = 600;
			diag.Height = 400;
			diag.Title = "分配资源";
			diag.URL = "${ctx}/sysmng/sysResource/assignResource?sysRoleId="+rows[0].id;
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
	<table id="grid"></table>
</body>
</html>
