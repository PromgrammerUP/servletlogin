<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	欢迎<%=request.getParameter("userId") %>登陆
	<hr color="bule">
	<a href="<%=request.getContextPath() %>/actionServlet?act=listall">展示所有商品</a><br>
	<a href="<%=request.getContextPath() %>/actionServlet?act=getaddpage">增加新商品</a>
</body>
</html>