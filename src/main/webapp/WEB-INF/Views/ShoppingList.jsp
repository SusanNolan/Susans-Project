<%@ page import="SusansCitCloudApp.Swap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Susan's Shopping List Application</title>
</head>
<body>
Susans Shopping List


	<c:forEach items="${swaps}" var="swaps" varStatus="row">
		<c:choose>
			<c:when test="${swap.done}">
				<del>${swap.text}</del> 
			</c:when>
			<c:otherwise>
			 	${swap.text} 
			</c:otherwise>
		</c:choose>
		<form method="post">
			<input name="_method" type="hidden" value="put"> 
			<input name="swapId" type="hidden" value="${row.count}"> 
			<input type="submit" value="Update">
		</form>
		<form method="post">
			<input name="_method" type="hidden" value="delete"> 
			<input name="swapId" type="hidden" value="${row.count}"> 
			<input type="submit" value="Delete">
		</form>
		<br />
	</c:forEach>
	<h2>Add Remove items</h2>
	<form method="post">
		Text: <input name="text"><input type="submit">
	</form>
</body>
</html>
