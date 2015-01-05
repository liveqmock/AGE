<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>  
    <%@ include file="/common/taglibs.jsp" %> 
 </head>
 <script>
	 
	$(function(){  
		initTree();
		loadGrid();
	});	
	
	function initTree(){
		$('#orgTree').tree({
			checkbox: false,
			url: '${ctx}/sysmng/sysOrg/getTreeList',
			onBeforeExpand:function(node,param){
                $(this).tree('options').url = "${ctx}/sysmng/sysOrg/getTreeList?parentId=" + node.id;
                loadGrid();
            },
			onSelect:function(node){
				loadGrid();
			}
		});
	}
	
	function loadGrid(node){
		var orgId = "";
		var node = $('#orgTree').tree('getSelected');
		if(node){
			orgId = node.id;
		}
		$('#grid').datagrid({
			title:'用户信息列表',
			url:'${ctx}/sysmng/sysUser/getList?orgId='+orgId, 
			pageSize:10, 
			pagination:true,
			rownumbers:true, 
			idField:'id',
			frozenColumns:[[ 
	               {field:'ck',checkbox:true},
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[   
	                   {field:'userName',title:'用户名称',width:180, 
	                   	formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"view('"+rec.id+"')\">"+rec.userName+"</a>";   
	                      	} 
	                   },   
	                   {field:'userAccount',title:'用户账号',width:180},
	                   {field:'stateCN',title:'状态',width:180},
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
				text:'分配角色',
				iconCls:'icon-edit',
				handler:function(){
					assignRole(); 
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
		diag.Height = 300;
		diag.Title = "";
		return diag
	}
	
	function create(){ 
		var node = $('#orgTree').tree('getSelected');
		if(node){ 
			var diag = createDialog();
			diag.URL = "${ctx}/sysmng/sysUser/create?orgId="+node.id;
			diag.doReturn = function(data){
				if(data.isSuccess){  
					Dialog.alert(data.message); 
					refreshGrid();
					diag.close();
			    }else{
				    Dialog.alert(data.message); 
			    }
			}; 
			diag.show();  
		}else{
			Dialog.alert('请先选择所属机构!');
		}
	}
	
	function view(id){ 
		var diag = createDialog();
		diag.URL = "${ctx}/sysmng/sysUser/view?id="+id; 
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
					url : "${ctx}/sysmng/sysUser/removeAll?selIds="+ids,
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
			diag.URL = "${ctx}/sysmng/sysUser/edit?id="+rows[0].id;
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
	
	function assignRole(){
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 1){ 
			var diag = new Dialog();
			diag.Width = 600;
			diag.Height = 400;
			diag.Title = "分配角色";
			diag.URL = "${ctx}/sysmng/sysRole/assignRole?sysUserId="+rows[0].id;
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
<body class="easyui-layout">
	<div region="west" split="true" iconCls="icon-tree" title="机构列表" style="width:280px;padding1:1px;">
		<ul id="orgTree" class="tree"></ul>
	</div>
	<div region="center" title="" style="overflow:hidden;">
		<table id="grid"></table>
	</div> 
</body> 
</html>
