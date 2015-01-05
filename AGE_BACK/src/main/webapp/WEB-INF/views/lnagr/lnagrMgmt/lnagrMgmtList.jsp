<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
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
		if($("#message").val()!="" && $("#message").val()!=null){
			Dialog.alert($("#message").val());
		}
		loadGrid();
	});	

	function loadGrid(){ 
		$('#grid').datagrid({
			title:'协议信息列表',
			url:'${ctx}/lnagr/lnagrMgmt/query',
			idField:'id',
			singleSelect:true,
			rownumbers: true,
			frozenColumns:[[ 
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[   
	                   {field:'tempFlowNo',title:'协议类型',width:150,align:'center'},   
	                   {field:'tempFlowName',title:'协议名称',width:200,align:'center'}, //协议模版名称
	                   {field:'signOrgId',title:'签署方',width:100,align:'center',
	                	  formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"signatories('"+rec.id+"')\">明细</a>";   
	                      } 
	                   }, 
	                   {field:'flowProject',title:' 协议相关项目',width:150,align:'center',
	                	  formatter:function(val,rec){   
	                           return "<a href='javascript:void(0)' onclick=\"flowProject('"+rec.id+"')\">明细</a>";   
	                      } 
	                   }, 
	                   {field:'bizStatus',title:'协议进度',width:100,align:'center',
	                	  formatter:function(val,rec){  
	                	   		var bizStatus="";
	                	   		if(rec.bizStatus=='1'){
	                	   			bizStatus="签署中";
	                	   		}else if(rec.bizStatus=='2'){
	                	   			bizStatus="已签署";
	                	   		}
	                           return "<a href='javascript:void(0)' onclick=\"flowProgress('"+rec.id+"')\">"+bizStatus+"</a>";   
	                      } 
	                   }, 
	                   {field:'flowEndTime',title:'签署日期',width:120,align:'center',
	                	  formatter:function(val,rec){ 
	                	   		if(rec.flowEndTime!=null && rec.flowEndTime!=''){
		                	   		//前10位
		                           return rec.flowEndTime.substr(0,10);   
	                	   		}else{
	                	   			return "";
	                	   		}
	                      } 
	                   }, 
	                   {field:'flowFile',title:'协议附件',width:100,align:'center',
	                	  formatter:function(val,rec){
	                		  // 协议处理状态：0=初始；1=签署中；2=签署完成；
	                		  if(rec.bizStatus=='2'){
	                           	return "<a href='javascript:void(0)' onclick=\"downLoad('"+rec.id+"')\">下载</a>"; 
	                		  }
	                      } 
	                   },
	                   {field:'operator',title:'操作',width:260,align:'center',
	                	  formatter:function(val,rec){ 
	                		 // 协议处理状态：0=初始；1=签署中；2=签署完成；
	                		  if(rec.bizStatus=='0'){
		                           return "<a href='javascript:void(0)' onclick=\"setFlow('"+rec.id+"')\">流程设置</a>&nbsp;<a href='javascript:void(0)' onclick=\"startFlow('"+rec.id+"')\">启动流程</a>";   
	                	   	   }else{
	                	   		   //FIXME 流程设置链接什么时候不能使用
	                	   		   return "";
	                	   	   }
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
	
	//查询
	function lnagrMgmtReg(){
		window.location = "${ctx}/lnagr/lnagrMgmt/lnagrMgmtReg";
	}
	
	//创建dialog
	function createDialog(width,height,title){ 
		var diag = new Dialog();
		diag.Width = width;
		diag.Height = height;
		diag.Title = title;
		return diag;
	}
	
	//签署方
	function signatories(id){
		var diag = createDialog(400,200,"签署方信息");
		diag.URL = "${ctx}/lnagr/lnagrMgmt/signatories?id="+id;
		diag.show(); 
	}
	//协议相关项目
	function flowProject(id){
		var diag =createDialog(400,200,"协议相关项目信息");
		diag.URL  = "${ctx}/lnagr/lnagrMgmt/flowProject?id="+id;
		diag.show(); 
	}
	//协议进度
	function flowProgress(id){
		var diag =createDialog(820,640,"协议进度信息");
		diag.URL = "${ctx}/lnagr/lnagrMgmt/flowProgress?id="+id;
		diag.show(); 
	}
	//协议附件
	function downLoad(id){
		window.open ("${ctx}/lnagr/lnagrMgmt/downLoad?id="+id);
				 
	}
	//流程设置
	function setFlow(id){
		var url = "${ctx}/lnagr/lnagrMgmt/setFlow?id="+id+GetUrlConNum();
		var diag = createDialog(1150,700,"流程设置");
		diag.URL = url;
		diag.show();
	}
	//启动流程
	function startFlow(id){
		 Dialog.confirm('确定要启动流程吗？',function(){
			$.ajax( {
				url : "${ctx}/lnagr/lnagrMgmt/startFlow?id="+id+GetUrlConNum(),
				dataType : "json",
				success : function(data) {
					  if(data.isSuccess){ 
						   Dialog.alert(data.message); 
						   refreshGrid();
					   }else{
						   Dialog.alert(data.message); 
						   refreshGrid();
					   }
				}
			});
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
	<form id="queryForm">
	<input id="message" name="message" type="hidden" value="${message}"/>
	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>  
<%--	中间变量  保存 企业名称条件值--%>
	<table class="table" style="margin: 0 0 5 0;">
		<thead>
		<tr>
			<td style="width:160px;"></td>
			<td style="width:280px;"></td>
			<td style="width:160px;"></td>
			<td style="width:280px;"></td>
		</tr>
		</thead>
		<tr>
			<td colspan="4">
				<input name="createBtn" class="button" type="button" onclick="lnagrMgmtReg()" value="新建" /> 
				<input name="queryBtn" class="button" type="button" onclick="query()" value="检索" /> 
			</td>
		</tr>
		<tr>
			<td>协议类型：</td>
			<td>
				<select name="tempFlowNo" id="tempFlowNo">
					<option value="">全部</option>
					<option value="F_XY_KJ">框架协议</option>
					<option value="F_XY_DK">贷款协议</option>
					<option value="F_XY_ZL">资金划拨协议</option>
				</select>
			</td> 
			<td>协议名称：</td>
			<td><input class="info" name="tempFlowName" id="tempFlowName" type="text"/></td> 
		</tr>
		<tr>
			<td>签署状态：</td>
			<td>
				<select name="bizStatus" id="bizStatus">
					<option value="">全部</option>
					<option value="2">已签署</option>
					<option value="1">签署中</option>
				</select>
			</td> 
			<td>签署日期：</td>
			<td><input class="info" type="text" id="flowEndTime"  name="flowEndTime" class="Wdate" onfocus="WdatePicker({lang:'zh',dateFmt:'yyyy-MM-dd'})"  onclick="WdatePicker()" /></td>
		</tr>  
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
