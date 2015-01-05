<%@ page language="java" import="java.util.*,com.drcgw.clms.common.Constants" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>  
    <%@ include file="/common/taglibs.jsp" %> 
     <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/>
     <% 
		request.setAttribute("ownerType",Constants.ownerType);
	%>
	<script type="text/javascript" src="${ctx}/scripts/common/common.js"></script>
 </head>
 <script>
	 
	$(function(){  
		loadGrid();
	});	 
	
	function loadGrid(){ 
		$('#grid').datagrid({
			title:'模板环节列表',
			url:'${ctx}/sysmng/sysTempFlowStep/getTempStepList', 
			pageSize:10, 
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			idField:'id',
			frozenColumns:[[ 
	               {field:'ck',checkbox:true},
	               {field:'id',title:'id',hidden:true}   
			]],
			columns:[[   
	                   {field:'stepName',title:'名称',width:120},   
	                   {field:'displayName',title:'显示名称',width:120},
	                   {field:'remark',title:'备注',width:80},

	                   {field:'operateUrl',title:'操作界面',width:180},
	                   {field:'ownerType',title:'拥有者类型',width:80,formatter:ownerType},

	                   {field:'ownerName',title:'拥有者',width:180},

	                   {field:'orderNo',title:'顺序号',width:80}
	              		]],
			toolbar:
			[{ 
				id:'btn_add',
				text:'确定选择',
				iconCls:'icon-add', 
				handler:function(){
					shureSelect(); 
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
		diag.Width = 600;
		diag.Height = 300;
		diag.Title = "";
		return diag
	}
	

	

	function shureSelect(){ 
		var ids = [];
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].id);
			}
			Dialog.confirm('您确认选择所选的数据项吗？',function(){
				var url = "${ctx}/lnagr/lnagrMgmt/addStep?orderNo=${orderNo}&agreementId=${id}&id="+ids+GetUrlConNum();
				$.ajax( {
					url : url,
					dataType : "json",
					success : function(data) {
						  if(data.isSuccess){ 
							ownerDialog.doReturn(data);

						   }else{
							   Dialog.alert(data.message); 
						   }
					}
				});
			}); 
		}else{
			Dialog.alert('请选择要选择的环节项!');
		} 
	}
	 
	function ownerType(value,row){
		var arr = [];
		<% 
		Map map = Constants.ownerType;
		Set<String> key = map.keySet();
	    for (Iterator it = key.iterator(); it.hasNext();) {
	    	
	        String s = (String) it.next();
	        %>
	    	arr['<%=s%>']='<%=map.get(s)%>';
	    	<%
	    }
		%>
		return arr[value];
	}	
	

</script>
<body>
	<input id ="sysTempFlowID" value="${sysTempFlowID}" style="display: none" />
	<table id="grid"></table>
</body>
</html>
