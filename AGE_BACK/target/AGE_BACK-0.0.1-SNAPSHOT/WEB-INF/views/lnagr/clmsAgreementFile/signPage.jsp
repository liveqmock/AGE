<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
request.setAttribute("basePath", basePath);
%>
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
	<script type="text/javascript" src="${ctx}/scripts/common/common.js"></script>
</head>
<script>
function back(){
	window.location.href = "${ctx}/lnagr/lnagrHandle/lnagrHandleList";	
}

function sub(){
	$.ajax( {
		url : "${ctx}/lnagr/lnagrHandle/sub?id="+$("#id").val()+GetUrlConNum(),
		dataType : "json",
		success : function(data) {
			  if(data.isSuccess){ 
				   alert(data.message); 
				   $("#submitBtn").hide();
				   $("#returnBtn").hide();
				   back();
			   }else{
				   Dialog.alert(data.message); 
			   }
		}
	});
}

function rollbackToFirstStep(){
	Dialog.confirm('确定要退回吗?',function(){
		$.ajax( {
				url : "${ctx}/lnagr/lnagrHandle/rollbackToFirstStep?id="+$("#id").val()+GetUrlConNum(),
				dataType : "json",
				success : function(data) {
					  if(data.isSuccess){ 
						   alert(data.message); 
						   $("#submitBtn").hide();
						   $("#returnBtn").hide();
						   window.location.href = "${ctx}/lnagr/lnagrHandle/lnagrHandleList";
					   }else{
						   Dialog.alert(data.message); 
					   }
				}
			});
	})
}

</script>

<script type="text/javascript">
   	  //初始化
   	  function load(){
   	  	var object = document.getElementById("HWPostil1");
   	  	object.ShowScrollBarButton=0;//隐藏下面的状态栏
   	  	object.ShowFullScreen = 0;
   	  	object.ShowDefMenu = 1; //隐藏菜单栏0，1显示
   	  	object.ShowToolBar = 1; //隐藏工具栏0，1显示
   	  	object.Setvalue("SET_CURRENT_COOKIE", "xxx");//设置客户端cookies
   	  }
   	  //文字查找盖章
   	  function autoseal(){
   	    var objlogin=document.getElementById("HWPostil1").Login("",1,32767,"","");
            alert(objlogin);
   	    if(objlogin=="-200"){
   	  		alert("没有插入智能卡！");
   	    }
	      var num=document.getElementById("HWPostil1").PageCount;
	      var searchStr="经办人"; //要查找的文字
	      var set=document.getElementById("HWPostil1").SetValue("SET_SEAL_BMPDATA:50", "d:\\奥巴马签名.bmp" );//如果是图片的base64值则调用方式：SetValue("SET_SEAL_BMPDATA:50", "STRDATA:图片数据" )
   	    alert("set:"+set);
	      var s =document.getElementById("HWPostil1").AddQifengSeal(0,"AUTO_ADD:0,"+num+",0,0,255,"+searchStr+")|(0,","","AUTO_ADD_SEAL_FROM_PATH");//1,50000,2说明;x轴坐标，y轴坐标,找到第几个关键字
	      alert(s);
   	  }
   	  //坐标盖章
   	   function zbseal(searchstring){
   	    var objlogin=document.getElementById("HWPostil1").Login("",1,32767,"","");
        alert(objlogin);
   	    if(objlogin=="-200"){
   	  		alert("没有插入智能卡！");
   	    }
   	    var str=searchstring.split(":");
   	    var set=document.getElementById("HWPostil1").SetValue("SET_SEAL_BMPDATA:100","d:\\奥巴马签名.bmp" );//如果是图片的base64值则调用方式：SetValue("SET_SEAL_BMPDATA:50", "STRDATA:图片数据" )
   	    alert("set:"+set);
	      var s=document.getElementById("HWPostil1").AddQifengSeal(0,0+","+str[0]+",0,"+str[1]+",50,"+str[2],"","AUTO_ADD_SEAL_FROM_PATH");
	      alert(s);
   	  }
   	  //盖章
   	   function seal(){
   		  var obj = document.getElementById("HWPostil1");
	   	  if(obj.IsLogin())obj.Logout();
	   	  obj.Login("",1,32767,"","");
	   	  obj.CurrAction=2568;
   	  	 
       }
   	 
   	  //保存文档
   	  function save(){
   		upload(false);
   	  }
     //上传文档到服务器
     function upload(isSubmint){
       document.all.HWPostil1.HttpInit(); //初始化HTTP引擎。
	     document.all.HWPostil1.HttpAddPostString("name","test.pdf"); //设置上传变量文件名。
	     document.all.HWPostil1.HttpAddPostString("id","${id}"); //设置上传变量文件名。
	     document.all.HWPostil1.HttpAddPostCurrFile("agrFile");//设置上传当前文件,文件标识为FileBlod。
       var ispost=document.all.HWPostil1.HttpPost("${basePath}/lnagr/clmsAgreementFile/upload");//上传数据。
       if(ispost){
	       alert("上传成功");
	       var Object = document.getElementById("HWPostil1");
	       if(Object.IsLogin())Object.Logout();
	       if(isSubmint)
	    	   sub();
	     }else{
	       alert("上传失败");
	     }
      }
      //打开pdf文档
       function loaddoc(){  
        var load=document.getElementById("HWPostil1").LoadFile("${basePath}/lnagr/clmsAgreementFile/download?fileUrl=${fileUrl}&id=${id}");//也支持http地址，本地路径
       }
      
       $(document).ready(function() {
    	   loaddoc();
       });
</script>
<script type="text/javascript">
function download(){
	var diag = new Dialog();
	diag.Title = "";
	diag.Width = 800;
	diag.Height = 600;
	diag.URL = "${basePath}/lnagr/clmsAgreementFile/download?fileUrl=${fileUrl}&id=${id}";//"${ctx}/lnagr/clmsAgreementFile/download?fileUrl=${fileUrl}&id=${id}"; "${basePath}/scripts/signature/123.pdf"
	diag.show(); 
}
</script>
<body class="mainBody" onload="load();">
<form id="form">
<%--协议id--%>
<input id="id" name="id" type="hidden" value="${id}"/>
<%--文件url--%>
<input id="fileUrl" name="fileUrl" type="hidden" value="${fileUrl}"/>
<div class="form_title" >签署协议</div>  
<table class="table" style="width: 100%">
	<tr>
		<td colspan="2">
		<!-- <input id="submitBtn" class="button" type="button" value="提交" onclick="sub()" /> -->
		<input id="returnBtn" class="button" type="button" value="退回" onclick="rollbackToFirstStep()"/>
		<input id="backBtn" class="button" type="button" value="返回" onclick="back()" />
		</td>
		
	<td>
	<input name="button" width="500px" height="500px" type="button" value="打开文档" onclick="loaddoc();" />
	<input name="button" width="500px" height="500px" type="button" value="手动盖章" onclick="seal()" />
	<input name="button" width="500px" height="500px" type="button" value="自动文字盖章" onclick="autoseal()" />
	<input name="button" width="500px" height="500px" type="button" value="自动坐标盖章" onclick="zbseal('20000:20000:0')" />
	<input name="button" width="500px" height="500px" type="button" value="保存" onclick="save();" />
	<input name="button" width="500px" height="500px" type="button" value="上传到服务器并提交" onclick="upload(true);" />
	</td>
	</tr>
</table> 	 
</form>
	<table width="100%" height="100%">
		<tr>
			<TD align=center style="height:500px;">
				<!-- -----------------------------== 装载AIP控件 ==--------------------------------- -->
				<script type="text/javascript" src="${ctx}/scripts/signature/LoadHWPostil.js" ></script>
				<!-- -----------------------------== 结束装载控件 ==-------------------------------- -->
			</TD>
		</tr>
	</table>
</body>
</html>
