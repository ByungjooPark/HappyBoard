<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="d_content">
	
	<div class="d_top">
		<div class="d_top_title">
			<h3>${bt.getBtitle() }</h3>
		</div>
		
		<div class="d_top_other">				
			<a href="boardReg_type?btype=${bt.getBtype() }&bid=${bt.getBid() }&r_type=1">수정</a>&nbsp;|&nbsp;
			<a href="boardReg_type?btype=${bt.getBtype() }&bid=${bt.getBid() }&r_type=2">삭제</a>&nbsp;|&nbsp;
			<a href="boardList?btype=${bt.getBtype() }&page=1">목록으로</a>
		</div>
	</div>
	<br>
	<div class="d_mid">
		${bt.getBcontent() }
	</div>
</div>

