<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>微信test</title>
    <base href="<%=basePath%>">

    <title>微信公共平台开发者接口</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <script type="text/javascript" src="jquery-1.11.0.min.js"></script>
    <script language="JavaScript" type="text/javascript">

        </script>
</head>
<body>
 <%--    success
     code=${sessionScope.code}
     state=${sessionScope.state}--%>
     <a href="<%=basePath%>payTotalServlet?code=${sessionScope.code}">点一点</a>
</body>
</html>
