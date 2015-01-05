<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<link href="<%=basePath%>styles/controls.css" type="text/css"
			rel="stylesheet" />
		<link href="<%=basePath%>styles/common.css" type="text/css"
			rel="stylesheet" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>scripts/jquery-easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>scripts/jquery-easyui/themes/icon.css">
		<title>信贷</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet"
			href="<%=basePath%>styles/jquery.multiselect.css" type="text/css"></link>
		<link rel="stylesheet" href="<%=basePath%>styles/jquery-ui.css"
			type="text/css"></link>
		<script type="text/javascript"
			src="<%=basePath%>scripts/jquery/jquery-1.6.4.min.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/jquery/jquery-ui.min.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/jquery/jquery.multiselect.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/jquery/jquery.validate.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/jquery/jquery.metadata.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/jquery/message_cn.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/jquery-easyui/jquery.easyui.min.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/jquery-easyui/locale/easyui-lang-zh_CN.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/zDialog/zDialog.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/zDialog/zDrag.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/jquery/underscore-min.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/util/validate.js">
</script>

		<script type="text/javascript">

$(function() {
	var enterpriseTypes = [];
	$.each(enterpriseTypes, function(index, data) {
		if (_.contains(currentUserEnterpriseTypeArr, data.key)) {
			$("#enterpriseType").append(
					"<option value='" + data.key + "'>" + data.value
							+ "</option>")
		}
	})

	$("input[type=radio]").click(function() {
		if ($(this).val() == '1') {
			$("#private").hide();
		} else {
			$("#private").show();
		}
	});

	$("input[type!=checkbox],.ui-multiselect").focus(function() {
		if ($(this).hasClass("ui-multiselect")) {
			$(this).next().next().html("");
		} else {
			$(this).next().html("");
		}
	});

	$("#saveBtn").click(function() {

		var accountType = $("input[type=radio]:checked").val();

		if (accountType == "2" && privateFormValid()) {
			return;
		}

		var formData = $("#privateForm").serialize()

		$.ajax( {
			type : "POST",
			url : "<%=basePath%>/ca/bind/save",
			data : formData,
			dataType : 'json',
			success : function(data) {
				$.messager.alert('系统提示', data.message);
				if (data.isSuccess) {
					document.getElementById("privateForm").reset();
				}
			}
		});
	});

	$("#resetBtn").click(function() {
		document.getElementById("privateForm").reset();
	});

	$("#privateUserName").blur(function() {
		if (!$(this).val()) {
			return;
		}
		if (!isCardNo($(this).val())) {
			$(this).next().html("请输入正确的15位或18位身份证号码");
		}
	});

	$("#privatePassWord")
			.blur(
					function() {
						var val = $(this).val();
						var flag = (/^[a-zA-Z0-9]+$/.test(val) && /(^(.*\d+.*[a-zA-Z]+.*)$)|(^(.*[a-zA-Z]+.*\d+.*)$)/
								.test(val));
						if (!flag || val.length < 6) {
							$(this).next().html("密码必须包含数字、字母组合，长度至少为6");
						} else {
							$(this).next().html("");
						}
					});

	$("#companyPassWord")
			.blur(
					function() {
						var val = $(this).val();
						var flag = (/^[a-zA-Z0-9]+$/.test(val) && /(^(.*\d+.*[a-zA-Z]+.*)$)|(^(.*[a-zA-Z]+.*\d+.*)$)/
								.test(val));
						if (!flag || val.length < 6) {
							$(this).next().html("密码必须包含数字、字母组合，长度至少为6");
						} else {
							$(this).next().html("");
						}
					});

	$("#privateCpassWord").blur(function() {
		if ($(this).val() != $("#privatePassWord").val()) {
			$(this).next().html("确认密码不正确");
		} else {
			$(this).next().html("");
		}
	});

	var privateFormValid = function() {
		var validateFlag = false;
		if (!$("#privateName").val()) {
			validateFlag = true;
			$("#privateName").next().html("请输入账号姓名");
		}

		if (!$("#telephone").val()) {
			validateFlag = true;
			$("#telephone").next().html("请输入手机号码");
		}

		if (!$("#userName").val()) {
			validateFlag = true;
			$("#userName").next().html("请输入个人名称");
		}
		if (!$("#phone").val()) {
			validateFlag = true;
			$("#phone").next().html("请输入固话号码");
		}

		if (!$("#privatePassWord").val()) {
			validateFlag = true;
			$("#privatePassWord").next().html("请输入密码");
		}
		if (!$("#orderNo").val()) {
			validateFlag = true;
			$("#orderNo").next().html("请输入顺序号");
		}
		var privatePassWord = $("#privatePassWord").val();
		var flag = (/^[a-zA-Z0-9]+$/.test(privatePassWord) && /(^(.*\d+.*[a-zA-Z]+.*)$)|(^(.*[a-zA-Z]+.*\d+.*)$)/
				.test(privatePassWord));
		if (!flag || privatePassWord.length < 6) {
			$("#privatePassWord").next().html("密码必须包含数字、字母组合，长度至少为6");
			validateFlag = true;
		}

		if (!$("#privateCpassWord").val()) {
			validateFlag = true;
			$("#privateCpassWord").next().html("请输入确认密码");
		}

		if ($("#privateCpassWord").val() != $("#privatePassWord").val()) {
			validateFlag = true;
		}

		return validateFlag;
	}

})
</script>
		<style>
table {
	font-size: 12;
}

.caStyle {
	width: 209px;
}
</style>
	</head>
	<body>
		<div id="body" style="margin-left: 8px">
			<div class="easyui-layout" style="width: 100%; height: 300px;">
				<div data-options="region:'center',title:'CA绑定'"
					style="height: 200px;">
					<div>
						<input name="accountType" type="radio" value="2" checked="checked"
							style="width: 20px">
						账号基本信息
					</div>
					<div>
						<form id="privateForm">
							<table id="private">
								<tr>
									<td style="color: red">
										用户账号：
									</td>
									<td>
										<input id="privateName" class="caStyle" name="accountName">
										<span style="color: red"></span>
									</td>
								</tr>

								<tr>
									<td style="color: red">
										用户名称：
									</td>
									<td>
										<input id="userName" class="caStyle" name="userName">
										<span style="color: red"></span>
									</td>
								</tr>

								<tr>
									<td style="color: red">
										手机：
									</td>
									<td>
										<input id="telephone" class="caStyle" name="telephone">
										<span style="color: red"></span>
									</td>
								</tr>

								<tr>
									<td style="color: red">
										固定电话：
									</td>
									<td>
										<input id="phone" class="caStyle" name="phone">
										<span style="color: red"></span>
									</td>
								</tr>



								<tr>
									<td style="color: red">
										顺序号：
									</td>
									<td>
										<input id="orderNo" class="caStyle" name="orderNo">
										<span style="color: red"></span>
									</td>
								</tr>

								<tr>
									<td style="color: red">
										密码：
									</td>
									<td>
										<input id="privatePassWord" class="caStyle" type="password"
											name="passWord">
										<span style="color: red"></span>
									</td>
								</tr>
								<tr>
									<td style="color: red">
										确认密码：
									</td>
									<td>
										<input id="privateCpassWord" class="caStyle" type="password">
										<span style="color: red"></span>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
			<div class="easyui-panel"
				style="padding: 5px; margin: 10px 0 10px 0;">
				<a id="saveBtn" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'">保存</a>
				<a id='resetBtn' href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-back'" style="margin-left: 10px;">重置</a>
			</div>
		</div>
	</body>
</html>