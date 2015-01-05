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
			title:'接口发送',
			url:'${ctx}/itf/send/getList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			
			columns:[[   
                {field:'itfNo',title:'报文编号',width:100,align:'center'},   
                {field:'sendSysNo',title:'发起方系统',width:100,align:'center',
                	formatter:function(val){   
                		return val == 'XD'?'信贷':'费用支付';
                   	} 	
                },
                {field:'sendReqTime',title:'发起方请求时间',width:130,align:'center'},
                {field:'recResResultType',title:'发送是否成功',width:100,align:'center',
                	formatter:function(val,row){   
                		var str = '';
                		var repeatNum = row.repeatNum;
                		var sendFlag = row.sendFlag;
                		if(val == '1'){	str = '发送成功'; }
                		if(val == '0'){ str = '发送失败'; }
                		if(val == '0' && repeatNum == null && sendFlag == '0'){ str = '尚未发送'; }
                		if(val == '0' && repeatNum != null && sendFlag == '0'){ str = '重新发送中'; }
                		return str;
                   	} 		
                },
                {field:'recResReamrk',title:'接收方响应描述',width:100,align:'center'},
                {field:'repeatNum',title:'重复发送次数',width:100,align:'center'},
                {field:'sendLastReqTime',title:' 最后发起方请求时间',width:150,align:'center'},              
                {field:'operator',title:'操作',width:180,align:'center',
					formatter:function(val,row,index){   
						var sendflag = row.sendFlag;
						var resultFlag = row.recResResultType;
						var s = "";
						if(resultFlag == '0' && sendflag == '1'){
							s += "<a href='javascript:void(0)' onclick=\"reSend('"+row.id+"')\">重新发送</a>";
						}
						s+="&nbsp;<a href='javascript:void(0)' onclick=\"view('"+row.id+"')\">查看报文</a>";
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
	
	function reSend(id){
		Dialog.confirm('您确认要重新发送吗？',function(){
			$.ajax( {
				url : "${ctx}/itf/send/sendMessage",
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
	
	function view(id){ 
		var diag = new Dialog();
		diag.Title = "";
		diag.Width = 800;
		diag.Height = 600;
		diag.URL = "${ctx}/itf/send/getMessage?id="+id; 
		diag.show(); 
	}
	
</script>
<body>  
	<%--grid页码--%>
	
	<form id="queryForm"  method="post">

	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>  
	<table class="table" style="width: 100%;margin: 0 0 5 0;">
		<tr>
			<td style="width:15%;">接口编号：</td>
			<td style="width:30%;"><input name="query_itfNo" type="text" style="width:90%;" /></td> 
			<td style="width:15%;">发送状态：</td>
			<td style="width:30%;">
				<input type="radio" name="query_recResResultType" value="0" /> 失败
				<input type="radio" name="query_recResResultType" value="1" /> 成功
			</td> 
		</tr> 
		<tr>
			<td style="width:15%;">开始日期：</td>
			<td style="width:30%;"><input  type="text" name="query_startSendReqTime" class="Wdate" onfocus="WdatePicker({lang:'zh',dateFmt:'yyyy-MM-dd'})"  style="width:90%;" onclick="WdatePicker()" /></td>
			<td style="width:15%;">结束日期：</td>
			<td style="width:30%;"><input  type="text" name="query_endSendReqTime" class="Wdate" onfocus="WdatePicker({lang:'zh',dateFmt:'yyyy-MM-dd'})"  style="width:90%;" onclick="WdatePicker()" /></td>
			<td style="width:10%;"><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td> 
		</tr>  
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
