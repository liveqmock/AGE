<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>  
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>
    <script type="text/javascript" src="${ctx}/scripts/common/common.js"></script>
 </head>
 <script>
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').treegrid({
			title:'资源信息列表',
			url:'${ctx}/sysmng/sysResource/getTreeGirdList', 
			nowrap: false,
			rownumbers: true,
			animate:true,
			collapsible:false, 
			idField:'id',
			treeField:'resName',
			singleSelect:true,
			frozenColumns:[[ 
	               {field:'ck',checkbox:true},
	               {field:'id',title:'id',hidden:true} ,
	               {field:'isLeaf',title:'isLeaf',hidden:true} 
			]],
			columns:[[   
	                   {field:'resName',title:'资源名称',width:180},   
	                   {field:'resNo',title:'资源代码',width:180},
	                   {field:'typeCN',title:'资源类型',width:180},
	                   {field:'orderNo',title:'顺序号',width:180},
	                   {field:'operate',title:'操作',width:250, 
		                	formatter:function(val,rec){   
		                        return "<a href='#' onclick=\"create('"+rec.id+"')\">添加子资源</a>&nbsp;&nbsp;<a href='#' onclick=\"edit('"+rec.id+"')\">修改</a>&nbsp;&nbsp;<a href='#' onclick=\"remove('"+rec.id+"')\">删除</a>";
		                   	} 
		               }
	              	]],
			onBeforeExpand:function(row){ 
		         var url = "${ctx}/sysmng/sysResource/getTreeGirdList?parentId="+row.id;   
		         $("#grid").treegrid("options").url = url;  
		         return true;      
			},
			toolbar:
			[{ 
				id:'btn_add',
				text:'新增',
				iconCls:'icon-add', 
				handler:function(){
					create(null); 
				}
			}] 
		});
	} 
	
	function refreshParent(selectId){  
		if(selectId){
			var parentNode = $('#grid').treegrid('getParent', selectId);
			if(parentNode){
				$('#grid').treegrid('reload', parentNode.id); 
			}else{  
				roladTreegrid()
			} 
			
		}else{
			roladTreegrid()
		} 
	}
	
	function refreshGrid(selectId){  
		if(selectId){
			var selectNode = $('#grid').treegrid('find', selectId); 
			if(selectNode.isLeaf == 'true'){
				refreshParent(selectId);
			}else{
				$('#grid').treegrid('reload', selectId); 
			} 
			
		}else{ 
			roladTreegrid();
		} 
	}
	
	function roladTreegrid(){
		$("#grid").treegrid("options").url = '${ctx}/sysmng/sysResource/getTreeGirdList';  
		$('#grid').treegrid('reload');
	}
	 
	
	function createDialog(){ 
		var diag = new Dialog();
		diag.Width = 500;
		diag.Height = 260;
		diag.Title = "";
		return diag
	}
	
	function create(selectId){  
		var diag = createDialog();
		diag.URL = "${ctx}/sysmng/sysResource/create?parentId="+(selectId==null?"":selectId);
		diag.doReturn = function(data){
			if(data.isSuccess){  
				refreshGrid(selectId);
				diag.close();
		    }else{
			    Dialog.alert(data.message); 
		    }
		}; 
		diag.show(); 
		
	}
	
	function view(id){ 
		var diag = createDialog();
		diag.URL = "${ctx}/sysmng/sysResource/view?id="+id; 
		diag.show(); 
	}
	
	function remove(selectId){ 
		Dialog.confirm('您确认删除所选的数据项吗？',function(){
				$.ajax( {
					url : "${ctx}/sysmng/sysResource/remove?id="+selectId+GetUrlConNum(),
					dataType : "json",
					success : function(data) {
						  if(data.isSuccess){ 
							   refreshParent(selectId);
						   }else{
							   Dialog.alert(data.message); 
						   }
					}
				});
			}); 
	}
	 
	function edit(selectId){  
		var diag = createDialog();
			diag.URL = "${ctx}/sysmng/sysResource/edit?id="+selectId;
			diag.doReturn = function(data){
				if(data.isSuccess){  
					refreshParent(selectId);
					diag.close();
			    }else{
				    Dialog.alert(data.message); 
			    }
			}; 
			diag.show(); 
	} 
</script>
<body>   
	<table id="grid"></table>
</body>
</html>
