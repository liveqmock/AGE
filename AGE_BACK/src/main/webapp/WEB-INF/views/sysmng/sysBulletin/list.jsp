<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>  
    <%@ include file="/common/taglibs.jsp" %> 
     <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>
 </head>
 <script>
	 
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'公告列表',
			url:'${ctx}/sysmng/sysBulletin/getTreeList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true, 
			idField:'id',
			frozenColumns:[[ 
	               {field:'ck',checkbox:true},
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[   
	                   {field:'title',title:'公告标题',width:400, 
	                   	formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"view('"+rec.id+"')\">"+rec.title+"</a>";   
	                      	} 
	                   },   
	                   {field:'status',title:'公告状态',width:80},
	                   {field:'recCreateTime',title:'记录创建时间',width:120},
	                   {field:'orderNo',title:'顺序号',width:80},
	                   //{field:'publisher',title:'公告发布人',width:180},
	                   {field:'publishTime',title:'公告发布时间',width:180}
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
				text:'发布',
				iconCls:'icon-ok',
				handler:function(){
					//1=发布
					releaseOrCancel(1); 
				}
			},{ 
				id:'btn_no',
				text:'撤销',
				iconCls:'icon-no',
				handler:function(){
					//2=撤销
					releaseOrCancel(2); 
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
		diag.Width = 800;
		diag.Height = 600;
		diag.Title = "";
		return diag
	}
	
	function create(){ 
		var diag = createDialog();
		diag.Title = "新增公告";
		diag.URL = "${ctx}/sysmng/sysBulletin/create";
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
	
	function releaseOrCancel(status){
		var ids = [];
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length>0){
			var title='您确认撤销所选的数据项吗？';
			if(status==1){
				title='您确认发布所选的数据项吗？';
			}
			
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].id);
			}
			Dialog.confirm(title,function(){
				$.ajax( {
					url : "${ctx}/sysmng/sysBulletin/releaseOrCancel?selIds="+ids+"&status="+status,
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
			var msg='请选择要撤销的数据项!';
			if(status==1){
				msg='请选择要发布的数据项!';
			}
			Dialog.alert(msg);
		} 
	}
	 
	function edit(){  
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 1){ 
			var diag = createDialog();
			diag.URL = "${ctx}/sysmng/sysBulletin/edit?id="+rows[0].id;
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
			<td style="width:10%;">公告标题：</td>
			<td style="width:20%;"><input name="title" type="text" style="width:90%;" /></td> 
			<td style="width:10%;">公告状态：</td>
			<td style="width:20%;">
				<select name="status" style="width:90%;">
					<option value="">全部状态</option>
					<option value="0">初始</option>
					<option value="1">发布</option>
					<option value="2">撤销</option>
				</select>
			</td> 
			<td style="width:70%;"><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td> 
		</tr>  
	</table>
	</form>  
	<table id="grid"></table>
</body>
</html>
