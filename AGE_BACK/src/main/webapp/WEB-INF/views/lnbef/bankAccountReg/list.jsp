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
	
	//对中文进行编码
	 function encode(encodeStr){
		 return encodeURIComponent(encodeURIComponent(encodeStr));
	 }
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'银行账户信息列表',
			url:'${ctx}/lnbef/bankAccountReg/getList',
			singleSelect:true,
			idField:'id',
			frozenColumns:[[ 
	               {field:'ck',checkbox:true},
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[   
	                   {field:'baseAccFlag',title:'账户类型',width:80,align:'center'},   
	                   {field:'bank',title:'开户行',width:100,align:'center'}, 
	                   {field:'accName',title:'账户名称',width:100,align:'center'}, 
	                   {field:'accNo',title:'账户号码',width:220,align:'center'}, 
	                   {field:'branchname',title:'分支行名称',width:200,align:'center'}, 
	                   {field:'bankprovince',title:'分支行省份',width:100,align:'center'}, 
	                   {field:'city',title:'分支行城市',width:100,align:'center'},
	                   {field:'accFlag',title:'账户状态',width:60,align:'center'},
	                   {field:'auditStatus',title:'审批状态',width:80,align:'center'} 
	              		]],
			toolbar:
			[{ 
				id:'btn_addBaseBankAcc',
				text:'添加基本户',
				iconCls:'icon-add', 
				handler:function(){
					addBankAcc(1); 
				}
			},{ 
				id:'btn_addLoanBankAcc',
				text:'添加贷款户',
				iconCls:'icon-add', 
				handler:function(){
					addBankAcc(0); 
				}
			},{ 
				id:'btn_ok',
				text:'启用',
				iconCls:'icon-ok',
				handler:function(){
					ok(1); 
				}
			},{ 
				id:'btn_no',
				text:'停用',
				iconCls:'icon-no',
				handler:function(){
					ok(2); 
				}
			},{ 
				id:'btn_no',
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					editBankAccDialog(3); 
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
	
	function addBankAcc(baseAccFlag){ 
		//基本户check
		var diag = new Dialog();
		diag.Width = 800;
		diag.Height = 300;
		if(baseAccFlag==0){
			diag.Title = "添加贷款户";
		}else if(baseAccFlag==1){
			diag.Title = "添加基本户";
		}
		
		if(document.getElementById("entName").value.length==0){
			Dialog.alert('请输入企业名称!');
			refreshGrid();
			return false;
		}else if(document.getElementById("entName").value.length>0){
			$.ajax( {
				url : "${ctx}/lnbef/bankAccountReg/checkEntprise?query_entName="+encode($("#query_entName").val())+GetUrlConNum(),
				dataType : "json",
				success : function(data) {
				  if(data.isSuccess){ 
						if(baseAccFlag==1){
							$.ajax( {
							url : "${ctx}/lnbef/bankAccountReg/isAdd?query_entName="+encode($("#entName").val())+GetUrlConNum(),
							dataType : "json",
							success : function(data) {
							  if(data.isSuccess){ 
								  	diag.URL = "${ctx}/lnbef/bankAccountReg/addBankAccDialog?baseAccFlag="+baseAccFlag+"&query_entName="+encode($("#entName").val())+GetUrlConNum(),
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
								   return true;
							   }else{
								   Dialog.alert(data.message); 
								   return false;
							   }
							}
							});
						}else if(baseAccFlag==0){
							diag.URL = "${ctx}/lnbef/bankAccountReg/addBankAccDialog?baseAccFlag="+baseAccFlag+"&query_entName="+encode($("#entName").val())+GetUrlConNum(),
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
					   return true;
				   }else{
					   Dialog.alert(data.message); 
					   return false;
				   }
				}
			});
		}
		
	}

	function ok(recAction){ 
		var ids = [];
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length>0){
			//启用或停用前check
			//审核中的账户不能启用或停用
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].id);
		    }
		 	$.ajax( {
				url : "${ctx}/lnbef/bankAccountReg/canEnableAndDisable?recAction="+recAction+"&selIds="+ids,
				dataType : "json",
				type : "post", 
				data:$("#form").serialize(),
				success : function(data) {
					  if(data.isSuccess){ 
						  //可以操作
						  if(recAction==2){
							  Dialog.confirm('您确认停用吗？',function(){
								$.ajax( {
									url : "${ctx}/lnbef/bankAccountReg/enableAndDisable?recAction="+recAction+"&selIds="+ids+GetUrlConNum(),
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
						  }else if(recAction==1){
							  Dialog.confirm('您确认启用吗？',function(){
								$.ajax( {
									url : "${ctx}/lnbef/bankAccountReg/enableAndDisable?recAction="+recAction+"&selIds="+ids+GetUrlConNum(),
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
					  }else{
						  //不可以操作
						  Dialog.alert(data.message); 
					  }
				}
			});
		}else{
			if(recAction==2){
				Dialog.alert('请选择要停用的账户!');
			}else if(recAction==1){
				Dialog.alert('请选择要启用的账户!');
			}
		} 
	}
	
	//提交复核
	function audit(recAction){
		var ids = [];
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length>0){
			//提交复核前check
			//审批初始状态才能提交复核
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].id);
		    }
		 	$.ajax( {
				url : "${ctx}/lnbef/bankAccountReg/checkAudit?recAction="+recAction+"&selIds="+ids,
				dataType : "json",
				type : "post", 
				data:$("#form").serialize(),
				success : function(data) {
					  if(data.isSuccess){ 
						  //可以操作
						 Dialog.confirm('您确认提交复核吗？',function(){
							$.ajax( {
								url : "${ctx}/lnbef/bankAccountReg/audit?recAction="+recAction+"&selIds="+ids+GetUrlConNum(),
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
					  }else{
						  //不可以操作
						  Dialog.alert(data.message); 
					  }
				}
			});
		}else{
			Dialog.alert('请选择要提交复核的账户!');
		} 
	}
	
	//打开编辑dialog
	function editBankAccDialog(recAction){
		var ids = [];
		var id='';
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length>0){
			if(rows.length>1){
				Dialog.alert('请选择一条要修改的账户!');
			}else{
				//check使用
				for(var i=0;i<rows.length;i++){
					ids.push(rows[i].id);
		    	}
				//修改使用
				id=rows[0].id;
				//修改前check
				$.ajax( {
				url : "${ctx}/lnbef/bankAccountReg/checkAudit?recAction="+recAction+"&selIds="+ids,
				dataType : "json",
				type : "post", 
				data:$("#form").serialize(),
					success : function(data) {
						  if(data.isSuccess){ 
							  //可以操作
								var diag = new Dialog();
								diag.Width = 800;
								diag.Height = 300;
								diag.Title = "修改银行账户";
								diag.URL = "${ctx}/lnbef/bankAccountReg/editBankAccDialog?id="+id+"&query_entName="+encode($("#entName").val());
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
						  }else{
							  //不可以操作
							  Dialog.alert(data.message); 
						  }
					}
				});
			}
		}else{
			Dialog.alert('请选择要修改的账户!');
		}
	}
	
	//查询
	function query(){
		if(document.getElementById("query_entName").value.length==0){
			 $("#entName").val("");
			Dialog.alert('请输入企业名称!');
			refreshGrid();
			return false;
		}
		if(document.getElementById("query_entName").value.length>0){
			$.ajax( {
				url : "${ctx}/lnbef/bankAccountReg/checkEntprise?query_entName="+encode($("#query_entName").val())+GetUrlConNum(),
				dataType : "json",
				success : function(data) {
				  if(data.isSuccess){ 
					  $("#entName").val($("#query_entName").val());
					  var params = {};
						var fields =$('#queryForm').serializeArray();
						$.each( fields, function(i, field){                        
							params[field.name] = field.value; 
						});  
						$('#grid').datagrid('options').queryParams = params;
						$('#grid').datagrid('options').pageNumber = 1;
						
						refreshGrid();
				   }else{
					   Dialog.alert(data.message); 
					    $("#entName").val($("#query_entName").val());
					   return false;
				   }
				}
			});
		}
	}
</script>
<body>  
	<form id="queryForm">
	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>  
<%--	中间变量  保存 企业名称条件值--%>
	<table class="table" style="width: 100%;margin: 0 0 5 0;">
		<tr>
			<td colspan="5">当前企业名称：<input id="entName" style="border:0;"  readonly="readonly" name="entName" type="text" value="" /></td>
		</tr>
		<tr>
			<td style="width:15%;color:red;">*企业名称：</td>
			<td style="width:30%;"><input name="query_entName" id="query_entName" type="text" style="width:90%;"/></td> 
			<td style="width:15%;">审核状态：</td>
			<td style="width:30%;">
				<select name="query_auditStatus" id="query_auditStatus">
					<option value="">全部</option>
					<option value="0">初始</option>
					<option value="1">审核中</option>
					<option value="2">审核通过</option>
					<option value="3">审核未通过</option>
				</select>
			</td> 
			<td style="width:10%;"><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td> 
		</tr>  
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
