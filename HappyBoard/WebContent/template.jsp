<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/top.css">
<link rel="stylesheet" type="text/css" href="css/${target }.css">
<title>HappyList</title>
</head>
<body>
	<jsp:include page="top_menu.jsp"></jsp:include>
	<div class="content">
		<jsp:include page="${target}.jsp"></jsp:include>
	</div>
	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>