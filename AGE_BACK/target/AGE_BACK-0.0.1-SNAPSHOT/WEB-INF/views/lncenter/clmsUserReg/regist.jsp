<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link href="<%=basePath%>styles/controls.css" type="text/css" rel="stylesheet" />
    <link href="<%=basePath%>styles/common.css" type="text/css" rel="stylesheet" />
    <title>备案</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.6.4.min.js"></script>
	<script src="<%=basePath%>scripts/formValidator/formValidator-4.1.1.js" type="text/javascript"></script> <!--表单验证必须库-->
	<script src="<%=basePath%>scripts/formValidator/formValidatorRegex.js" type="text/javascript"></script> <!--表单验证扩展库-->
	<script type="text/javascript" src="<%=basePath%>/scripts/zDialog/zDialog.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/zDialog/zDrag.js"></script>
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
	.frame .frametitle{height:37px;padding:12px 0px 0px 20px;
		font:bold 30px "黑体";color:#8a9ca8;}
	.frame .framecontent{font-size:14px;}
	.frame .headr{width:15px;height:100%;float:right;background:#fff;} 
    
	#errorinfo{width:100%;height:70px;overflow:hidden;
		margin-top:100px;
		font:bold 18px "宋体";color:#fff;text-align:right;
		}
	.drinfo{height:30px;text-align:right;
		padding:55px 0px 0px 0px;
		font:bold 13px "宋体";
		color:#8a9ca8;
		}
	.regtable{width:620px;
				font-size:13px;
		}
	.regtable td{padding-top:5px;}
	.regtable td p{float:right;}
	.regtable td p.req{color:red}
	.regtable td input{width:200px; height:20px;}
	</style>
	
	<script> 
	$(document).ready(function(){	
		$.formValidator.initConfig({ formID: "registForm"})
		$("#entName").formValidator({ onShow:"请填写与上传证件一致的企业名称",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"企业名称不能为空"});
		$("#entLegalName").formValidator({ onShow: "请填写与上传证件一致的法人名称",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"法人名称不能为空"}).regexValidator({ regExp: "realname", dataType: "enum", onError: "请输入由20个英文字母或10个汉字组成的法人代表" });
		$("#entOrgNo").formValidator({ onShow: "请填写与上传证件一致的组织机构代码", onFocus:"",onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"组织机构代码不能为空"}).ajaxValidator({  
	        type : "post",  
	        cache : false, 
	        addidvalue: true,
	        async:false,
	        url : "<%=basePath%>lncenter/clmsUserReg/checkEntOrgNoUnique",  
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
	        onError : "您注册的组织机构代码已存在",  
	        onWait : "正在对组织机构代码进行校验..."  
	    }).defaultPassed();  
		$("#businessLicense").formValidator({ onShow: "请上传不超过2M的jpg/jpeg格式的企业营业执照",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,onError:"企业营业执照扫描件不能为空"}).regexValidator({ regExp: "certificate", dataType: "enum", onError: "扫描件只能是大小不超过2M的jpg/jpeg格式的图片" });
		$("#taxRegCertificate").formValidator({ onShow: "请上传不超过2M的jpg/jpeg格式的税务登记证",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,onError:"税务登记证扫描件不能为空"}).regexValidator({ regExp: "certificate", dataType: "enum", onError: "扫描件只能是大小不超过2M的jpg/jpeg格式的图片" });
		$("#entOrgNoCertificate").formValidator({ onShow: "请上传不超过2M的jpg/jpeg格式的组织机构代码证",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,onError:"组织机构代码证扫描件不能为空"}).regexValidator({ regExp: "certificate", dataType: "enum", onError: "扫描件只能是大小不超过2M的jpg/jpeg格式的图片" });
		$("#bankAccountLicence").formValidator({ onShow: "请上传不超过2M的jpg/jpeg格式的银行开户许可证",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,onError:"银行开户许可证扫描件不能为空"}).regexValidator({ regExp: "certificate", dataType: "enum", onError: "扫描件只能是大小不超过2M的jpg/jpeg格式的图片" });
		$("#userName").formValidator({ onShow: "请填写经办人电子邮箱",onFocus: "请注意你输入的email格式，例如:drcgw@drcgw.com", onCorrect: "格式正确" }).inputValidator({min:1,onError:"经办人电子邮箱不能为空"}).regexValidator({ regExp: "email", dataType: "enum", onError: "邮箱格式不正确" }).ajaxValidator({  
	        type : "post",  
	        cache : false,  
	        addidvalue: true,
	        async:false,
	        url : "<%=basePath%>lncenter/clmsUserReg/checkUserNameUnique",  
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
	        onError : "您注册的电子邮箱已存在",  
	        onWait : "正在对电子邮箱进行校验..."  
	   
	    }).defaultPassed();  
		$("#entHandleName").formValidator({ onShow: "请填写企业经办人",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"经办人不能为空"}).regexValidator({ regExp: "realname", dataType: "enum", onError: "请输入由20个英文字母或10个汉字组成的经办人" });
		$("#entHandleMobile").formValidator({ onShow: "请输入经办人11位手机号码",onFocus:"", onCorrect: "格式正确" }).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"请不要以空字符开始或结尾！"},onError:"经办人手机号码不能为空"}).regexValidator({ regExp: "mobile", dataType: "enum", onError: "请输入正确的手机号码" });
		
		$("#regist").click(function(){
			if($.formValidator.pageIsValid()){
				Dialog.confirm('请确认以上备案信息，大润工作人员审核后会及时联系到您！',function(){
					$("#registForm").submit();
				})
			}
		})
		
		if($("#result").val()!=""){
			Dialog.alert($("#result").val());
		}
	})
	</script>
  </head>
  
  <body>
    <div class="head">▶▶大润电子交易平台</div>
	<div class="frame" style="width:750px;height:450px;float:right;margin-top:0px;">
    	<div class="headr" style="margin-right:20px;"></div>
        <div class="headcon" style="width:650px;">
        	<div style=" padding-top: 2px;text-align: right;"><a style="text-decoration : none" href="<%=basePath%>login">返回</a></div>
        	<div class="frametitle">企业首次备案信息注册</div>
            <div class="framecontent" style="overflow:hidden;color:#0d1d3e;margin:10px auto auto 10px;">
                <form id="registForm" name="registForm" method="post" action="<%=basePath%>lncenter/clmsUserReg/saveRegist" enctype="multipart/form-data">
	            	<table align="left" class="regtable" border="0">
	                	<tr>
	                    	<td style="width:150px;"><p class="req">*企业名称：</p></td>
	                    	<td >
	                        	<input id="entName" type="text" name="entName" maxlength="100" value="${clmsUserRegVO.entName}" style="width:150px;"/>
	                        </td>
	                        <td>
	                        	<div id="entNameTip"  style="font-size:13px;color:red;width:300px;"></div>
	                        </td>
	                    </tr>
	                    <tr>
	                    	<td style="width:150px;"><p class="req">*法人代表：</p></td>
	                    	<td >
	                        	<input id="entLegalName" type="text" name="entLegalName" maxlength="100" value="${clmsUserRegVO.entLegalName}" style="width:150px;"/>
	                        </td>
	                        <td>
	                        	<div id="entLegalNameTip"  style="font-size:13px;color:red;width:300px;"></div>
	                        </td>
	                    </tr>
	                    <tr>
	                    	<td style="width:150px;"><p class="req">*组织机构代码：</p></td>
	                    	<td >
	                    		<input id="entOrgNo" type="text" name="entOrgNo" maxlength="15" value="${clmsUserRegVO.entOrgNo}" style="width:150px;"></input>
	                    	</td>
	                        <td>
	                    		<div  id="entOrgNoTip"  style="font-size:13px;color:red;width:300px;"></div>
	                    	</td>
	                    </tr>
	                    <tr>
	                    	<td style="width:150px;"><p class="req">*营业执照扫描件：</p></td>
	                    	<td >
	                        	<input id="businessLicense" name="businessLicense"  type="file" value="" onkeydown="return false" onkeyup="return false" oncontextmenu="return false" style="width:150px;"/>
	                        </td>
	                        <td>
	                        	<div id="businessLicenseTip"  style="font-size:13px;color:red;width:300px;"></div>
	                        </td>
	                    </tr>
	                    <tr>
	                    	<td style="width:150px;"><p class="req">*税务登记证扫描件：</p></td>
	                    	<td >
	                        	<input id="taxRegCertificate" name="taxRegCertificate"  type="file" value="" onkeydown="return false" onkeyup="return false" oncontextmenu="return false" style="width:150px;" />
	                        </td>
	                        <td>
	                        	<div  id="taxRegCertificateTip"  style="font-size:13px;color:red;width:300px;"></div>
	                        </td>
	                    </tr>
	                    <tr>
	                    	<td style="width:150px;"><p class="req">*组织机构代码证扫描件：</p></td>
	                    	<td >
	                        	<input id="entOrgNoCertificate" name="entOrgNoCertificate"  type="file" value="" onkeydown="return false" onkeyup="return false" oncontextmenu="return false" style="width:150px;"/>
	                        </td>
	                        <td>
	                        	<div id="entOrgNoCertificateTip"  style="font-size:13px;color:red;width:300px;"></div>
	                        </td>
	                    </tr>
	                    <tr>
	                    	<td style="width:150px;"><p class="req">*银行开户许可证扫描件：</p></td>
	                    	<td >
	                        	<input id="bankAccountLicence" name="bankAccountLicence"  type="file" value="" onkeydown="return false" onkeyup="return false" oncontextmenu="return false" style="width:150px;"/>
	                        </td>
	                        <td>
	                        	<div  id="bankAccountLicenceTip"  style="font-size:13px;color:red;width:300px;"></div>
	                        </td>
	                    </tr>
	                    <tr>
	                    	<td style="width:150px;"><p class="req">*经办人：</p></td>
	                    	<td >
	                        	<input id="entHandleName" type="text" name="entHandleName" maxlength="12"  value="${clmsUserRegVO.entHandleName}" style="width:150px;"/>
	                        </td>
	                        <td>
	                        	<div  id="entHandleNameTip"  style="font-size:13px;color:red;width:300px;"></div>
	                        </td>
	                    </tr>
	                    <tr>
	                    	<td style="width:150px;"><p class="req">*经办人电子邮箱：</p></td>
	                    	<td >
	                        	<input id="userName" type="text" name="userName" maxlength="50"  value="${clmsUserRegVO.userName}" style="width:150px;"/>
	                        </td>
	                        <td>	
	                        	<div  id="userNameTip"  style="font-size:13px;color:red;width:300px;"></div>
	                        </td>
	                    </tr>
	                    <tr>
	                    	<td style="width:150px;"><p class="req">*经办人手机号码：</p></td>
	                    	<td >
	                        	<input id="entHandleMobile" type="text" name="entHandleMobile" maxlength="22" value="${clmsUserRegVO.entHandleMobile}"  style="width:150px;"/>
	                        </td>
	                        <td>
	                        	<div  id="entHandleMobileTip"  style="font-size:13px;color:red;width:300px;"></div>
	                        </td>
	                    </tr>
	                </table>
	                <div style="width:100%;clear:both;padding-top:10px;margin-left:162px;">
	                	<input id="regist" type="button" class="button1"  value="注册" />
	                	<input  type="reset" class="button1"  value="重置" /> 
	                </div>
	                <input id="result" type="hidden" value="${message}"/>
                </form>
            </div>
        </div>
    	<div class="headl"></div>
    </div>
    <div style="width:250px;float:right;margin-right:10px;">
    	<div id="errorinfo" style="visibility:visible"><!--不显示时，值为hidden--> 
    	
        </div>
        <div class="drinfo">
            江苏大润传感科技有限公司&nbsp;版权所有<br />
            http://www.drcgw.com
        </div>
    </div>
    
  </body>
</html>