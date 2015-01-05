<%@ page language="java" import="java.util.*,com.drcgw.clms.common.Constants" pageEncoding="UTF-8"%>
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
			title:'模板环节列表',
			url:'${ctx}/sysmng/sysTempFlow/getTempStepListToFlow?sysTempFlowID='+$("#sysTempFlowID").val(), 
			pageSize:10, 
			pagination:true,
			rownumbers:true, 
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
	                   {field:'ownerType',title:'拥有者类型',width:80,
	                	   formatter:function(val,row){
	                		   //拥有者类型 0=角色；1=部门；2:=人员
								if(val == '0'){
									return '角色';
								}else if(val == '1'){
									return '部门';
								}else if(val == '2'){
									return '人员';
								}else{
									return val;
								}
							}
	                   },

	                  // {field:'ownerName',title:'拥有者',width:180},

	                   {field:'orderNo',title:'顺序号',width:80}
	              		]],
			toolbar:
			[{ 
				id:'btn_add',
				text:'选择环节',
				iconCls:'icon-add', 
				handler:function(){
					selectStep(); 
				}
			}
			/**,{
				id:'btn_eidt',
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					edit();
				}
			}*/
			,{ 
				id:'btn_eidt',
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
					removeAll(); 
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
		diag.Width = 800;
		diag.Height = 400;
		diag.Title = "";
		return diag
	}
	
	var orderNo = 10; //从10 开始
	function getOrderNo(){
		var rows = $('#grid').datagrid('getRows');
		var selectedRows = $('#grid').datagrid('getSelected');
		if(rows.length > 0){
			var row = rows[rows.length -1];
			var orderTemp = Math.floor(row.orderNo/10) * 10;
			orderNo = orderTemp + 10;
		}
		if(selectedRows){
			orderNo = parseInt(selectedRows.orderNo) + 2;
		}
	}
	function selectStep(){
		getOrderNo();
		var diag = createDialog();
		diag.Title = "选择环节";
		diag.URL = "${ctx}/sysmng/sysTempFlowStep/selectStep?orderNo="+orderNo+"&sysTempFlowID="+$("#sysTempFlowID").val();
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
	/**
	function edit(){  
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length == 1){ 
			var diag = createDialog();
			diag.URL = "${ctx}/sysmng/sysTempFlowStep/flowStepEdit?id="+rows[0].id;
			diag.doReturn = function(data){
				if(data){  
					refreshGrid();
					diag.close();
				}
			}; 
			diag.show(); 
		}else{
			Dialog.alert('请选择一条要修改的数据项!');
		}  
	}
	**/
		function removeAll(){ 
		var ids = [];
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].id);
			}
			Dialog.confirm('您确认删除所选的数据项吗？',function(){
				$.ajax( {
					url : "${ctx}/sysmng/sysTempFlowStep/removeAll?sysTempFlowID="+$("#sysTempFlowID").val()+"&selIds="+ids,
					dataType : "json",
					success : function(data) {
						  if(data.isSuccess){
							   Dialog.alert(data.message); 
							   refreshGrid();
						   }else{
							   Dialog.alert(data.message); 
						   }
					}
				});
			}); 
		}else{
			Dialog.alert('请选择要删除的数据项!');
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
		<!--流程ID-->
<input id ="sysTempFlowID" value="${sysTempFlowID}" style="display: none"/>
	<table id="grid"></table>
</body>
</html>
