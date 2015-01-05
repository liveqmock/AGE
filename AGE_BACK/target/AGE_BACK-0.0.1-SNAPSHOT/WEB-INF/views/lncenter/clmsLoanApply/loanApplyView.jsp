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
		$("#apply").click(function(){
			Dialog.confirm('您确认要提交吗？',function(){
				 $.ajax( {
					url : "${ctx}/lncenter/clmsLoanApply/saveLoanApplyNew",
					dataType : "json",
					type : "post", 
					data:$("#form").serialize(),
					success : function(data) {
						  if(data.isSuccess){ 
							  Dialog.alert(data.message,function(){
								  window.location.href="${ctx}/lncenter/loanApplySchedule/search";
							  });
						   }else{
							   Dialog.alert(data.message); 
						   }
					}
				});
			});
		})
	})
</script>
<body class="mainBody">
<form id="form">
<input id="id" name="id" type="hidden" value="${clmsLoanApplyVO.id}" />
<div class="form_title" style="width: 80%;margin-right:auto;margin-left:auto;" >新贷款申请详情</div>  
<table class="table" style="width: 80%" align=center >
	<tr>
		<td style="width:10%;" align=right><p class="req">*贷款类型：</p></td>
		<td style="width:30%;height:30px">投标保证金
			<select id="loanType" name="loanType" style="display:none">
				<option value="1" selected>投标保证金</option>
			</select>
		</td> 
	</tr>  
	<tr>
		<td style="width:15%;" align=right><p class="req">*招标编号：</p></td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.tenderNo}
			<input id="tenderNo" name="tenderNo" type="text" value="${clmsLoanApplyVO.tenderNo}" style="width:50%;display:none"/>
			<div id="tenderNoTip"  style="display:inline;font-size:13px;color:red;width:300px"></div>
		</td> 
	</tr>  
	<tr>
		<td style="width:15%;" align=right><p class="req">*项目名称：</p></td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.proName}
			<input id="proName" name="proName" type="text" value="${clmsLoanApplyVO.proName}" style="width:50%;display:none"/>
			<div id="proNameTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>   
	<tr>
		<td style="width:15%;" align=right><p class="req">*项目编码：</p></td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.proNo}
			<input id="proNo" name="proNo" type="text" value="${clmsLoanApplyVO.proNo}" style="width:50%;display:none"/>
			<div id="proNoTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>
	<tr>
		<td style="width:15%;" align=right><p class="req">*标段名称：</p></td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.bidName}
			<input id="bidName" name="bidName" type="text" value="${clmsLoanApplyVO.bidName}" style="width:50%;display:none"/>
			<div id="bidNameTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>  
	<tr>
		<td style="width:15%;" align=right><p class="req">*标段编码：</p></td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.bidNo}
			<input id="bidNo" name="bidNo" type="text" value="${clmsLoanApplyVO.bidNo}" style="width:50%;display:none"/>
			<div id="bidNoTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td> 
	</tr>  
	<tr>
		<td style="width:15%;" align=right><p class="req">*招标类型：</p></td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.bidType}
			<!-- <select name="bidType" id="bidType" style="display:none">
				<option value="002">施工</option>
				<option value="005">监理</option>
				<option value="004">勘察设计</option>
				<option value="001">试验检测</option>
				<option value="003">材料采购</option>
			</select> -->
			
			<input name="bidType" type="hidden" value="${bidType}"/>
			<div id="bidTypeTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td> 
	</tr>
	<tr>
		<td style="width:15%;" align=right>招标资审方式：</td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.bidCheckTypeName}
			<div style="display:none">
			<input  name="bidCheckType" type="radio" ${clmsLoanApplyVO.bidCheckType eq '001'?'checked':''}  value="001"/>资格预审<input  name="bidCheckType" type="radio" ${clmsLoanApplyVO.bidCheckType eq '002'?'checked':''} value="002"/>资格后审
			</div>
			<div id="bidCheckTypeTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>  
	<tr>
		<td style="width:15%;" align=right>招标单位：</td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.tenderComp}
			<input id="tenderComp" name="tenderComp" type="text" value="${clmsLoanApplyVO.tenderComp}" style="width:50%;display:none"/>
			<div id="tenderCompTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>  
	<tr>
		<td style="width:15%;" align=right>招标代理：</td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.tenderAgent}
			<input id="tenderAgent" name="tenderAgent" type="text" value="${clmsLoanApplyVO.tenderAgent}" style="width:50%;display:none"/>
			<div id="tenderAgentTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>   
	<tr>
		<td style="width:15%;" align=right><p class="req">*投标截止日期：</p></td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.bidFinishDate}
			<input id="bidFinishDate" name="bidFinishDate" type="hidden" value="${clmsLoanApplyVO.bidFinishDate}" class="Wdate" onfocus="WdatePicker()"/>
			<div id="bidFinishDateTip"  style="font-size:13px;color:red;width:400px;display:inline"></div>
		</td>
	</tr>
	<tr>
		<td style="width:15%;" align=right><p class="req">*申请放款时间：</p></td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.applyLoanTime}
		<input id="applyLoanTime" name="applyLoanTime" type="hidden" value="${clmsLoanApplyVO.applyLoanTime}" class="Wdate" onfocus="WdatePicker()"/>
			<div id="applyLoanTimeTip"  style="font-size:13px;color:red;width:400px;display:inline"></div>
		</td>
	</tr>
	<tr>
		<td style="width:15%;" align=right><p class="req">*贷款意向金额：</p></td>
		<td style="width:30%;height:30px">${clmsLoanApplyVO.loanMoney}
			<input id="loanMoney" name="loanMoney" type="text" value="${clmsLoanApplyVO.loanMoney}" style="width:22%;display:none"/>元
			<div id="loanMoneyTip"  style="font-size:13px;color:red;width:400px;display:inline"></div>
		</td>
	</tr>  
	<tr>
		<td style="width:15%;" align=right>备注：</td>
		<td style="width:30%;">${clmsLoanApplyVO.remark}
			<textarea style="display:none" id="remark" name="remark" rows=5 cols=56>${clmsLoanApplyVO.remark}</textarea>
		</td> 
	</tr> 
	<tr>
		<td style="width:15%;" align=right>基本户银行：</td>
		<td style="width:30%;">${clmsLoanBank.bank}
		</td> 
	</tr> 
	<tr>
		<td style="width:15%;" align=right>分支行名称·：</td>
		<td style="width:30%;">${clmsLoanBank.branchname}
		</td> 
	</tr> 
	<tr>
		<td style="width:15%;" align=right>基本户帐户名称：</td>
		<td style="width:30%;">${clmsLoanBank.accName}
		</td> 
	</tr>
	<tr>
		<td style="width:15%;" align=right>基本户帐号：</td>
		<td style="width:30%;">${clmsLoanBank.accNo}
		</td> 
	</tr>  
	<tr id="operation">
		<td colspan="4" style="height:35px;" align="center"> 
			<input id="apply"  class="button" type="button"  value="提交" />
			<input  class="button" type="button"  value="取消" onclick="window.history.back()" />
		</td>
	</tr>  
</table> 	 
</form>		
</body>
</html>
