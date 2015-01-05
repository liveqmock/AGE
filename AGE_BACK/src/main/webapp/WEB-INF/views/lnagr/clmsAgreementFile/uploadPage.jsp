<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
	<script type="text/javascript" src="${ctx}/scripts/ajaxFileUploader/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/scripts/ajaxfileupload.css" />
	<script type="text/javascript" src="${ctx}/scripts/ajaxFileUploader/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/common/common.js"></script>
</head>
<style type="text/css">
    
</style>
<script>
//对中文进行编码
function encode(encodeStr){
	 return encodeURIComponent(encodeURIComponent(encodeStr));
}

function back(){
	window.location.href = "${ctx}/lnagr/lnagrHandle/lnagrHandleList";	
}

function sub(){
	Dialog.confirm('确定要提交吗?',function(){
		$.ajax( {
			url : "${ctx}/lnagr/lnagrHandle/sub?id="+$("#id").val()+GetUrlConNum(),
			dataType : "json",
			success : function(data) {
				  if(data.isSuccess){ 
					   window.location ="${ctx}/lnagr/lnagrHandle/lnagrHandleList?message="+encode(data.message)+GetUrlConNum();
				   }else{
					   window.location ="${ctx}/lnagr/lnagrHandle/lnagrHandleList?message="+encode(data.message)+GetUrlConNum();
				   }
			}
		});
	})
}

function rollbackToFirstStep(){
	Dialog.confirm('确定要退回吗?',function(){
		$.ajax( {
				url : "${ctx}/lnagr/lnagrHandle/rollbackToFirstStep?id="+$("#id").val()+GetUrlConNum(),
				dataType : "json",
				success : function(data) {
					  if(data.isSuccess){ 
						   window.location ="${ctx}/lnagr/lnagrHandle/lnagrHandleList?message="+encode(data.message)+GetUrlConNum();
					   }else{
						   window.location ="${ctx}/lnagr/lnagrHandle/lnagrHandleList?message="+encode(data.message)+GetUrlConNum();
					   }
				}
			});
	})
}

/**************************文件上传 start************************************/
function upload(){
	if(validateImage()){
		 // 开始上传文件时显示一个图片
        $("#wait_loading").ajaxStart(function() {
            $(this).show();
        // 文件上传完成将图片隐藏起来
        }).ajaxComplete(function() {
            $(this).hide();
        });
		$.ajaxFileUpload({
	        url: '${ctx}/lnagr/clmsAgreementFile/upload', 
	        type: 'post',
	        secureuri: false, //一般设置为false
	        fileElementId: 'agrFile', // 上传文件的id属性名
	        data:{
	        	"fileName":$("#fileName").val(),
	        	"id":$("#id").val()
			},
	        dataType: 'text', //返回值类型，一般设置为json、application/json
	        success: function(data, status){
	        	if(data=='true'){
	        		sub();//提交
	        	}else{
	        		Dialog.alert("文件上传失败！请上传小于 10M的文件");
		            return false;
	        	}
	        },
	        error: function(data, status, e){ 
	             Dialog.alert("文件上传失败!");
	             return false;
	        }
	    });
	}
}

//校验图片格式 
function validateImage() {  
    var tmpFileValue = document.getElementById('agrFile').value;  
    if(tmpFileValue == ""){ 
        Dialog.alert("请选择上传的文件!");  
        return false;  
    }  
    //校验图片格式  
    if(/^.*?\.(pdf)$/.test(tmpFileValue.toLowerCase())){  
        return true;  
    } else {  
        Dialog.alert("只能上传pdf格式的文件！");  
        return false;  
    }  
} 
/**************************文件上传 end************************************/

function onswitch(){
	upload();//上传
}
</script>
<body class="mainBody">
<form id="form">
<%--协议id--%>
<input id="id" name="id" type="hidden" value="${id}"/>
<div class="form_title" >协议处理</div>  
<table class="table" style="width: 100%">
		<tr>
			<td style="width:100px;">文件名称：</td>
			<td >
				<input type="text" name="fileName" id="fileName"/>
			</td>
		</tr>
		<tr>
			<td style="width:100px;">文件文件：</td>
			<td style="padding-bottom:0px">
				<input type="file" name="agrFile" id="agrFile" />
				<span id="wait_loading" style="display:none;">
<%--		        <span style="width: 103px;"><img src="${ctx}/scripts/ajaxFileUploader/loading.gif"/></span>--%>
		        <span style="width: 103px;color:red;"><span>请稍等...</span></span>
				</span>
			</td>
		</tr>
	<tr>
		<td colspan="2">
		<input id="submitBtn" class="button" type="button" value="提交" onclick="onswitch()" />
		<input id="returnBtn" class="button" type="button" value="退回" onclick="rollbackToFirstStep()"/>
		<input id="backBtn" class="button" type="button" value="返回" onclick="back()" />
		</td>
	</tr>
</table> 	 
</form>		
</body>
</html>
