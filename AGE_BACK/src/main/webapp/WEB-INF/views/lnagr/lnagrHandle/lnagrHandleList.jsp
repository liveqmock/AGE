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
		if($("#message").val()!="" && $("#message").val()!=null){
			Dialog.alert($("#message").val());
		}
		loadGrid();
	});	

	function loadGrid(){ 
		$('#grid').datagrid({
			title:'协议信息列表',
			url:'${ctx}/lnagr/lnagrHandle/query',
			idField:'id',
			singleSelect:true,
			rownumbers: true,
			frozenColumns:[[ 
	               {field:'id',title:'id',hidden:true},
	               {field:'clmsAgreementId',title:'clmsAgreementId',hidden:true}
			]],
			columns:[[   
	                   {field:'tempFlowNo',title:'协议类型',width:150,align:'center'},   
	                   {field:'flowName',title:'协议名称',width:200,align:'center'}, //显示名称
	                   {field:'bizStatusCN',title:'协议状态',width:120,align:'center'}, //当前环节名称
	                   {field:'operator',title:'操作',width:100,align:'center',
		                	  formatter:function(val,rec){   
		                           return "<a href='javascript:void(0)' onclick=\"lnagrHandleDeal('"+rec.clmsAgreementId+"','"+rec.operateUrl+"')\">处理</a>";   
		                      } 
	                   }
	        ]]
		});
	}  
	
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
	}
	
	
	//查询
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
	
	//处理
	function lnagrHandleDeal(id,operateUrl){
		$.ajax( {
			url : "${ctx}/lnagr/lnagrHandle/lnagrHandleDeal?id="+id+"&operateUrl="+operateUrl+GetUrlConNum(),
			dataType : "json",
			success : function(data) {
				  if(data.isSuccess){ 
					   window.location.href = '${ctx}'+data.url;
				   }else{
					   Dialog.alert(data.message); 
				   }
			}
		});
	}
</script>
<style>
select {
	width:300px;
}
.info{
	width:300px;
}
</style>
<body> 
<input id="message" name="message" type="hidden" value="${message}"/> 
	<table id="grid"></table>
</body>
</html>
