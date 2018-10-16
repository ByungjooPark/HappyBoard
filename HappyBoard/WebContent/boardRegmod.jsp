<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


${btype }
<form action="boardRegmod" method="post">
	<div class="reg_top">
		<div>
			제목&nbsp;&nbsp;<input type="text" name="btitle" value="${dto.getBtitle() }" maxlength="70" required="required">
		</div>
		<div>
			비밀번호&nbsp;&nbsp;<input type="password" name="pw" maxlength="4" required="required" value="${dto.getPw() }">
		</div>
	</div>
	<br>
	<div class="reg_mid">
		content&nbsp;&nbsp;<textarea name="bcontent" rows="10" cols="60" required="required">${dto.getBcontent() }</textarea>
	</div>
	<br>
	<input type="hidden" name="btype" value="${btype }">
	<input type="hidden" name="bid" value="${dto.getBid() }">
	<input type="submit" value="완료">
	<input type="hidden" name="r_type" value="${r_type }">
</form>