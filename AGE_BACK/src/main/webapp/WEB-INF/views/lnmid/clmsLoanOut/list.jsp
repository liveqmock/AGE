<%@ page language="java" import="java.util.*,com.drcgw.clms.common.Constants" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>
     <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>
    <style type="text/css">
	.td-even{text-align:right;}
	.td-odd{text-align:left;}
	</style>
    <%
    	request.setAttribute("curStep", Constants.curStep_OUT);
    	request.setAttribute("curStepStatus", Constants.curStep_STATUS);
    %>
 </head>
 <script>
	 
	$(function(){  
		loadGrid();
	});	
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'放贷划拨列表',
			url:'${ctx}/lnmid/clmsLoanOut/getList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true, 
			idField:'id',
			singleSelect:true,
			frozenColumns:[[ 
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[
					{field:'loanNo',title:'贷款流水号',width:100,align:'center'},
					{field:'loadOutTime',title:'放款时间',width:130,align:'center'},
					{field:'loadOutMoney',title:'放款金额',width:100,align:'center'},
					{field:'loanOutStatus',title:'放款状态',width:100,align:'center',formatter:status},
					{field:'recCreateTime',title:'创建时间',width:130,align:'center'},
					{field:'curStep',title:'操作',width:150,align:'center',formatter:operator},
					{field:'curStepStatus',title:'操作状态',width:200,align:'center',formatter:status2}
	              		]],
	         onLoadSuccess:function(data){
				$.parser.parse();
			}
			
		});
	}
	function status(val,row,index){
		if(val==0)
			return '初始';
		else if(val == 1)
			return '进行中';
		else if(val == 2)
			return '成功';
		else if(val == 3)
			return '失败';
		else
			return '状态异常';
	}
	function status2(val,row,index){
		var text = '';
		if(val==0)
			text = '初始';
		else if(val == 1)
			text = '进行中';
		else if(val == 2)
			text = '成功';
		else if(val == 3)
			text = '失败';
		else
			text = '状态异常';
		var step = row.curStep;
		var name = $("#curStep option[value='"+step+"']").text();
		
		return name + "  " +text;
	}
	function operator(value,row,index){
		var name = $("#curStep option[value='"+value+"']").text();
		var temp = "<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"nextNode('"+row.id+"',this)\">"+name+"</a>";
		if(row.curStepStatus == 0){
			
		}
		else if(row.curStepStatus == 1 )
			temp = name;
		else if(row.curStepStatus == 2){
			var first = value.substring(0,1);
			var last = value.substring(1);
			var full = first + (parseInt(last)+1);
			if(last == '4'){
				temp = name;
			}else{
				var name = $("#curStep option[value='"+full+"']").text();
				temp = "<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"nextNode('"+row.id+"',this)\">"+name+"</a>";
			}
		}else if(row.curStepStatus == 3){
			
		}else{
			temp = name;
		}
		return temp;
	}
	function nextNode(id,obj){
		Dialog.confirm('警告：您确认要 <font color="red">'+$(obj).text()+'</font>？',function(){
		$(obj).hide();
		$.ajax({
		     url: "${ctx}/lnmid/clmsLoanOut/nextNode",
		     type: "POST",
		     data:{id:id},
		     dataType : "json",
		     success: function(data){
		        var success = data.isSuccess;
		        var message = data.message;
		        if(success){
		        	Dialog.alert(message);
		          	refreshGrid();
		         }else{
		            Dialog.alert(message);
		            refreshGrid();
		           }
		          },
		       error: function(XMLHttpRequest,textStatus,errorThrown){alert(errorThrown);}
		  });
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
	
	function create(){
		var diag = createDialog();
		diag.URL = "${ctx}/ebms/clmsLoanOut/create";
		diag.doReturn = function(data){
			if(data.isSuccess){
				refreshGrid();
				diag.close();
		    }else{
			    Dialog.alert(data.message);
		    }
		};
		diag.show();
		
	}
	
	function view(id){
		var diag = createDialog();
		diag.URL = "${ctx}/ebms/clmsLoanOut/view?id="+id;
		diag.show();
	}
	
	 
	function edit(){  
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 1){ 
			var diag = createDialog();
			diag.URL = "${ctx}/ebms/clmsLoanOut/edit?id="+rows[0].id;
			diag.doReturn = function(data){
				if(data.isSuccess){
					refreshGrid();
					diag.close();
			    }else{
				    Dialog.alert(data.message);
			    }
			}; 
			diag.show(); 
		}else{
			Dialog.alert('请选择一条要修改的数据项!');
		}  
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
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',title:'查询条件',split:true,border:false" >
	<form id="queryForm">
	<table style="width: 80%;margin: 0 0 5 0;font-size:12px;">
		<tr>
			<td class="td-even">贷款流水号：</td>
			<td class="td-odd"><input name="loanNo" type="text" /></td>
			<td class="td-even">操作:</td>
			<td class="td-odd">
				<select id="curStep" name="curStep">
					<option></option>
					<c:forEach items="${requestScope.curStep}" var="m" varStatus="id">
     					<option value="${m.key}">${m.value}</option>
     				</c:forEach>
				</select>
			</td>
			<td class="td-even">结果:</td>
			<td class="td-odd">
				<select id="curStepStatus" name="curStepStatus">
					<option></option>
					<c:forEach items="${requestScope.curStepStatus}" var="m" varStatus="id">
     					<option value="${m.key}">${m.value}</option>
     				</c:forEach>
				</select>
			</td>
			<td ><a href="javascript:void(0)" class="easyui-linkbutton" onclick="query()">查询</a></td> 
		</tr>  
	</table>
	</form> 
	<table id="grid"></table>
	</div>
</body>
</html>
