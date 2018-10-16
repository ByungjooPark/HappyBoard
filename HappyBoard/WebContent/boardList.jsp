<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.co.happy.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<a href="boardRegmod?btype=${btype }"><input class="regmod" type="button" value="글쓰기"></a>

<table>
	<tr>		
		<th class="th1">글번호</th>
		<th class="th2">제목</th>
		<th class="th3">등록일</th>
	</tr>

<c:forEach var="list" items="${data}">
	<tr>
		<td class="td_center">${list.getSeq() }</td>
		<td><a href="boardDetail?btype=${list.getBtype() }&bid=${list.getBid() }">${list.getBtitle() }</a></td>
		<td class="td_center">${list.getBregdate() }</td>
	</tr>			
</c:forEach>
</table>

<br>

<div>
	<a href="boardList?btype=${btype }&page=1">처음으로</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<c:forEach var="pageCnt" begin="1" end="${pageCnt}" step="1">
		<a href="boardList?btype=${btype }&page=${pageCnt }"><span>${pageCnt }</span></a>&nbsp;&nbsp;
	</c:forEach>
	&nbsp;&nbsp;<a href="boardList?btype=${btype }&page=${pageCnt }">마지막으로</a>
</div>
