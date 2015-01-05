<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>  
    <%@ include file="/common/taglibs.jsp" %> 
     <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>
     <script type="text/javascript" src="${ctx}/scripts/common/common.js"></script>
 </head>
 <script>
	$(function(){  
		loadGrid();
	});	 
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'模板环节列表',
			url:'${ctx}/sysmng/sysTempStep/getTempStepList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			frozenColumns:[[ 
	               {field:'ck',checkbox:true},
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[   {field:'orderNo',title:'顺序号',width:120},
	                   {field:'stepName',title:'名称',width:120},   
	                   {field:'displayName',title:'显示名称',width:120},
	                   {field:'remark',title:'备注',width:180},
	                   {field:'operateUrl',title:'操作界面',width:180},
	                   {field:'ownerType',title:'拥有者类型',width:100,
	                	   formatter:function(val,row){
	                		   //拥有者类型 0=角色；1=部门；2:=人员
								if(val == '0'){
									return '角色';
								}else if(val == '1'){
									return '部门';
								}else if(val == '2'){
									return '人员';
								}else{
									return val;
								}
							}
	                   },

	                   {field:'ownerName',title:'拥有者',width:180},
	                   {field:'stepNo',title:'环节编号',width:120},
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
			toolbar:
			[{ 
				id:'btn_add',
				text:'新增',
				iconCls:'icon-add', 
				handler:function(){
					create(); 
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
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
					removeAll(); 
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
		diag.Width = 600;
		diag.Height = 300;
		diag.Title = "";
		return diag
	}
	
	function create(){ 
		var diag = createDialog();
		diag.Title = "新增环节";
		diag.URL = "${ctx}/sysmng/sysTempStep/create";
		diag.doReturn = function(data){
			if(data.isSuccess){  
				refreshGrid();
				diag.close();
				Dialog.alert(data.message);
		    }else{
			    Dialog.alert(data.message); 
		    }
		};  
		diag.show(); 
	}
	
	function view(id){ 
		var diag = createDialog();
		diag.Title = "公告信息";
		diag.URL = "${ctx}/sysmng/sysBulletin/view?id="+id; 
		diag.show(); 
	}
	

	function edit(){  
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 1){ 
			var diag = createDialog();
			diag.URL = "${ctx}/sysmng/sysTempStep/edit?id="+rows[0].id;
			diag.doReturn = function(data){
				if(data){  
					refreshGrid();
					diag.close();
				}
			}; 
			diag.show(); 
		}else{
			Dialog.alert('请选择一条要修改的数据项!');
		}  
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
					url : "${ctx}/sysmng/sysTempStep/removeAll?selIds="+ids+GetUrlConNum(),
					dataType : "json",
					success : function(data) {
						  if(data.isSuccess){ 
							   Dialog.alert(data.message); 
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
	 
	function assignResource(){
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 1){ 
			var diag = new Dialog();
			diag.Width = 600;
			diag.Height = 400;
			diag.Title = "分配环节";
			diag.URL = "${ctx}/sysmng/sysTempFlow/assignStep?sysTempFlowID="+rows[0].id;
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
	<form id="queryForm"  method="post">
	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>  
	<table class="table" style="width: 100%;margin: 0 0 5 0;">
		<tr>
			<td style="width:10%;">环节名称：</td>
			<td style="width:20%;"><input name="stepName" type="text" style="width:90%;" /></td> 
			<td style="width:70%;"><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td> 
		</tr>  
	</table>
	</form>  
	<table id="grid"></table>
</body>
</html>
