<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
	<script src="${ctx}/scripts/formValidator/formValidator-4.1.3.js" type="text/javascript"></script> <!--表单验证必须库-->
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
function lenValidator(val, elem) {
	if (val.length>250){ 
        return "备注长度要小于250";
	} else {
		return true ;
    }
}

	$(document).ready(function(){	
		$.formValidator.initConfig({ formID: "form"})
		$("#tenderNo").formValidator({ onShow:"请输入招标编号",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"招标编号不能为空"});
		$("#proName").formValidator({ onShow:"请输入贷款项目名称",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"项目名称不能为空"});
		$("#proNo").formValidator({ onShow:"请输入贷款项目编号",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"项目编号不能为空"});
		$("#bidName").formValidator({ onShow:"请输入贷款标段名称",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"标段名称不能为空"});
		$("#bidNo").formValidator({ onShow:"请输入贷款标段编码",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"标段编码不能为空"});
		$("#bidFinishDate").formValidator({ onShow:"请输入投标截止日期(注：投标截止日期不能小于当前日期)",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"投标截止日期不能为空"});
		$("#applyLoanTime").formValidator({ onShow:"请输入申请放款时间",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"申请放款时间不能为空"});
		$("#loanMoney").formValidator({ onShow:"请输入对应保证金金额",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,max:18,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"贷款意向金额不能为空且长度不能大于18"}).regexValidator({ regExp: "intege1", dataType: "enum", onError: "贷款金额只能为数字" });
		$("#remark").formValidator({ onShow:"",onFocus:"", onCorrect: "" }).functionValidator({ fun: lenValidator });
		$("#apply").click(function(){ 
			if($.formValidator.pageIsValid()){
				 $.ajax( {
					url : "${ctx}/lncenter/clmsLoanApply/checkApplyInfo",
					dataType : "json",
					type : "post", 
					data:$("#form").serialize(),
					success : function(data) {
						  if(data.isSuccess){ 
							   $("#form").submit();
						   }else{
							   Dialog.alert(data.message); 
						   }
					}
				});
			}
		})
	})

</script>
<body class="mainBody">
<form id="form" method="post" action="${ctx}/lncenter/clmsLoanApply/loanApplyView">
<input id="id" name="id" type="hidden" value="${clmsLoanApplyVO.id}" />
<div class="form_title" style="width: 80%;margin-right:auto;margin-left:auto;text-align:left;font-size:15px" >新贷款申请</div>  
<table class="table" style="width: 80%" align=center >
	<tr>
		<td style="width:10%;" align=right><p class="req">*贷款类型：</p></td>
		<td style="width:30%;height:30px">
			<select id="loanType" name="loanType">
				<option value="1" selected>投标保证金</option>
			</select>
		</td> 
	</tr>  
	<tr>
		<td style="width:15%;" align=right><p class="req">*招标编号：</p></td>
		<td style="width:30%;height:30px">
			<input id="tenderNo" name="tenderNo" type="text" value="${clmsLoanApplyVO.tenderNo}" style="width:50%;" maxlength="25" />
			<div id="tenderNoTip"  style="display:inline;font-size:13px;color:red;width:300px"></div>
		</td> 
	</tr>  
	<tr>
		<td style="width:15%;" align=right><p class="req">*项目名称：</p></td>
		<td style="width:30%;height:30px">
			<input id="proName" name="proName" type="text" value="${clmsLoanApplyVO.proName}" style="width:50%;" maxlength="100" />
			<div id="proNameTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>   
	<tr>
		<td style="width:15%;" align=right><p class="req">*项目编号：</p></td>
		<td style="width:30%;height:30px">
			<input id="proNo" name="proNo" type="text" value="${clmsLoanApplyVO.proNo}" style="width:50%;" maxlength="25" />
			<div id="proNoTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>
	<tr>
		<td style="width:15%;" align=right><p class="req">*标段名称：</p></td>
		<td style="width:30%;height:30px">
			<input id="bidName" name="bidName" type="text" value="${clmsLoanApplyVO.bidName}" style="width:50%;" maxlength="100" />
			<div id="bidNameTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>  
	<tr>
		<td style="width:15%;" align=right><p class="req">*标段编码：</p></td>
		<td style="width:30%;height:30px">
			<input id="bidNo" name="bidNo" type="text" value="${clmsLoanApplyVO.bidNo}" style="width:50%;" maxlength="25" />
			<div id="bidNoTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td> 
	</tr>  
	<tr>
		<td style="width:15%;" align=right><p class="req">*招标类型：</p></td>
		<td style="width:30%;height:30px">
			<select name="bidType" id="bidType">
				<option value="002">施工</option>
				<option value="005">监理</option>
				<option value="004">勘察设计</option>
				<option value="001">试验检测</option>
				<option value="003">材料采购</option>
			</select>
			<div id="bidTypeTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td> 
	</tr>
	<tr>
		<td style="width:15%;" align=right>招标资审方式：</td>
		<td style="width:30%;height:30px">
			<input  name="bidCheckType" type="radio" checked value="001"/>资格预审<input  name="bidCheckType" type="radio" value="002"/>资格后审
			<div id="bidCheckTypeTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>  
	<tr>
		<td style="width:15%;" align=right>招标单位：</td>
		<td style="width:30%;height:30px">
			<input id="tenderComp" name="tenderComp" type="text" value="${clmsLoanApplyVO.tenderComp}" style="width:50%;" maxlength="150" />(选填)
			<div id="tenderCompTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>  
	<tr>
		<td style="width:15%;" align=right>招标代理：</td>
		<td style="width:30%;height:30px">
			<input id="tenderAgent" name="tenderAgent" type="text" value="${clmsLoanApplyVO.tenderAgent}" style="width:50%;" maxlength="150" />(选填)
			<div id="tenderAgentTip"  style="font-size:13px;color:red;width:300px;display:inline"></div>
		</td>
	</tr>   
	<tr>
		<td style="width:15%;" align=right><p class="req">*投标截止日期：</p></td>
		<td style="width:30%;height:30px">
			<input id="bidFinishDate" name="bidFinishDate" type="text" value="${clmsLoanApplyVO.bidFinishDate}" class="Wdate" onfocus="WdatePicker({minDate:'${currentDate}'})"/>
			<div id="bidFinishDateTip"  style="font-size:13px;color:red;width:400px;display:inline"></div>
		</td>
	</tr>
	<tr>
		<td style="width:15%;" align=right><p class="req">*申请放款时间：</p></td>
		<td style="width:30%;height:30px">
			<input id="applyLoanTime" name="applyLoanTime" type="text" value="${clmsLoanApplyVO.applyLoanTime}" class="Wdate" onfocus="WdatePicker({minDate:'${currentDate}'})"/>
			<div id="applyLoanTimeTip"  style="font-size:13px;color:red;width:400px;display:inline"></div>
		</td>
	</tr>
	<tr>
		<td style="width:15%;" align=right><p class="req">*贷款意向金额：</p></td>
		<td style="width:30%;height:30px">
			<input id="loanMoney" name="loanMoney" type="text" value="${clmsLoanApplyVO.loanMoney}" style="width:22%;" maxlength="20"/>元
			<div id="loanMoneyTip"  style="font-size:13px;color:red;width:400px;display:inline"></div>
		</td>
	</tr>  
	<tr>
		<td style="width:15%;" align=right>备注：</td>
		<td style="width:30%;">
			<textarea id="remark" name="remark" rows=5 cols=56>${clmsLoanApplyVO.remark}</textarea>
			<div id="remarkTip"  style="font-size:13px;color:red;width:400px;display:inline"></div>
		</td> 
	</tr> 
	<tr>
		<td colspan="4" style="height:35px;" align="center"> 
			<input id="apply"  class="button" type="button"  value="申请" />
			<input  class="button" type="reset"  value="重置" />
		</td>
	</tr>  
</table> 	 
</form>	
</body>
</html>
