<%@page import="org.javachina.login.dto.LeixingDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品增加</title>
</head>
<body>
	<center>
	<font size="7" color="blue">增加商品</font>
	<hr color="red">
	<form action="<%=request.getContextPath() %>/actionServlet" method="get" >
	<input type="hidden" name="act" value="adddo">
		<table border="1" bordercolor="blue">
			<tr><td>商品名称</td><td><input type="text" name="name" size="35"></td></tr>
			<tr><td>商品单价</td><td><input type="text" name="price" size="35"></td></tr>
			<tr><td>商品产地</td><td><input type="text" name="address" size="35"></td></tr>
			<tr><td>入库时间</td><td><input type="text" name="inputDate" size="35"></td></tr>
			<tr>
				<td>商品类型</td>
				<td>
					<select name="typeId" style="width:100%">
						<%
							ArrayList<LeixingDto> allLeixing = (ArrayList)request.getAttribute("allLeixing");
							for(LeixingDto lx:allLeixing){
						%>
							<option value="<%=lx.getId() %>"><%=lx.getName() %></option>
							<%
							}
						
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td>商品描述</td>
				<td><textarea name="description" rows="4" cols="20"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="增加商品"></td>
			</tr>
			
		</table>
	</form>
  </center>
</body>
</html>