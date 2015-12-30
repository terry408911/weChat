<%@page import="java.util.LinkedList"%>
<%@page import="com.bluesky.wechat.bean.ConstructionSite"%>
<%@page import="com.bluesky.wechat.dao.ConstructionSiteDao"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"  />
<title>Insert title here</title>
</head>

<script src="../js/showImage.js" type="text/javascript"></script>
<script src="../js/changeSelect.js" type="text/javascript"></script>

<body>
	<h1>微信举报</h1>
	<%
		Date date=new Date();
		long d=date.getTime();
		LinkedList<String> str_district=(LinkedList<String>)request.getAttribute("str_district");
		LinkedList<String> str_street=(LinkedList<String>)request.getAttribute("str_street");
		LinkedList<String> str_constructionId=(LinkedList<String>)request.getAttribute("str_constructionId");
	%>
	<form action="weixin_infoServlet" method="post" enctype="multipart/form-data">
		业务编号<input type="text"  disabled="disabled" value="<%=d%>">&nbsp;&nbsp;&nbsp;&nbsp;
			
			<input   name="id"type="hidden" value="<%=d %>">
		
		<input type="submit" value="创建"><br>
		<div id="select">
			选择工地
			<select id="select_district" onchange="changeSelect_fir()">
			<%for(String s : str_district){ %>
				<option><%=s %></option>
			<%} %>
			</select>
			<select id="select_street" onchange="changeSelect_sec()">
			<%for(String s : str_street){ %>
				<option><%=s %></option>
			<%} %>
			</select>
			<select id="select_constructionId">
			<%for(String s : str_constructionId){ %>
				<option><%=s %></option>
			<%} %>
			</select>
		</div>
		<br>
		业务内容<br>
		<div style='width:100%; height:300px;'>
			<textarea name="content" rows="15%" cols="80%"></textarea> 
		</div> 
		<c:forEach begin="1" step="1" end="3" var="i">
			<input type="file" name="src${i }" onchange="showImage(this,${i })" />	
			<div id="imgPreview${i }" style='width:100; height:80;float: left;'></div> 
		</c:forEach> </br>
		<br><br>
	</form>
</body>
</html>