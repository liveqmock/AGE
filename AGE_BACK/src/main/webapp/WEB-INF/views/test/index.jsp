<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/common/meta.jsp" %>    
	 
</head>
  <script>
	function drSend(){  
		 $.post("${ctx}/test/drSend.do");
	}
	function bocSend(){  
		 $.post("${ctx}/test/bocSend.do");
	}
</script>
  <body>
    <table style="width: 100%"> 
	<tr>
		<td style="height:35px;" align="center"> 
			<input type="button" onclick="drSend()" value="大润发送银行接收" />
			<input type="button" onclick="bocSend();" value="银行发送大润接收" />
		</td>
	</tr>  
</table> 	 
  </body>
</html>
