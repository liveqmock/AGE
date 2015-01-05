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
		$('#grid').datagrid({
			title:'银行账户信息列表',
			url:'${ctx}/lnbef/bankAccountAudit/getTreeGirdList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			frozenColumns:[[ 
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[   
	                   {field:'entName',title:'企业名称',width:80},   
	                   {field:'baseAccFlag',title:'账户信息',width:100,
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"view('"+rec.id+"','"+rec.baseAccFlag+"')\">"+rec.baseAccFlagCN+"</a>";   
	                      	} 
	                   }, 
	                   {field:'recActionCN',title:'经办动作',width:100}, 
	                   {field:'recOperator',title:'经办人',width:100}, 
	                   {field:'recOperateTime',title:'经办日期',width:120}, 
	                   {field:'operator',title:'操作',width:180,
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"passOrNo('"+rec.id+"'\,2)\">通过</a>&nbsp;<a href='javascript:void(0)' onclick=\"passOrNo('"+rec.id+"'\,3)\">不通过</a>";   
	                      	} 
	                   } 
	        ]]
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
	
	
	function view(id,baseAccFlag){ 
		var diag = createDialog();
		diag.URL = "${ctx}/lnbef/bankAccountAudit/view?id="+id+"&baseAccFlag="+baseAccFlag; 
		diag.show(); 
	}
	
	
	
	function passOrNo(id,passFlag){ 
		var confirmMsg='';
		if(passFlag=='2'){
			confirmMsg='您确认通过吗？';
		}else if(passFlag=='3'){
			confirmMsg='您确认不通过吗？';
		}
		 Dialog.confirm(confirmMsg,function(){
			$.ajax( {
				url : "${ctx}/lnbef/bankAccountAudit/passOrNo?id="+id+"&passFlag="+passFlag+GetUrlConNum(),
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
			<td style="width:15%;">企业名称：</td>
			<td style="width:10%;"><input name="query_entName" id="query_entName" type="text" style="width:90%;"/></td> 
			<td style="width:10%;"><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td> 
		</tr>  
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
