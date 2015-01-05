<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
	<link rel="stylesheet" type="text/css" href="${ctx}/scripts/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/scripts/jquery-easyui/themes/icon.css">
	
	<script type="text/javascript" src="${ctx}/scripts/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/jquery-easyui/locale/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDialog.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/validate.js"></script>
</head>
<script> 
	$(function(){ 
		initTree(); 
	});  
	function initTree(){ 
		$('#resourceTree').tree({
			checkbox: true, 
			url: '${ctx}/sysmng/sysResource/getTreeList?sysRoleId=${sysRoleId}'
		});
	} 
	function getResourceIds(){
		var ids = [];
		var nodes = $('#resourceTree').tree('getChecked');
		for(var i=0;i<nodes.length;i++){
			ids.push(nodes[i].id);
		}
		return ids.join(',');
	}
	
	function save(){ 
		$.post("${ctx}/sysmng/sysResource/saveSysRoleResources?sysRoleId=${sysRoleId}&resourceIds="+getResourceIds(),null,
		    function(data) {
		        if(data.isSuccess){ 
					   ownerDialog.doReturn(data);
				   }else{
					   Dialog.alert(data.message); 
				   }
		    },
		    "json"
		 ); 
	}
</script>
<body>  
	<div align="right">
		<input name="saveBtn" class="button" type="button" onclick="save()" value="确定" /> 
	</div>
	<div>
		<ul id="resourceTree" class="tree"></ul>
	</div>
</body>
</html>
