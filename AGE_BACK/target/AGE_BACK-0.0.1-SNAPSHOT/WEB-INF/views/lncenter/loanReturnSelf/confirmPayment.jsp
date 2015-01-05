<%@ page language="java" import="java.util.*" import="java.io.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title></title>
    <%@include file="/common/meta.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/common.css"/> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery-easyui/themes/icon.css">

	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery-easyui/locale/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.metadata.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/message_cn.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/zDialog/zDialog.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	function toPayPage(){			
	 	 	var diag = new Dialog();
			diag.Drag=false;
			diag.Title = "支付";
			diag.Width = 300;
			diag.Height = 80;
			diag.InnerHtml='<div style="text-align:center;color:black;font-size:14px;"><b>请您在新打开的页面进行支付，支付完成前请不要关闭该页面!</b></div>'
			diag.OKEvent = function(){
				diag.doReturn(true);
			};
			diag.CancelEvent=function(){
				diag.close();
			};
			diag.doReturn = function(){
				diag.close();
				ownerDialog.doReturn(true);
			}
			diag.show();
			diag.okButton.value="已完成支付";
			diag.cancelButton.value="支付遇到问题";
			
			document.form2.action="${returnData.paymentUrl}"; //${ctx}/pay/payTenderFile/createTradeRecord
			document.form2.target="_blank";
		    document.form2.submit();
	}		
	</script>
</head>

<body>
<form id="form2" name="form2" method="post" >
	<div id="body">
		<div class="easyui-panel" style="padding:5px 0 5px 1px;margin:5px 0 5px 0;border:none">
			<table>
					<tr>
						<td style="width:20%;"><input type="button" class="button-css" style="width:110px;height:25px" value='去汇润天下支付' onclick="toPayPage()">
												<br/><br/><br/>
						</td>
						<td style="width:80%;">
						<font style="color: #f00;font-size:12px">
							  * 汇润天下统一支付平台由中国银联旗下中金支付和江苏大润传感科技有限公司合作开发，请放心使用。
							  <br/>
                  			  * 请用户注意勿对同一保证金进行重复支付。
						</font>
						</td>
					</tr>
			</table>
		</div>
			<input type="hidden" class="textbox" name="fileDownloadFlag" id="fileDownloadFlag" value="${fileDownloadFlag}">
			<input type="hidden" name="searchFlg" id="searchFlg">
			
			
			
			 <input type="hidden" name="orderNo" value="${returnData.orderNo}"/>
             <input type="hidden" name="amount" value="${returnData.loanMoney}"/>
             <input type="hidden" name="usage" value="${returnData.fileType}"/>
             <input type="hidden" name="signature" value="${returnData.signature}"/>
             <input type="hidden" name="merid" value="${returnData.merId }"/>
             <input type="hidden" name="bider" value=""/>
             <input type="hidden" name="bidNum" value=""/>
             <input type="hidden" name="accountType" value="12">
             <input type="hidden" name="bankName" value="${enterprise.basicName}">
             <input type="hidden" name="paymentType" value="1">
             <input type="hidden" name="sysType" value="1">
             <input type="hidden" name="costType" value="${costType}">
              <input type="hidden" name="notificationUrl" value="${returnData.notificationUrl}">
			<div class="easyui-panel" title="确认支付" style="padding:5px; margin-bottom:10px;">
				<table>
					<tr>
						<td class="title" style="width:12%;">费用金额：</td>
						<td style="width:20%;">
							${returnData.loanMoney}
						</td>
						<td class="title" style="width:12%;">还贷订单号：</td>
						<td style="width:20%;">
							${returnData.orderNo}
						</td>
						<td class="title" style="width:12%;">费用类型：</td>
						<td style="width:20%;">
							${returnData.fileType}
						</td>
					</tr>
				</table>
			</div>
			<div class="easyui-panel" title="账户确认" style="padding:5px; margin-bottom:10px;">
				<table>
					<tr>
						<td class="title" style="width:12%;" colspan=2>支付银行账户信息(付款)</td>
						<td class="title" style="width:12%;" colspan=2>贷款银行账户信息(收款)</td>
					</tr>
					<tr>
						<td class="title" style="width:12%;">账户名称：</td>
						<td style="width:40%;">
						${returnData.bascBank.accName}
						</td>
						<td class="title" style="width:12%;">账户名称：</td>
						<td style="width:35%;">
						${returnData.loanBank.accName}
						</td>
					</tr>
					<tr>
						<td class="title" style="width:12%;">账户号码：</td>
						<td style="width:40%;">
						${returnData.bascBank.accNo}
						</td>
						<td class="title" style="width:12%;">账户号码：</td>
						<td style="width:35%;">
						${returnData.loanBank.accNo}
						</td>
					</tr><tr>
						<td class="title" style="width:12%;">开户行名称：</td>
						<td style="width:40%;">
						${returnData.bascBank.bank}
						</td>
						<td class="title" style="width:12%;">开户行名称：</td>
						<td style="width:35%;">
						${returnData.loanBank.bank}
						</td>
					</tr>
					<tr>
						<td class="title" style="width:12%;">分支行名称：</td>
						<td style="width:40%;">
						${returnData.bascBank.branchname}
						</td>
						<td class="title" style="width:12%;">分支行名称：</td>
						<td style="width:35%;">
						${returnData.loanBank.branchname}
						</td>
					</tr>
					<tr>
						<td class="title" style="width:12%;">分支行省份：</td>
						<td style="width:40%;">
						${returnData.bascBank.bankprovince}
						</td>
						<td class="title" style="width:12%;">分支行省份：</td>
						<td style="width:35%;">
						${returnData.loanBank.bankprovince}
						</td>
					</tr>
					<tr>
						<td class="title" style="width:12%;">分支行城市：</td>
						<td style="width:40%;">
						${returnData.bascBank.city}
						</td>
						<td class="title" style="width:12%;">分支行城市：</td>
						<td style="width:35%;">
						${returnData.loanBank.city}
						</td>
					</tr>
				</table>
			</div>
	</div>
	</form>
</body>
</html>
