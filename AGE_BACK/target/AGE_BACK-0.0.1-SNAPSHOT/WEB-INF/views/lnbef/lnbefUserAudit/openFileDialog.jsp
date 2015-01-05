<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/taglibs.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/base.css"/> 
 </head>
  <c:choose>
  <c:when test="${not empty filePath}">
  <img height="550" width="750" alt="备案审核附件" src="${ctx}/lnbef/lnbefUserAudit/showFile?filePath=${filePath}">
  </c:when>
  <c:otherwise>
  文件不存在！
  </c:otherwise>
  </c:choose>
<body>  
</body>
</html>
