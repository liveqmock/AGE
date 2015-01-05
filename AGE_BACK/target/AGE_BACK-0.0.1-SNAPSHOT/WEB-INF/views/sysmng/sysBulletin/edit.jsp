<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>   
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDialog.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/validate.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/ckeditor/ckeditor.js"></script>
</head>
<script>
$(document).ready(function(){
	if($("#result").val()!=""){
		Dialog.alert($("#result").val());
	}
	if("${ empty size ?0:size}">0){
		$("#attachmentList").show();
		$("#btnClear").show();
	}
})


/**
需要注意的几个地方：
.由于input type=file控件不能对type属性赋值(in IE),所以采用插入HTML代码的方式(insertAdjacentHTML)；
.input type=file控件的value是只读的，不能被赋值；
.因为用一个input type=file控件有缺陷，就是当用户删除了最后一次添加的文件再添加同样的文件则无法触发onchange事件；
并且要上传文件，只用一个上传控件是不够的；
.客户端无法获取文件大小，除非用FSO或ActiveX等。除了img控件，其fileSize可以在图片文件加载完成后获取其文件大小；
.还有另外一种变通的方法就是用flash与js交互达到获取客户端文件大小的效果(现在flash在安全方面也做了限制)；
*/
var deleteId = [];
var i = "${ empty size ?0:size}"; // 用来动态生成span,upfile的id
function addAttachmentToList(obj) {
    //如果添加了同一个目录下的文件 只做提示
    if (findAttachment($(obj).val())){
    	Dialog.alert("您添加了同一个附件"); 
    }
    //如果file控件的值是空或者‘’（file的value值被删除）
    //删除attachmen对应的span
    if ($(obj).val() == null || $(obj).val() == '') {
        var j = obj.id.substr(7);
        $('#_attachment' + j).remove();
        return;
    }

    //如果attachmentlist存在下标是j的元素，删除
    var j = obj.id.substr(7);
    if (G('_attachment' + j)) {
        $('#_attachment' + j).remove();
    }

    // 动态创建附件信息栏并添加到附件列表中
    var span = document.createElement('span');

    span.id = '_attachment' + j;
    span.innerHTML = extractFileName($(obj).val()) + '&nbsp;<a href="javascript:delAttachment(' + (j) + ')"><font color="blue">删除</font></a><br/>';
    span.title = $(obj).val();
    G('attachmentList').appendChild(span);

    // 显示附件列表并变换添加附件按钮文本
    if (G('attachmentList').style.display == 'none') {
        G('btnAdd').value = '继续添加';
        G('attachmentList').style.display = '';
        G('btnClear').style.display = '';
    }
    G('total').innerText = '当前选择上传' + G('attachmentList').childNodes.length + '个附件';
}

function selectAttachment() {
 /**如果flielist span长度-1==attachmentList span长度  
 * 说明有一个file控件，不能再继续添加file控件
 **/
    var attachment = G('attachmentList').childNodes;
    var file = $("#fileList span");
    if (attachment.length == file.length - 1) {
        Dialog.alert("请选择您要添加的附件");
        return;
    }

    // 动态创建上传控件并与span对应
    var upfile = '<span id="_file' + i + '"><input type="file" onchange="addAttachmentToList(this);" name="_upfile" id="_upfile' + i + '"></br></span>';
    i++;
    $("#fileList").show();
    $("#fileList").append(upfile);
    //G('_upfile'+i).click();
}

//获得filename 
function extractFileName(fn) {
    return fn.substr(fn.lastIndexOf('\\') + 1);
}

//是否添加了同一个目录下的文件 
function findAttachment(fn) {
    var o = G('attachmentList').getElementsByTagName('span');
    for (var i = 0; i < o.length; i++) if (o[i].title == fn) return true;
    return false;
}

function delAttachment(id) {
    if ("${empty size ?0:size}" > 0 && id < "${empty size ?0:size}" + 1) {
        deleteId.push($("#_attachment" + id).attr("tempid"));
    }
    $(G('_attachment' + id)).remove();
    $("#_file" + id).remove();
    // 当附件列表为空则不显示并且变化添加附件按钮文本
    if (G('attachmentList').childNodes.length == 0) {
        G('btnAdd').value = '添加附件';
        G('attachmentList').style.display = 'none';
        G('btnClear').style.display = 'none';
    }

    G('total').innerText = '当前选择上传' + G('attachmentList').childNodes.length + '个附件';
}


//清空控件
function clearAttachment() {
 	//attachmentList删除
    var o = G('attachmentList').childNodes;
    for (var i = o.length - 1; i >= 0; i--) {
        deleteId.push($(o[i]).attr("tempid"));
        $(o[i]).remove();
    }
    //fileList删除
    o = G('fileList').childNodes;
    for (var i = o.length - 1; i >= 0; i--) {
        $("#_file" + i).remove(); //$(o[i]).remove();
    }

    G('btnAdd').value = '添加附件';
    G('attachmentList').style.display = 'none';
    G('btnClear').style.display = 'none';

    G('total').innerText = '当前选择上传0个附件';

}

//根据id获得obj
function G(id) {
    return document.getElementById(id);
}

	
	
	
	function save(){ 
		var pageType = $("#pageType").val();
		
		var title = $("#title").val();
		if(isEmpty(title)){
		 	Dialog.alert('请输入公告标题！');return;
		 }
		var editor_data = CKEDITOR.instances.content.getData();
		//console.debug(editor_data);
		if(isEmpty(editor_data)){
		 	Dialog.alert('请输入公告内容！');return;
		 }
		if(pageType=='edit'){
			var id = $("#id").val();
			var orderNo = $("#orderNo").val();
			if(isEmpty(orderNo)){
			 	Dialog.alert('请输入顺序号！');return;
			 }else if(!isDigit($("#orderNo").val())){
				 Dialog.alert('顺序号是数字！');return;
			 }
			
		}
		$("#deleteId").val(deleteId);
		Dialog.confirm("您确定要保存吗？",function(){
			$("#form").submit();
		});
		//ownerDialog.doReturn(true);
		
	}
</script>
<style >
table {width:100%; font-size:12px;text-align:left;border:1px solid #ccc; border-collapse:collapse; }
table td {border:1px solid #ccc ;line-height:22px;   padding:0px 2px 0px 0px; }
</style>
<body class="mainBody">
<form id="form" method="post" action="${ctx}/sysmng/sysBulletin/save" enctype="multipart/form-data">
<input id="id" name="id" type="hidden" value="${sysBulletin.id}" />
<input id="deleteId" name="deleteId" type="hidden"  />
<%--编辑 1--%>
<input id="pageType" name="pageType" type="hidden" value="${pageType}" />
	<input type="button" class="button" value="添加附件" id="btnAdd" onclick="selectAttachment();">&nbsp;
	<input type="button" class="button" value="清空附件" id="btnClear" style="display:none" onclick="clearAttachment();">
	<div id="attachmentList" style="margin:3px 0px 0px 0px;padding:4px 3px 4px 3px;display:none;border:0px;">
		<c:forEach items="${list}" varStatus="i" var="item" >  
			<span id="_attachment${i.index}" tempid="${item.id}">${item.fileName}&nbsp;<a href="javascript:delAttachment('${i.index}')"><font color="blue">删除</font></a><br/></span>
		</c:forEach>
	</div>
	<div id="fileList" style="margin:3px 0px 0px 0px;padding:4px 3px 4px 3px;display:none;border:0px;">
	<c:forEach items="${list}" varStatus="i" var="item" >  
<%--	value="1" 标识 已经添加的文件 <br/>--%>
			<span id="_file${i.index}"><input  type="file" style="display:none" onchange="addAttachmentToList(this);" name="_upfile" id="_upfile${i.index}"></span>
		</c:forEach>
	</div>
	<div id="total" style="margin:3px 0px 0px 0px;">当前选择上传${ empty size ?0:size}个附件</div>
<table  style="width: 100%">
	<tr>
		<td style="width:5%;color:red;">*公告标题：</td>
		<td style="width:30%;"><input id="title" name="title" type="text" value="${sysBulletin.title}" style="width:90%;" maxlength="50"/></td> 
	</tr>  
	<c:if test="${pageType=='edit'}">
		<tr>
			<td style="width:5%;color:red;">*顺序号：</td>
			<td style="width:30%;"><input id="orderNo" name="orderNo" type="text" value="${sysBulletin.orderNo}" style="width:90%;" maxlength="8"/></td> 
		</tr>  
	</c:if>
	<tr>
		<td style="width:5%;color:red;">*公告内容：</td>
		<td style="width:30%;">
		<textarea id="content" name="content" >${sysBulletin.content}</textarea>
		<script>

		CKEDITOR.replace( 'content');
		</script>

		</td> 
	</tr>  
	<tr>
		<td colspan="4" style="height:35px;" align="center"> 
			<input name="saveBtn" class="button" type="button" onclick="save()" value="保存" />
			<input name="closeBtn" class="button" type="button" onclick="ownerDialog.close();" value="关闭" />
			<input id="result" type="hidden" value="${message}"/>
		</td>
	</tr>  
</table> 	 
</form>		
</body>
</html>
