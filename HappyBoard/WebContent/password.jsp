<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>${pw_error }</h3>
<c:choose>
	<c:when test="${r_type eq 1 }">
		<form action="boardModify" method="post">
			<input type="password" name="pw" required="required" maxlength="4" placeholder="비밀번호 입력">
			<input type="hidden" name="r_type" value="${r_type }">
			<input type="hidden" name="bid" value="${bid }">
			<input type="hidden" name="btype" value="${btype }">
			<input type="submit" value="완료">
		</form>
	</c:when>
	<c:otherwise>
		<form action="boardDelete" method="post">
			<input type="password" name="pw" required="required" maxlength="4" placeholder="비밀번호 입력">
			<input type="hidden" name="r_type" value="${r_type }">
			<input type="hidden" name="bid" value="${bid }">
			<input type="hidden" name="btype" value="${btype }">
			<input type="submit" value="완료">
		</form>
	</c:otherwise>
</c:choose>