<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
	<script src="${ctx}/scripts/formValidator/formValidator-4.1.1.js" type="text/javascript"></script> <!--表单验证必须库-->
	<script src="${ctx}/scripts/formValidator/formValidatorRegex.js" type="text/javascript"></script> <!--表单验证扩展库-->
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDialog.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/validate.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/datePicker/WdatePicker.js"></script>
</head>
<style>
.req{color:red}
body{text-align:center;width:100%;}
</style>
<script>
	$(function(){
		if($("loadOutTime").val()==""){
			Dialog.alert("请填写放款时间");
			return;
		}else{
			$("#confirm").click(function(){
				 $.ajax( {
					url : "${ctx}/lnmid/clmsLoanRec/saveLoanOut",
					dataType : "json",
					type : "post", 
					data:$("#form").serialize(),
					success : function(data) {
						  if(data.isSuccess){ 
							  ownerDialog.doReturn(data);
						   }else{
							   Dialog.alert(data.message); 
						   }
					}
				});
			})
		}
	})
</script>
<body class="mainBody">
<form id="form">
<input name="recId" id="recId" value="${clmsLoanRec.id }" type="hidden"/>
<div class="form_title" style="width: 80%;margin-right:auto;margin-left:auto;" >新贷款申请详情</div>  
<table class="table" style="width: 80%" align=center >
	<tr>
		<td style="width:10%;" align=right>贷款类型：</td>
		<td style="width:30%;height:30px">投标保证金
			<select id="productId" name="productId" style="display:none">
				<option value="1" selected>投标保证金</option>
			</select>
		</td> 
	</tr> 
	<tr>
		<td style="width:10%;" align=right>贷款用户：</td>
		<td style="width:30%;height:30px">${realName }
		<input name="userId" id="userId" value="${userId }" type="hidden"/>
		</td> 
	</tr>  
	<tr>
		<td style="width:15%;" align=right>放款金额：</td>
		<td style="width:30%;height:30px">${clmsLoanRec.loadMoney}
			<input id="loadOutMoney" name="loadOutMoney" type="text" value="${clmsLoanRec.loadMoney}" style="width:22%;display:none"/>元
		</td>
	</tr>  
	<tr>
		<td style="width:15%;" align=right>放款时间：</td>
		<td style="width:30%;height:30px">
			<input id="loadOutTime" name="loadOutTime" type="text" value="" class="Wdate" onfocus="WdatePicker()"/>
		</td>
	</tr>
	<tr id="operation">
		<td colspan="4" style="height:35px;" align="center"> 
			<input id="confirm"  class="button" type="button"  value="确定" />
			<input  class="button" type="button"  value="取消" onclick="ownerDialog.close()" />
		</td>
	</tr>  
</table> 	 
</form>		
</body>
</html>
