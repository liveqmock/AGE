<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			title:'备案信息列表',
			url:'${ctx}/lnbef/lnbefUserAudit/getTreeGirdList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			
			columns:[[   
	                   {field:'entName',title:'企业名称',width:180,align:'center'},   
	                   {field:'entLegalName',title:'法人代表',width:180,align:'center'},
	                   {field:'businessLicense',title:'营业执照',width:100,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"openFileDialog('"+rec.id+"','1')\">查看</a>";  
	                      	} 
	                   }, 
	                   {field:'taxRegCertificate',title:'税务登记证',width:100,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"openFileDialog('"+rec.id+"','2')\">查看</a>";   
	                      	} 
	                   }, 
	                   {field:'entOrgNoCertificate',title:'组织机构代码',width:100,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"openFileDialog('"+rec.id+"','3')\">查看</a>";   
							} 
	                   }, 
	                   {field:'bankAccountLicence',title:'开户许可证',width:100,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"openFileDialog('"+rec.id+"','4')\">查看</a>";   
	                      	} 
	                   }, 
	                   {field:'applyTime',title:'申请日期',width:120,align:'center'},
	                   {field:'operator',title:'操作',width:80,align:'center',
							formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"view('"+rec.id+"')\">审核</a>";   
	                      	} 
	                   }
	        ]] 
		});
	}  
	
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
	}
	
	//创建dialog
	function createDialog(){ 
		var diag = new Dialog();
		diag.Width = 500;
		diag.Height = 200;
		diag.Title = "";
		return diag
	}
	

	//查看用户备案附件文件
	function openFileDialog(id,fileType){ 
		var diag = new Dialog();
		diag.Title = "";
		diag.Width = 800;
		diag.Height = 600;
		diag.URL = "${ctx}/lnbef/lnbefUserAudit/openFileDialog?id="+id+"&fileType="+fileType; 
		diag.show(); 
	}
	
	//显示审核页面
	function view(id){ 
		var pageSize=$('#grid').datagrid('options').pageSize;
		$("#queryForm").attr("action","${ctx}/lnbef/lnbefUserAudit/view?id="+id);
		$("#queryForm").submit();
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
			<td style="width:15%;">企业名称：</td>
			<td style="width:30%;"><input name="query_entName" type="text" style="width:90%;" /></td> 
			<td style="width:15%;">申请日期：</td>
			<td style="width:30%;"><input  type="text" name="query_applyTime" class="Wdate" onfocus="WdatePicker({lang:'zh',dateFmt:'yyyy-MM-dd'})"  style="width:90%;" onclick="WdatePicker()" /></td>
			<td style="width:10%;"><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td> 
		</tr>  
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
