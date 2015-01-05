<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css" />
</head>
<script>
	 
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'接口接收',
			url:'${ctx}/itf/rec/getList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true, 
			idField:'id',
			columns:[[   
                {field:'itfNo',title:'报文类型编号',width:150,align:'center'},   
                {field:'sendSysNo',title:'发起方系统编号',width:150,align:'center',
                	formatter:function(val){   
                		return val == 'XD'?'信贷':'费用支付';
                   	} 	
                },
                {field:'sendLastReqTime',title:' 发起方请求时间',width:150,align:'center'},
                {field:'handledFlag',title:'业务是否处理',width:150,align:'center',
                	formatter:function(val){   
                		var str = '';
                		if(val == '1'){
                			str = '处理成功';
                		}else if(val == '0'){
                			str = '处理失败';
                		}else if(val == null){
                			str = '尚未处理';
                		}
                		return str;
                   	} 	
                },
                {field:'operator',title:'操作',width:180,align:'center',
					formatter:function(val,row,index){   
						var flag = row.handledFlag;
						var s = "";
						if(flag == '0'){
							s += "<a href='javascript:void(0)' onclick=\"reHandle('"+row.id+"')\">重新处理</a>";
						}
						s+="&nbsp;&nbsp;<a href='javascript:void(0)' onclick=\"view('"+row.id+"')\">查看报文</a>";
                        return s;   
                   	} 
                }
	        ]] 
		});
	}  
	
	function refreshGrid(){ 
		$('#grid').datagrid("reload");
		$('#grid').datagrid('clearSelections');
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
	
	function view(id){ 
		var diag = new Dialog();
		diag.Title = "";
		diag.Width = 800;
		diag.Height = 600;
		diag.URL = "${ctx}/itf/rec/getMessage?id="+id; 
		diag.show(); 
	}
	
	function reHandle(id){
		Dialog.confirm('您确认要重新处理吗？',function(){
			$.ajax( {
				url : "${ctx}/itf/rec/handleMessage",
				data : {
					"id" : id
				},
				method :'POST',
				dataType : "json",
				success : function(data) {
				   Dialog.alert(data.remark); 
				   refreshGrid();
				}
			});
		});
	}
</script>
<body>
	<%--grid页码--%>

	<form id="queryForm" method="post">

		<div class="form_title" style="width: 100%; text-align: center;">查询条件</div>
		<table class="table" style="width: 100%; margin: 0 0 5 0;">
			<tr>
				<td style="width: 15%;">接口编号：</td>
				<td style="width: 30%;"><input name="query_itfNo" type="text" style="width: 90%;" /></td>
				<td style="width: 15%;">业务处理状态：</td>
				<td style="width:30%;">
					<input type="radio" name="query_handledFlag" value="0" /> 未处理
					<input type="radio" name="query_handledFlag" value="1" /> 已处理
				</td> 
			</tr>
			<tr>
				<td style="width: 15%;">开始日期：</td>
				<td style="width: 30%;"><input type="text"	name="query_startSendReqTime" class="Wdate"	onfocus="WdatePicker({lang:'zh',dateFmt:'yyyy-MM-dd'})"	style="width: 90%;" onclick="WdatePicker()" /></td>
				<td style="width: 15%;">结束日期：</td>
				<td style="width: 30%;"><input type="text"	name="query_endSendReqTime" class="Wdate" onfocus="WdatePicker({lang:'zh',dateFmt:'yyyy-MM-dd'})" style="width: 90%;" onclick="WdatePicker()" /></td>
				<td style="width: 10%;"><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td>
			</tr>
		</table>
	</form>
	<table id="grid"></table>
</body>
</html>
