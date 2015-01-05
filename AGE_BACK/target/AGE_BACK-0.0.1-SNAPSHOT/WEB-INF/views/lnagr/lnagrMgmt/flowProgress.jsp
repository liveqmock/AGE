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
			pageSize:10, 
			url:'${ctx}/lnagr/lnagrMgmt/queryFlowProgress?id='+$("#id").val(),
			singleSelect:true,
			columns:[[   
                   {field:'recOperator',title:'操作员',width:120,align:'center'},   
                   {field:'endTime',title:'操作时间',width:200,align:'center'}, 
                   {field:'curStepName',title:'环节名称',width:120,align:'center'}, //FIXME 名称 还是 显示名称
                   {field:'curStepStatus',title:'进度',width:120,align:'center'},
                   {field:'remark',title:'备注',width:200,align:'center'}
	        ]]
		});
	}
	
	//关闭对话框
	function cancel(){
		ownerDialog.close();
	}
</script>
<body>  
<input type="hidden" id="id" name="name" value="${id}">
	<table id="grid"></table>
	<div  style="text-align:center;"><input class="button" type="button" onclick="cancel()" value="关闭"/></div>
</body>
</html>
