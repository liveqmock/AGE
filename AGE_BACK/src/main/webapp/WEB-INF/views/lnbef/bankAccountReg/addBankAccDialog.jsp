<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
	<script type="text/javascript" src="${ctx}/scripts/util/validate.js"></script>
	<script src="${ctx}/scripts/formValidator/formValidator-4.1.3.js" type="text/javascript"></script> <!--表单验证必须库-->
	<script src="${ctx}/scripts/formValidator/formValidatorRegex.js" type="text/javascript"></script> <!--表单验证扩展库-->
	<script type="text/javascript" src="${ctx}/scripts/common/common.js"></script>
</head>
<style type="text/css">
    
</style>
<script>
	$(document).ready(function(){	
		$.formValidator.initConfig({ formID: "registForm"})
		$("#accName").formValidator({ onShow: "请填写账户名称",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,onError:"账户名称不能为空"}).regexValidator({ regExp: "chinese", dataType: "enum", onError: "请填写100位以内由汉字组成的账户名称" });
		$("#accNo").formValidator({ onShow: "请填写账户号码", onFocus:"",onCorrect: "格式正确" }).inputValidator({min:1,onError:"账户号码不能为空"}).regexValidator({ regExp: "bankNo", dataType: "enum", onError: "请填写20位以内由数字组成的的账户号码" }).ajaxValidator({  
	        type : "post",  
	        cache : false, 
	        addidvalue: true,
	        async:false,
	        url : "${ctx}/lnbef/bankAccountReg/checkAccNoUnique",  
	        datatype : "json",  
	        success : function(data){
	        	data = $.parseJSON(data); 
	            if(data.isSuccess){  
	                return true;              
	            }else{  
	                return false;                 
	            }  
	        },  
	        error: function(){alert("服务器忙，请重试");},  
	        onError : "您填写的账户号码已存在",  
	        onWait : "正在对组织机构代码进行校验..."  
	    }).defaultPassed();
		$("#branchname").formValidator({ onShow: "请填写分支行名称",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,onError:"分支行名称不能为空"}).regexValidator({ regExp: "chinese", dataType: "enum", onError: "请填写100位以内由汉字组成的分支行名称" });
		$("#bankprovince").formValidator({ onShow: "请填写分支行省份",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,onError:"分支行省份不能为空"}).regexValidator({ regExp: "chinese", dataType: "enum", onError: "请填写100位以内由汉字组成的分支行省份" });
		$("#city").formValidator({ onShow: "请填写分支行城市",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,onError:"分支行城市不能为空"}).regexValidator({ regExp: "chinese", dataType: "enum", onError: "请填写100位以内由汉字组成的分支行城市" });
			//保存账户，初始，停用
			$("#saveBtn").click(function(){
				if($.formValidator.pageIsValid()){
					Dialog.confirm('您确认保存交吗？',function(){
						 $.ajax( {
							url : "${ctx}/lnbef/bankAccountReg/addBankAcc?clmsUserId="+$("#clmsUserId").val()+"&baseAccFlag="+$("#baseAccFlag").val()+"&auditStatus=0"+GetUrlConNum(),
							dataType : "json",
							type : "post", 
							data:$("#form").serialize(),
							success : function(data) {
								  if(data.isSuccess){ 
									   ownerDialog.doReturn(data);
								   }else{
									   ownerDialog.doReturn(data);
								   }
							}
						});
					});
				}
			});
			
			//提交账户，审核中，停用
			$("#submitBtn").click(function(){
				if($.formValidator.pageIsValid()){
					Dialog.confirm('您确认要提交吗？提交之后就不能修改了！',function(){
						 $.ajax( {
							url : "${ctx}/lnbef/bankAccountReg/addBankAcc?clmsUserId="+$("#clmsUserId").val()+"&baseAccFlag="+$("#baseAccFlag").val()+"&auditStatus=1"+GetUrlConNum(),
							dataType : "json",
							type : "post", 
							data:$("#form").serialize(),
							success : function(data) {
								  if(data.isSuccess){ 
									   ownerDialog.doReturn(data);
								   }else{
									   ownerDialog.doReturn(data);
								   }
							}
						});
					});
				}
			});
	})
</script>
<body class="mainBody">
<form id="form" >
<input id="id" name="id" type="hidden" value="${clmsLoanBank.id}" />
<input id="baseAccFlag" name="baseAccFlag" type="hidden" value="${baseAccFlag}" />
<input id="query_entName" name="query_entName" type="hidden" value="${query_entName}" />
<div class="form_title" >账户信息填写</div> 
<table class="table" style="width: 100%">

	<tr>
		<td style="width:15%;color:red">*企业名称：</td>
		<td colspan="2">${query_entName}</td> 
	</tr>
	<tr>
		<td style="width:15%;color:red">*账户名称：</td>
		<td style="width:30%;"><input id="accName" name="accName" type="text" value="${clmsLoanBank.accName}" style="width:90%;"/></td> 
		<td style="width:31%;"><div  id="accNameTip"  style="font-size:13px;color:red;"></div></td>
	</tr>  
	<tr>
		<td style="width:15%;color:red">*账户号码：</td>
		<td style="width:30%;"><input id="accNo" name="accNo" type="text" value="${clmsLoanBank.accNo}" style="width:90%;"/></td> 
		<td style="width:31%;"><div  id="accNoTip"  style="font-size:13px;color:red;"></div></td>
	</tr>
	<tr>
		<td style="width:15%;color:red">*开户行：</td>
		<td style="width:30%;">
			<select name="bank" id="bank" style="width:90%;" >
				<c:choose>
					<c:when test="${baseAccFlag=='1'}">
						<!-- 					基本户 -->
						<option <c:if test="${clmsLoanBank.bank=='104'}">selected="selected"</c:if> value ="104">中国银行</option>
						<option <c:if test="${clmsLoanBank.bank=='103'}">selected="selected"</c:if> value ="103">中国农业银行</option>
						<option <c:if test="${clmsLoanBank.bank=='102'}">selected="selected"</c:if> value ="102">中国工商银行</option>
						<option <c:if test="${clmsLoanBank.bank=='105'}">selected="selected"</c:if> value ="105">中国建设银行</option>
						<option <c:if test="${clmsLoanBank.bank=='104'}">selected="selected"</c:if> value ="104">交通银行</option>
						<option <c:if test="${clmsLoanBank.bank=='302'}">selected="selected"</c:if> value ="302">中信银行</option>
						<option <c:if test="${clmsLoanBank.bank=='303'}">selected="selected"</c:if> value ="303">中国光大银行</option>
						<option <c:if test="${clmsLoanBank.bank=='304'}">selected="selected"</c:if> value ="304">华夏银行</option>
						<option <c:if test="${clmsLoanBank.bank=='305'}">selected="selected"</c:if> value ="305">中国民生银行</option>
						<option <c:if test="${clmsLoanBank.bank=='306'}">selected="selected"</c:if> value ="306">广发银行</option>
						<option <c:if test="${clmsLoanBank.bank=='307'}">selected="selected"</c:if> value ="307">平安银行</option>
						<option <c:if test="${clmsLoanBank.bank=='308'}">selected="selected"</c:if> value ="308">招商银行</option>
						<option <c:if test="${clmsLoanBank.bank=='309'}">selected="selected"</c:if> value ="309">兴业银行</option>
						<option <c:if test="${clmsLoanBank.bank=='310'}">selected="selected"</c:if> value ="310">上海浦东发展银行</option>
						<option <c:if test="${clmsLoanBank.bank=='422'}">selected="selected"</c:if> value ="422">河北银行</option>
						<option <c:if test="${clmsLoanBank.bank=='440'}">selected="selected"</c:if> value ="440">徽商银行</option>
						<option <c:if test="${clmsLoanBank.bank=='434'}">selected="selected"</c:if> value ="434">天津银行</option>
						<option <c:if test="${clmsLoanBank.bank=='408'}">selected="selected"</c:if> value ="408">宁波银行</option>
					</c:when>
					<c:when test="${baseAccFlag=='0'}">
					<!-- 贷款户 -->
						<option <c:if test="${clmsLoanBank.bank=='104'}">selected="selected"</c:if> value ="104">中国银行</option>
						<option <c:if test="${clmsLoanBank.bank=='103'}">selected="selected"</c:if> value ="103">中国农业银行</option>
						<option <c:if test="${clmsLoanBank.bank=='102'}">selected="selected"</c:if> value ="102">中国工商银行</option>
						<option <c:if test="${clmsLoanBank.bank=='105'}">selected="selected"</c:if> value ="105">中国建设银行</option>
						<option <c:if test="${clmsLoanBank.bank=='1501'}">selected="selected"</c:if> value ="1501">江苏银行</option>
						<option <c:if test="${clmsLoanBank.bank=='310'}">selected="selected"</c:if> value ="310">上海浦东发展银行</option>
					</c:when>
				</c:choose>
			</select>
		</td> 
		<td style="width:31%;"><div  id="bankTip"  style="font-size:13px;color:red;"></div></td>
	</tr>  
	<tr>
		<td style="width:15%;;color:red">*分支行名称：</td>
		<td style="width:30%;"><input id="branchname" name="branchname" type="text" value="${clmsLoanBank.branchname}" style="width:90%;"/></td> 
		<td style="width:31%;"><div  id="branchnameTip"  style="font-size:13px;color:red;"></div></td>
	</tr>
	<tr>
		<td style="width:15%;color:red">*分支行省份：</td>
		<td style="width:30%;color:red"><input id="bankprovince" name="bankprovince" type="text"  value="${clmsLoanBank.bankprovince}" style="width:90%;"/></td> 
		<td style="width:31%;"><div  id="bankprovinceTip"  style="font-size:13px;color:red;"></div></td>
	</tr>  
	<tr>
		<td style="width:15%;color:red">*分支行城市：</td>
		<td style="width:30%;color:red"><input id="city" name="city" type="text"  value="${clmsLoanBank.city}" style="width:90%;"/></td> 
		<td style="width:31%;"><div  id="cityTip"  style="font-size:13px;color:red;"></div></td>
	</tr>
	<tr>
		<td colspan="4" style="height:35px;" align="center"> 
			<input id="saveBtn" class="button" type="button" value="保存" />
			<input id="submitBtn" class="button" type="button" value="提交" />
			<input name="closeBtn" class="button" type="button" onclick="ownerDialog.close();" value="取消" />
		</td>
	</tr>  
</table> 	 
</form>		
</body>
</html>
