<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信贷管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/jquery/json2.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/jquery/jquery.jsoncookie.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/jquery-easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>scripts/jquery-easyui/locale/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="<%=basePath%>scripts/zDialog/zDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/zDialog/zDrag.js"></script>
<link href="<%=basePath%>styles/common.css" type="text/css" rel="stylesheet" />
<script>    
$(function(){ 
	$('#centerTab').tabs({
		fit:true, 
		onClose:function(title){
			delCookieTab(title); 
		},
		tools:[{
			iconCls:'icon-reload',
			handler: function(){ 
				refreshSelectedTab();
				}
			},{
				iconCls:'icon-remove',
				handler:function(){
					closeAllTabs();
				}
			}
		]
	});  
	initCookieTab();
	//initMenus();
});

function closeAllTabs(){
	clearCookieTab();
	top.location.href="<%=basePath%>index"; 
	
	
}
//删除cookie
function clearCookieTab() {
	$.cookie("tab_cookie", null, { path: '/' });
}

function initCookieTab(){   
	var cookie_val = $.JSONCookie("tab_cookie");//读取json格式cookie 
	if (typeof (cookie_val.tab_list) == "undefined") {//判断cookie是否存在
		cookie_val = { "tab_list": []}; 
		$.JSONCookie("tab_cookie", cookie_val, { path: '/', expires: 1 });//存储json格式cookie
	}else { 
		//showAllCookieTab(); //关闭刷新打开tab
	}
}
var i=0;
var timer;
function showAllCookieTab(){  
	 var cookie_val = $.JSONCookie("tab_cookie");
	 var len = cookie_val.tab_list.length;
	 if(i < len){
		 var tabId = cookie_val.tab_list[i].tabId;
		 var title = cookie_val.tab_list[i].title;
		 var url = cookie_val.tab_list[i].url;  
		 addTab(false,tabId,title,url,false); 
		 //addTab(tabId,title,url,false); 
		 
		 i++;
		 timer = setTimeout(showAllCookieTab,1000);
	 } 
} 
function addCookieTab(_tabId,_title,_url){  
	var cookie_val = $.JSONCookie("tab_cookie"); 
	var _tab = {tabId:_tabId,title:_title,url:_url};
    cookie_val.tab_list.push(_tab); 
    $.JSONCookie("tab_cookie", cookie_val, { path: '/', expires: 1 }); 
} 
function delCookieTab(_title){  
	var cookie_val = $.JSONCookie("tab_cookie");
	for(var i=0; i<cookie_val.tab_list.length; i++){ 
		var title = cookie_val.tab_list[i].title;  
		if(_title == title){
			cookie_val.tab_list.splice(i, 1);
		}
	}
	$.JSONCookie("tab_cookie", cookie_val, { path: '/', expires: 1 }); 
}

function refreshSelectedTab(){  
     var refresh_tab = $('#centerTab').tabs('getSelected');  
     if(refresh_tab && refresh_tab.find('iframe').length > 0){  
	     var _refresh_ifram = refresh_tab.find('iframe')[0];  
	     var refresh_url = _refresh_ifram.src;  
	     _refresh_ifram.contentWindow.location.href=refresh_url;  
     }  
} 
function addTab(obj,tabId,title,url,addCookieFlag){ 
	if(obj){
		//$("h3").css("background-color","white");
		//$(obj).parent().css("background-color","#1984df");
	}
	if($("#"+tabId).html()==null){
		var name = 'iframe_'+tabId;  
		var iframe = '<iframe name="'+name+'" id="'+tabId+'" src="'+url+'" width="100%" height="100%" style="position:relative;" frameborder="0" ></iframe>';
	 	$('#centerTab').tabs('add',{
			title: title,         
			closable:true,
			cache : false,  
			content : iframe
		});   
		
		if(addCookieFlag == false){
			
		}else{ 
			addCookieTab(tabId,title,url)
		}
	}else{
		$("#centerTab").tabs('select',title);
	}
	
}
/*function addTab(tabId,title,url,addCookieFlag){ 
	if($("#"+tabId).html()==null){
		var name = 'iframe_'+tabId;  
		var iframe = '<iframe name="'+name+'" id="'+tabId+'" src="'+url+'" width="100%" height="100%" style="position:relative;" frameborder="0" ></iframe>';
	 	$('#centerTab').tabs('add',{
			title: title,         
			closable:true,
			cache : false,  
			content : iframe
		});   
		
		if(addCookieFlag == false){
			
		}else{ 
			addCookieTab(tabId,title,url)
		}
	}else{
		$("#centerTab").tabs('select',title);
	}
	
}*/

function userExit(){ 
 	$.post("<%=basePath%>userExit",null,
		    function(data) {
		        top.location.href="<%=basePath%>index"; 
		    } 
		 ); 
}

function editPwd(){ 
 	var diag = new Dialog();
	diag.Width = 310;
	diag.Height = 170;
	diag.Title = "修改密码";
	diag.URL = "<%=basePath%>sysmng/sysUser/editPwd";
	diag.doReturn = function(data){
		if(data.isSuccess){  
			diag.close();
			Dialog.alert("修改密码成功");
	    }else{
		    Dialog.alert(data.message); 
	    }
	}; 
	diag.show(); 
}
function initMenus(){
	$.ajax({
		type: "post",
		url : "<%=basePath%>sysmng/sysMenu/getMenus",
		dataType : "json",
		success : function(data) {
			  if(data!=null){ 
				  $(data).each(function(i,val) {
					  var div = "";
					  if(val.rootModule!=null){
						  if(val.children!=null){
							  $(val.children).each(function(j,vals) { 
								  div += "<h3  style=\"color:#0099FF;padding-left:30px;;font-size:12px\"><a href=\"javascript:void(0)\" onclick=\"addTab(this,'"+vals.id+"','"+vals.displayName+"','"+'<%=basePath%>'+vals.url+"');return false;\">"+vals.displayName+"</a></h3><p></p>";
							  })
						  }
						  $('#accordion').accordion('add',{
				                title:val.rootModule.displayName,
				                id:val.rootModule.id,
				                content:div,
				                selected: i==0?true:false
				          });
					  }  
				  })
			  }
		}
	});
}
function tree_node(node){
	var s = node.text;
	if (node.children){
	 	//s += '&nbsp;<span style=\'color:blue\'>(' + node.children.length + ')</span>';
	}else{
		s = "<a href=\"javascript:void(0)\" onclick=\"addTab(this,'"+node.id+"','"+node.text+"','"+'<%=basePath%>'+node.url+"');return false;\">"+node.text+"</a>";
	}
	return s;
}
</script>
</head>
<body class="easyui-layout">
	<div region="north" style="height:100%;overflow:hidden;">
		 <div class="headc"><!--标题部分div-->
            <div style="width:100%;height:30px;overflow:hidden;">
                <div class="tips">
                    <input type="button" class="exit" onclick="userExit()" value="退出系统" />
                    <input type="button" class="pwd"  onclick="editPwd()" value="修改密码" />
                    <div class="user">
                        欢迎您，<label>${username}</label>
                    </div>
                </div>
                <div class="tips-l"></div>
            </div>
        	<div class="logo">江苏大润电子信贷管理系统</div>
        </div>
	</div> 
	<div region="west" split="true" title="<div style='text-align: center;'>导航菜单</div>" style="width:240px;padding1:1px;overflow:auto;"> 
		 <ul id="tt1" class="easyui-tree" data-options="url:'<%=basePath%>sysmng/sysMenu/getMenus2?timestamp=<%=new Date().getTime() %>',
		 method:'get',animate:true,formatter:tree_node">
		 <%--	<li>
				<span>信贷前台</span>
				<ul> 
					<li> 
						<a href="javascript:void(0)" onclick="addTab('tabId_311','新贷款申请','<%=basePath%>lncenter/clmsLoanApply/loanApplyNew');return false;">新贷款申请</a>
					</li>  
					
					<li> 
						<a href="javascript:void(0)" onclick="addTab('tabId_411','新贷款申请进度查询','<%=basePath%>lncenter/loanApplySchedule/search');return false;">新贷款申请进度查询</a>
					</li>  
					
					<li> 
						<a href="javascript:void(0)" onclick="addTab('tabId_611','自行还款','<%=basePath%>lncenter/loanReturnSelf/list');return false;">自行还款</a>
					</li>  
					
					<li> 
						<a href="javascript:void(0)" onclick="addTab('tabId_511','现有贷款查询','<%=basePath%>lncenter/existingLoanSearch/search');return false;">现有贷款查询</a>
					</li>  
				</ul>
			</li> 
			
			<li>
				<span>贷前管理</span>
				<ul> 
					<li> 
						<a href="javascript:void(0)" onclick="addTab('tabId_10','首次线上备案初审','<%=basePath%>lnbef/lnbefUserAudit/list');return false;">首次线上备案初审</a>
					</li> 
					<li> 
						<span>企业银行信息维护</span>
						<ul>
							<li>
								<a href="javascript:void(0)" onclick="addTab('tabId_11_1','银行账户信息维护(经办)','<%=basePath%>lnbef/bankAccountReg/list');return false;">银行账户信息维护(经办)</a>
							</li>
							<li>
								<a href="javascript:void(0)" onclick="addTab('tabId_11_2','银行账户信息维护(复核)','<%=basePath%>lnbef/bankAccountAudit/list');return false;">银行账户信息维护(复核)</a>
							</li>
						</ul>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_12','新贷款申请审核','<%=basePath%>lnbef/loanApplyAudit/list');return false;">新贷款申请审核</a>
					</li>  
				</ul>
			</li> 
			<li>
			
				<span>贷中管理</span>
				<ul> 
					<li> 
						<a href="javascript:void(0)" onclick="addTab('tabId_16','贷款发起','<%=basePath%>lnmid/clmsLoanRec/list');return false;">贷款发起</a>
					</li> 
					<li>
						<span>贷款拨划</span>
						<ul>
							<li>
								<a href="javascript:void(0)" onclick="addTab('tabId_17_1','放贷划拨（手动）','<%=basePath%>lnmid/clmsLoanOut/list');return false;">放贷划拨（手动）</a>
							</li>
							<li>
								<a href="javascript:void(0)" onclick="addTab('tabId_17_2','还贷划拨（自动）','<%=basePath%>lnmid/clmsLoanReturn/list');return false;">还贷划拨（自动）</a>
							</li>
						</ul>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_18','放贷监控','<%=basePath%>lnmid/clmsLoanMoney/list?bizType=0');return false;">放贷监控</a>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_19','还贷监控','<%=basePath%>lnmid/clmsLoanMoney/list?bizType=1');return false;">还贷监控</a>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_20','贷款业务明细查询','<%=basePath%>lnmid/clmsLoanRecSearch/loanBusinessDetailSearch');return false;">贷款业务明细查询</a>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_21','贷款订单明细查询','<%=basePath%>lnmid/clmsLoanRecSearch/loanOrderDetailSearch');return false;">贷款订单明细查询</a>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_22','自行还款明细查询','<%=basePath%>lnmid/clmsLoanRecOwn/list');return false;">自行还款明细查询</a>
					</li> 
				</ul>
			</li> 
			<li>
				<span>贷后管理</span>
				<ul> 
				</ul>
			</li> 
			<li>
				<span>协议管理</span>
				<ul> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_201','协议管理','<%=basePath%>lnagr/lnagrMgmt/lnagrMgmtList');return false;">协议管理</a>
					</li>
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_204','协议处理','<%=basePath%>lnagr/lnagrHandle/lnagrHandleList');return false;">协议处理</a>
					</li> 
				</ul>
			</li>  
			<li>
				<span>接口管理</span>
				<ul> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_101','接口发送','<%=basePath%>itf/send/list');return false;">接口发送</a>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_102','接口接收','<%=basePath%>itf/rec/list');return false;">接口接收</a>
					</li> 
				</ul>
			</li>  
			<li>
				<span>系统管理</span>
				<ul> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_2','机构管理','<%=basePath%>sysmng/sysOrg/list');return false;">机构管理</a>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_3','用户管理','<%=basePath%>sysmng/sysUser/list');return false;">用户管理</a>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_4','角色管理','<%=basePath%>sysmng/sysRole/list');return false;">角色管理</a>
					</li>
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_5','资源管理','<%=basePath%>sysmng/sysResource/list');return false;">资源管理</a>
					</li>  
					<li> 
						<a href="javascript:void(0)" onclick="addTab('tabId_1','参数管理','<%=basePath%>sysmng/sysParam/list');return false;">参数管理</a>
					</li> 
					
					<li> 
						<a href="javascript:void(0)" onclick="addTab('tabId_9','系统字典管理','<%=basePath%>sysmng/sysDict/list');return false;">系统字典管理</a>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_6','公告管理','<%=basePath%>sysmng/sysBulletin/list');return false;">公告管理</a>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_7','流程模板管理','<%=basePath%>sysmng/sysTempFlow/list');return false;">流程模板管理</a>
					</li> 
					<li>
						<a href="javascript:void(0)" onclick="addTab('tabId_8','环节模板管理','<%=basePath%>sysmng/sysTempStep/list');return false;">环节模板管理</a>
					</li> 
				</ul>
			</li>  --%>
		</ul>    
		<div id="accordion" class="easyui-accordion"  data-options="border:false">
		</div>
	</div>
	<div region="center" title="" style="overflow:hidden;">
		<div class="easyui-tabs" id="centerTab" fit="true" border="false">
			<div title="欢迎页" style="padding:20px;overflow:hidden;"> 
				 <!-- 
				<iframe src="<%=basePath%>trade/mesSend/list" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>
				  -->
			</div>
		</div>
	</div>
</body>
</html>
