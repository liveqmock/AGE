
/* ---------------------------
 * 江苏CA控件操作，
 * 编写者：Liuy
 *---------------------------*/
function getVersion()
{
	 return  "2012-03-30"; 	
}

/*
 * 所有为null的情况，均使用此函数返回错误信息返回
 */
var errs=null;
function getJSCAErr()
{
	return  errs; 	
}

/*
 * 判断不为空的情况
 */
function unEmpty(s)
{
	if(typeof(s)!='undefined'&&s!=null&&s.length!=0)
	{
		return true;
	}
	else
	{
		return false;
	}
}


/*
 * 检测是否为IE浏览器，正确返回true，其他返回空
 */
function isIE()
{
	var Sys = {}; 
        var ua = navigator.userAgent.toLowerCase(); 
        if (window.ActiveXObject) 
            Sys.ie = ua.match(/msie ([\d.]+)/)[1] 
        else if (document.getBoxObjectFor) 
            Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1] 
        else if (window.MessageEvent && !document.getBoxObjectFor) 
            Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1] 
        else if (window.opera) 
            Sys.opera = ua.match(/opera.([\d.]+)/)[1] 
        else if (window.openDatabase) 
            Sys.safari = ua.match(/version\/([\d.]+)/)[1]; 
        
        //以下进行测试 
        if(Sys.ie) 
        {
			//alert('IE: '+Sys.ie); 
        	return true;
        }
	     if(Sys.firefox)
	     {
	     	errs+="本控件暂不支持：Firefox: "+Sys.firefox+",请使用IE浏览器！";
	     	return;
	     }
	    if(Sys.chrome) 
	    {
	    	errs+="控件暂不支持：chrome: "+Sys.chrome+",请使用IE浏览器！";
	     	return;
	    }
	     if(Sys.opera) 
	     {
	     	errs+="控件暂不支持：Opera: "+Sys.opera+",请使用IE浏览器！";
	     	return;
	     }
	     if(Sys.safari)
	     {
	     	errs+="控件暂不支持：Safari: "+Sys.safari+",请使用IE浏览器！";
	     	return;
	     }
	     else
	     {
	     	errs+="控件暂不支持,请使用IE浏览器！";
	     	return;
	     }
}


/*
 *一般配合证书助手使用，无需自动加载cab包
 *如果IE中只有一张证书，会选择唯一的证书
 *
 */
function signerXIni(fingerprint,pin) 
{
	//判断是否为IE
//	if(isIE())
//	{
		try{
			var signerX = new ActiveXObject("SignerX.FormSigner.1"); 
			signerX.AutoSign=0;//手动签名
			signerX.Quiet=1;
			signerX.oem=1;
//		signerX.CFSubjectContains="997";//主题项中包含的证书
//		signerX.CFKeyUsage=0x01;//必须含有私钥
//		signerX.CFIssuerContains="JSCA_CA";//只显示"JSCA_CA"颁发的证书			
			var finger;
			var cs = kdemo_signerx_vb2js_array(signerX.AvailableCertificates);
	 		var length=cs.length;
	 		if(length==0)
		  {
		  		errs="证书为空，请确认证书是否导入";
		  		return;
		  }
		  if(length==1)
		  {
			  	var c = kdemo_signerx_vb2js_array(cs[0]);
			  	//证书指纹
			  	finger=c[0];
		  }
		  if(unEmpty(pin))
			{
				 signerX.PIN=pin;
			}
		  if(unEmpty(fingerprint))
			{
					signerX.PreferredCertificate=fingerprint;
			}
			else
			{
					if(unEmpty(finger))
					{
						  signerX.PreferredCertificate=finger;
					}
			}
			return signerX;
		}
		catch(err)
		{
				errs="signerXIni_ActiveXObject:"+err.description+"\n";
				errs+="1 如未下载证书助手，请下载并安装\n2 如已下载，请检测并修复 \n3 如仍未解决请联系江苏CA客服 025-96010";
				return;
		}
//	}
//	else
//	{
//		return;
//	}
}

/* 
 * 嵌入试加载证书
 * ieQrZhengshu需要在页面上加载此div，例如：<div id="ieQrZhengshu" ></div>
 */
function ieQrZhengshu() 
{
	var activex = signerXIni();
	if (activex) 
  	{
	    var cs = kdemo_signerx_vb2js_array(activex.AvailableCertificates);
	    var length=cs.length;
	    if(length==0)
	    {
	    		errs="证书为空，请确认证书是否导入";
		  		return;
	    }
	    else
	    {
	    	var s="<select name='select' class='bttn' id='select' style='width:350px;'  onclick=jzCertInfo() >";
		    for (n in cs) 
		    {
		       	 var c = kdemo_signerx_vb2js_array(cs[n]);
		       	 s+="<option value="+c[0]+">"+c[2]+"</option>";
		    }
		    s+="</select>";
		    document.all.ieQrZhengshu.innerHTML=s;
			//填写对应内容
			jzCertInfo();
	    }
  	}
}	

function kdemo_signerx_vb2js_array(v) 
{
	var j = (new VBArray(v)).toArray();
	return j;
}

/* 
 * 加载证书内容
 * 
 */
function jzCertInfo() 
{
	 //获取证书对应指纹：
	 var vl=document.all.select.value;
	 var activex = signerXIni();
	 var ic=activex.GetCertificate(vl);
	 var subject=ic.Subject;
	 var xm=subject.getOneKey("G",",");
	 document.all.xm.value=xm;
	 document.all.zszt.value=subject;
	 document.all.zsxlh.value=ic.SerialNumber;
}

/*
 *解析参数值
 *key:需要获取的参数
 *fenge：参数之间的分割符号，"&"为默认分割符号
 */
String.prototype.getOneKey=function(key,fenge)
{
	var ls;
	try{		
		if(unEmpty(fenge))
		{
			ls=this.toLowerCase().split(fenge);
		}
		else
		{
			ls=this.toLowerCase().split("&");
		}
		for(var i=0;i<ls.length;i++)
		{
			 var sub=ls[i].split("=");
			 var keyL=sub[0].replace(" ","");
			 var val=sub[1].replace(" ","");
			 sub[1].replace(" ","");
			 if(keyL==key.toLowerCase())
			 {
			 	return val;
			 }
		}
	}
	catch(e)
	{
		return;
	}
}

/*
 *判断序列化中的字符串是否为空
 *fenge：参数之间的分割符号，"&"为默认分割符号
 */
String.prototype.getSerializeUnEmpty=function(fenge)
{
	var ls;
	try{	
		if(unEmpty(fenge))
		{
			ls=this.toLowerCase().split(fenge);
		}
		else
		{
			
			ls=this.toLowerCase().split("&");
		}
		for(var i=0;i<ls.length;i++)
		{
			 var sub=ls[i].split("=");
			 var keyL=sub[0].replace(" ","");
			 var val=sub[1].replace(" ","");
			 if(unEmpty(val))
			 {
			 	 continue;
			 }
			 else
			 {
			 	return  false;
			 }
		}
		return true;
		
	}
	catch(e)
	{
		return false;
	}
}

/* 
 * 实现pkcs签名,签名算法均使用sha1
 * 1  pkcstype   pkcs的类型
 * 2  inputdata  签名内容
 * 3  fingerprint  证书指纹
 */
function pkcssign(pkcstype,inputdata,fingerprint,pin) 
{
	var svsRu=null;
	var signerX=signerXIni(fingerprint,pin);
	if(unEmpty(signerX))
	{
		try
		{
				if(pkcstype=="p1")
				{
					var i = signerX.PKCS1(inputdata, "sha1"); 
					if (i == 0) 
					{
						svsRu   =   new   Object(); 
						svsRu.p = signerX.SXSignature;//签名信息
						svsRu.c = signerX.SXCertificate;//签名证书
						svsRu.d = signerX.SXInput;//签名原文
						svsRu.s=signerX;//signerX对象
						return svsRu;
					}
					else 
					{
							errs="pkcs1Sign_signerX_PKCS1："+signerX.GetErrorString(i)+"\n";
							errs+="1 点击：工具->Internet选项->内容->证书->个人，查看证书是否正确导入\n";	
							errs+="2 请注意检查输入的签名数据是否为null或其他无效字符";	
							return null;
					}	
				}
				if(pkcstype=="p7")
				{
					var i = signerX.PKCS7(inputdata, "sha1"); 
					if (i == 0) 
					{
						svsRu = signerX.SXSignature;//签名信息
						return svsRu;
					}
					else 
					{
							errs="pkcs7Sign_signerX_PKCS7："+signerX.GetErrorString(i)+"\n";
							errs+="1 点击：工具->Internet选项->内容->证书->个人，查看证书是否正确导入\n";		
							errs+="2 请注意检查输入的签名数据是否为null或其他无效字符";	
							return null;
					}	
				}
				else
				{
					 errs="pkcssign_pkcstype:请检查签名的类型是否正确";
					 return null;
				}
		}
		catch(err)
		{
					errs="pkcs1Sign_signerX:"+err.description+"\n";
					errs+="请注意检查输入的签名数据是否为null或其他无效字符";		
					return null;
		}
	}
}


/*
 *实现pkcs1签名
 */
function pkcs1Sign(inputdata,fingerprint) 
{
	 var svsRu=pkcssign("p1",inputdata,fingerprint);
	 return  svsRu; 
}

/*
 *实现pkcs1签7
 */
function pkcs7Sign(inputdata,fingerprint,pin) 
{
	 var svsRu=pkcssign("p7",inputdata,fingerprint,pin);
	 return  svsRu; 
}

/*
 *实现文件签名
 */
function file_Sign(filePath,fingerprint) 
{
	var signerX=signerXIni(fingerprint);
	if(unEmpty(signerX))
	{
		var fileSha1=signerX.SHA1File(filePath);
		alert(fileSha1);
		if(unEmpty(fileSha1))
		{
			var p7=pkcs7Sign(fileSha1);
			alert(p7);
			return  p7; 
		}
		else
		{
			errs="file_Sign_SHA1File：请检查文件路径是否正确\n";
			return null;
		}
	}
	else
	{
		return  null; 
	}
}


/*
 * 页面中加载，在不使用助手的情况的下，实现自动加载
 * 不建议使用
 */
function signerXIni2(fingerprint)
{
//	document.write("<OBJECT id='signerx' classid='CLSID:5C457383-C43E-4F0F-BACD-8CAD3CE597C5' CODEBASE='/cab/SignerX.cab#Version="+signeXIni+"' VIEWASTEXT width='0'height='0'>");
//	document.write("</object>");
	//如使用	document.write有问题时，使用div的动态写入
	var a="<OBJECT id='signerx' classid='CLSID:5C457383-C43E-4F0F-BACD-8CAD3CE597C5'  CODEBASE='/cab/SignerX.cab' VIEWASTEXT width='0'height='0'>";
	a+="<param name='AutoSign' value='0'>";
	a+="<param name='CertificateFilter' value='0'>";
	if(fingerprint!=undefined)
	{
		a+="<param name='PreferredCertificate' value="+fingerprint+">";
	}
	a+="<param name='Quiet' value='1'>";
	a+="<param name='Debug' value='0'>";
	a+="</OBJECT>";
}

/*
 * 跳转之前检测控件是否正常安装，通常用于SSL与SVS同时使用时，
 * 在首页检测控件是否正常
 */
function checkSignerX(url) 
{
		var b = signerXIni();
		if(b!=null)
		{
			window.location.href=url;
		}
		else
		{
			return null;
		}
}

/*
 * 在页面中初始化calibx
 * 不建议使用
 */
function calibXIni2()
{
	var a="";
	//签名控件
	a+="<object classid='clsid:13BAEB81-CE23-40EE-911A-04550079D820' id='caSign' ></object>";
	//XML报文
	a+="<object classid='clsid:4C09916C-F86B-4CB2-B563-80B7A2B4FE21' id='caXML' ></object>";
	//验证
	a+="<object classid='clsid:DB61A0CF-C62F-454F-AA74-9235C8A08EDB' id='caVerify' ></object>";
	//加解密
	a+="<object classid='clsid:F11BC838-0D56-47F4-8984-99325F03B6BB' id='caCrypt' ></object>";
	//证书相关
	a+="<object classid='clsid:5CFC9864-E612-41D0-B564-F7872562F5D7' id='caCertificate' ></object>";
	calibXIniDiv.innerHTML=a;
}

/*
 * 初始化caSign
 */
function caSign()
{
	var caSign=null;
	try
	{
			caSign = new ActiveXObject("CaLib.CASign.1"); 
			return caSign;
	}
	catch(err)
	{
			errs="caSign_ActiveXObject:"+err.description+"\n";
			errs+="1 如未下载证书助手，请下载并安装\n2 如已下载，请检测并修复 \n3 如仍未解决请联系江苏CA客服 025-96010";
			return null;
	}
}

/*
 * 初始化caXML
 */
function caXML()
{
	var ob=null;
	try
	{
			ob = new ActiveXObject("CaLib.CAXML.1"); 
			return ob;
	}
	catch(err)
	{
			errs="caXML_ActiveXObject:"+err.description+"\n";
			errs+="1 如未下载证书助手，请下载并安装\n2 如已下载，请检测并修复 \n3 如仍未解决请联系江苏CA客服 025-96010";
			return null;
	}
}

/*
 * 初始化caVerify
 */
function caVerify()
{
	var ob=null;
	try
	{
			ob = new ActiveXObject("CaLib.CAVerify.1"); 
			return ob;
	}
	catch(err)
	{
			errs="caVerify_ActiveXObject:"+err.description+"\n";
			errs+="1 如未下载证书助手，请下载并安装\n2 如已下载，请检测并修复 \n3 如仍未解决请联系江苏CA客服 025-96010";
			return null;
	}
}

/*
 * 初始化caCrypt
 */
function caCrypt()
{
	var ob=null;
	try
	{
			ob = new ActiveXObject("CaLib.CACrypt.1"); 
			return ob;
	}
	catch(err)
	{
			errs="caCrypt_ActiveXObject:"+err.description+"\n";
			errs+="1 如未下载证书助手，请下载并安装\n2 如已下载，请检测并修复 \n3 如仍未解决请联系江苏CA客服 025-96010";
			return null;
	}
}


/*
 * 初始化caCertificate
 */
function caCertificate()
{
	var ob=null;
	try
	{
			ob = new ActiveXObject("CaLib.CACertificate.1"); 
			return ob;
	}
	catch(err)
	{
			errs="caCertificate_ActiveXObject:"+err.description+"\n";
			errs+="1 如未下载证书助手，请下载并安装\n2 如已下载，请检测并修复 \n3 如仍未解决请联系江苏CA客服 025-96010";
			return null;
	}
}


/*
 * 初始化calibx
 * 1 initype 
 *    1 初始化  caSign   
 *    2 初始化  caXML
 *    3 初始化  caVerify
 *    4 初始化  caCrypt
 *    5 初始化  caCertificate
 */
function calibXIni(initype)
{
	var calibx=null;
	if(initype==1)
	{
		calibx=caSign();
		if(calibx!=null)
		{
			 calibx.Debug_=0;
			 return calibx;	
		}
		else
		{
			return null;	
		}
	}
	if(initype==2)
	{
		calibx=caXML();
		if(calibx!=null)
		{
			calibx.Debug_=0;
			return calibx;			
		}
		else
		{
			return null;	
		}
	}
	if(initype==3)
	{
		calibx=caVerify();
		if(calibx!=null)
		{
			calibx.Debug_=0;
			return calibx;	
		}
		else
		{
			return null;	
		}
	}
	if(initype==4)
	{
		calibx=caCrypt();
		if(calibx!=null)
		{
			calibx.Debug_=0;
			return calibx;	
		}
		else
		{
			return null;	
		}
	}
	if(initype==5)
	{
		calibx=caCertificate();
		if(calibx!=null)
		{
			calibx.Debug_=0;
			return calibx;	
		}
		else
		{
			 return null;	
		}
	}
	else
	{
		 errs="calibXIni初始化参数错误，请注意查看文档说明！";
		 return null;	
	}
}

//加密
function gtEncryptStr(encdata)
{
		var  ob=calibXIni(4);
		var enc=null;
		if(unEmpty(ob))
		{
				if(encdata!=undefined&&encdata!="")
				{
					try
					{
							var i=ob.gtEncryptStr(encdata);
							if(i==0)
							{
								var b64SessionKey=ob.B64SessionKey;	
								//加密结果
								var b64Encrypted=ob.B64Encrypted;
								//将加密结果拼接，利用存储	
								enc =b64SessionKey+'|'+b64Encrypted; 
								return enc; 
							}
							else
							{
								 	 errs="gtEncryptStr："+ob.gtShowErrMsg()+"\n";
								 	 errs+="点击：工具->Internet选项->内容->证书->个人，查看证书是否正确导入\n";
								 	 errs+="检查导入的证书是否含有加密证书";
								 	 return null;
							}
					}
					catch(err)
					{
							errs="gtEncryptStr_ActiveXObject:"+err.description+"\n";
							errs+="1 如未下载证书助手，请下载并安装\n2 如已下载，请检测并修复 \n3 如仍未解决请联系江苏CA客服 025-96010";
							return null;
					}
				}
				else
				{
					  errs="gtEncryptStr：请检查参数是否输入为空";
					  return null;
				}
		}
	  else
		{
			 return null;
		}
}

//解密
function gtDecryptStr(encdata)
{
	var  ob=calibXIni(4);
	if(ob!=null)
	{
			if(unEmpty(encdata))
			{			
					var ls=encdata.split("|");
					var b64SessionKey=ls[0];
					var b64Encrypted=ls[1];
					ob.B64SessionKey=b64SessionKey;
					var dec=ob.gtDecryptStr(b64Encrypted);
					if(dec==0)
					{
						 return ob.StrData;
					}
					else
					{
						 errs="gtDecryptStr:"+dec+":"+ob.gtShowErrMsg()+"\n";
						 return null;
					}
			}
			else
			{
					errs="gtDecryptStr：请检查参数是否输入为空";
					return null;
			}
	}
 else
 {
 		return null;
 }	
}


/*
	获取指定的URL参数值
	URL:http://www.blogjava.net/blog?name=bainian
	参数：paramName URL参数
	调用方法:getParam("name")
	返回值:bainian
*/
function getParam(paramName)
{
        paramValue = "";
        isFound = false;
        if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=")>1)
        {
            arrSource = unescape(this.location.search).substring(1,this.location.search.length).split("&");
            i = 0;
            while (i < arrSource.length && !isFound)
            {
                if (arrSource[i].indexOf("=") > 0)
                {
                     if (arrSource[i].split("=")[0].toLowerCase()==paramName.toLowerCase())
                     {
                        paramValue = arrSource[i].split("=")[1];
                        isFound = true;
                     }
                }
                i++;
            }   
        }
   return paramValue;
}
