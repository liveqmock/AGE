<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script>var _ctx="${ctx}";</script>
<script type="text/javascript" src="${ctx}/scripts/jquery/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ca/Load_DJ_CA.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登录</title>
    <link href="<%=basePath%>styles/controls.css" type="text/css" rel="stylesheet" />
    <link href="<%=basePath%>styles/common.css" type="text/css" rel="stylesheet" />
    <style type="text/css">
    *{padding:0px;margin:0px;}
	.clear{clear:both;}
	body{background:url(<%=basePath%>images/bg-stripe.gif) 0px 200px repeat-x;background-color:#0d1d3e;}
	.head{/*标题*/height:130px;padding:55px 0px 0px 75px;
		font:bold 50px "黑体";color:#8a9ca8;}
	/*登录框架样式*/
	.frame .headl{width:16px;height:100%;float:right;
		background:url(<%=basePath%>images/frame-headl.gif) 0px 27px no-repeat;}
	.frame .headcon{height:100%;float:right;
		background:url(<%=basePath%>images/frame-headc.gif) 0px 27px repeat-x;background-color:#fff;}
	.frame .frametitle{height:37px;padding:35px 0px 0px 20px;
		font:bold 30px "黑体";color:#8a9ca8;}
	.frame .framecontent{font-size:14px;}
	.frame .headr{width:15px;height:100%;float:right;background:#fff;} 
    
	#errorinfo{width:100%;height:70px;overflow:hidden;
		margin-top:100px;
		font:bold 18px "宋体";color:#fff;text-align:right;
		}
	.drinfo{height:30px;text-align:right;
		padding:35px 0px 0px 0px;
		font:bold 12px "宋体";
		color:#8a9ca8;
		}
	.logontable{width:330px;height:120px;
		}
	</style>
	<script language="JavaScript">
		if (top.location !== self.location) {
			top.location=self.location;
		}
		
	  <!--
		function doLogin(){ 
			document.loginForm.action="<%=basePath%>userLogin";
			document.loginForm.submit();
		}
	  
	  function setLoginType(){
			if(document.getElementById("loginTypeCa").checked){
				loginCA();
			}else{
				loginOrdinary();
			}
		}
	  function loginCA(){
		  var success = SelectCertificateOnClick();
		  if(success){
			  var obj = GetCertInfoOnClick();
			  if(obj != null){
				  var signInfo = sign("<%=request.getSession().getId()%>");
				  document.getElementById("username").value = obj.name;
				  document.getElementById("userpwd").value = obj.serialNumber;
				  document.getElementById("caInfo").value = signInfo;
				  doLogin();
			  }else{
				  alert('获取证书信息失败');
				  return;
			  }
		  }
		  
	  }
	  function loginOrdinary(){
		  document.getElementById("username").value = "";
		  document.getElementById("userpwd").value = "";
		  document.getElementById("caInfo").value = "";
	  }
	  
	  //-->
   </script>
</head>
<body>
<div id="FakeCryptoAgent"></div>
<form name="loginForm" method="post" action="<%=basePath%>userLogin">
	<div class="head">▶▶大润信贷管理系统</div>
	<div class="frame" style="height:260px;float:right;">
    	<div class="headr" style="margin-right:110px;"></div>
        <div class="headcon" style="width:350px;">
        	<div class="frametitle">登录</div>
            <div class="framecontent" style="overflow:hidden;color:#0d1d3e;margin:10px auto auto 30px;">
            <!-- table，实现用户登录操作 -->
            	<table align="left" class="logontable">
            		<tr>
            			<td colspan="2">
            				<input type="radio" onclick="setLoginType()"  name="loginType" value="notCa" id="loginTypeOrdinary" checked="checked"/>
							<label for="loginTypeOrdinary">普通登录</label>
							<input type="radio" onclick="setLoginType()"  name="loginType" id="loginTypeCa" value="ca" />
							<label for="loginTypeCa">CA登录</label>
							<input type="hidden" id="caInfo" name="caInfo" value=""/>
	                        </td>
	                    </tr>
                	<tr>
                    	<td style="width:60px;">用户名：</td>
                    	<td>
                        	<input id="username" name="username" type="text" class="textbox1" />
                        </td>
                    </tr>
                	<tr>
                    	<td>密码：</td>
                    	<td>
                        	<input id="userpwd" name="userpwd" type="password" class="textbox1" />
                        </td>
                    </tr>
                	<tr style="visibility:hidden;"><!--若要显示，改为visible-->
                    	<td>验证码：</td>
                    	<td></td>
                    </tr>
                </table>
                <div style="width:100%;clear:both;text-align:center;">
                	<input id="" name="" type="submit" class="button1"  value="登 录"/>
                	<input id="" name="" type="reset" class="button1"  value="重设"/> 
                </div>
            </div>
        </div>
    	<div class="headl"></div>
    </div>
    <div style="width:550px;float:right;margin-right:-10px;">
        <div class="drinfo" style="padding:200px 0px 0px 0px;" align=center>
        <br/>
        	企业首次使用本系统，请点击 <a href="<%=basePath %>lncenter/clmsUserReg/regist" style="color: red;font-size:20px">备案</a> 进行信息注册。<br style="height:10px;"/><br/>
    <div>   
    <div style="width:230px;float:right;margin-right:10px;">
    	<div id="errorinfo" style="visibility:visible"><!--不显示时，值为hidden--> 
    	${message}
        </div>
        <div class="drinfo">
            江苏大润传感科技有限公司&nbsp;版权所有<br />
            http://www.drcgw.com
        </div>
    </div>
</form>
</body>
</html>

