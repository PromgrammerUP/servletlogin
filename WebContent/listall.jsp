<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.javachina.login.dto.ShangpinDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品展示页面</title>
</head>
<body>
	<center>
	<font size="7" color="blue">商品展示</font>
	<hr color="red">
	<table width="100%" border="1" bordercolor="blue" cellspacing="0" cellpadding="5">
		<tr>
			<td>商品编号</td><td>商品名称</td><td>商品单价</td><td>商品产地</td><td>商品日期</td><td>商品类型</td><td>商品描述</td><td>操作</td>
		</tr>
	   <%
	   		ArrayList<ShangpinDto> all = (ArrayList)request.getAttribute("allShangpin");
	   		for(ShangpinDto dto: all){
	   	%>
	   			<tr>
	   				<td><%=dto.getId() %></td>
	   				<td><%=dto.getName() %></td>
	   				<td><%=dto.getPrice() %></td>
	   				<td><%=dto.getAddress() %></td>
	   				<td><%=dto.getInputDate() %></td>
	   				<td><%=dto.getLeixingName() %></td>
	   				<td><%=dto.getDescription() %></td>
	   				<td><a href="<%=request.getContextPath() %>/actionServlet?act=delete&&shangpinId=<%=dto.getId()%>">删除</a>
	   				<a href="<%=request.getContextPath() %>/actionServlet?act=getupdatepage&&shangpinId=<%=dto.getId()%>">更新</a></td>
	   			<tr>
		<%
	   		}
	   %>
	</table>
	</center>
</body>
</html>