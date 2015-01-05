<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
    <script type="text/javascript" src="${ctx}/scripts/jquery-easyui/extension/datagrid-detailview.js"></script>
 </head>
 <script>
	 
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'现有贷款查询列表',
			url:'${ctx}/lncenter/existingLoanSearch/getList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			columns:[[   
	                   {field:'proName',title:'贷款项目',width:150,align:'center'},
	                   {field:'loanNo',title:'贷款编号',width:150,align:'center'},
	                   {field:'loadMoney',title:'贷款金额',width:150,align:'center'},
	                   {field:'bank',title:'贷款银行',width:150,align:'center'},
	                   {field:'loadOutTime',title:'贷款开始日期',width:150,align:'center'},
	                   {field:'loadReturnTime',title:'贷款到期日期',width:150,align:'center'}
	        ]],
	        view: detailview,
                detailFormatter:function(index,row){
                    return '<div style="padding:2px"><table class="ddv"></table></div>';
                },
                onExpandRow: function(index,row){
                    var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
                    ddv.datagrid({
                        url:'${ctx}/lncenter/existingLoanSearch/getDetailList?id='+row.id,
                        fitColumns:true,
                        singleSelect:true,
                        rownumbers:true,
                        loadMsg:'',
                        height:'auto',
                        columns:[[
                            {field:'operationType',title:'放款/还款类型',width:100,align:'center'},
                            {field:'loadOutOrReturnTime',title:'放款/还款时间',width:200,align:'center'},
                            {field:'loadOutOrReturnMoney',title:'放款/还款金额',width:100,align:'center'},
	                        {field:'kkpz',title:'放款/还款凭证',width:100,align:'center',
	                               formatter:function (val,row,index){
                            			if(row.loanOut!=""){
	                            			return "<a href='javascript:void(0)' onclick=\"createPDF('"+row.id+"','loanOut')\">下载</a>";
	                            		}else{
	                            			return "<a href='javascript:void(0)' onclick=\"createPDF('"+row.id+"','loanReturn')\">下载</a>";
	                            		}
								   }
	                        }
                        ]],
                        onResize:function(){
                            $('#grid').datagrid('fixDetailRowHeight',index);
                        },
                        onLoadSuccess:function(){
                            setTimeout(function(){
                                $('#grid').datagrid('fixDetailRowHeight',index);
                            },0);
                        }
                    });
                    $('#grid').datagrid('fixDetailRowHeight',index);
                }
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
	
	function createPDF(id,type){
		window.location.href="${ctx}/lncenter/existingLoanSearch/createPDF?id="+id+"&type="+type;
		/*$.ajax({
			url : "${ctx}/lncenter/existingLoanSearch/createPDF",
			dataType : "json",
			type : "post", 
			data:{"id":id,
				  "type":type
			},
			success : function(data) {
				  if(!data.isSuccess){ 
					   Dialog.alert(data.message); 
				   }
			}
		});*/
	}
</script>
<body>  
	<%--grid页码--%>
	<form id="queryForm"  method="post">

	<div class="form_title"  style="width: 100%;text-align:center;">查询条件</div>  
	<table class="table" style="width: 100%;margin: 0 0 5 0;">
		<tr>
			<td style="width:15%;">贷款项目：</td>
			<td style="width:30%;"><input name="proName" type="text" style="width:90%;" /></td> 
			<td style="width:15%;">贷款金额：</td>
			<td style="width:30%;"><input  type="text" name="loadMoney"   style="width:90%;"  /></td>
			<td style="width:10%;" algin=center><input name="queryBtn" class="button" type="button" onclick="query()" value="查询" /></td>
		</tr>  
		
	</table>
	</form> 
	<table id="grid"></table>
</body>
</html>
