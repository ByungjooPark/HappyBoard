package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardDelete")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String pw = request.getParameter("pw");
		String str_btype = request.getParameter("btype");
		int btype = Util.chk_ZeroInteger(str_btype);
		String str_bid = request.getParameter("bid");
		int bid = Util.chk_ZeroInteger(str_bid);
		int execute;
		String srd = "boardList?btype=";
		
		request.setAttribute("pw_error", "");

		BoardDAO dao = BoardDAO.getInstance();
		
		String pw_check = dao.pw_check(bid);
		
		if(pw.equals(pw_check)) {
			execute = dao.delete(bid);
			srd += btype;
			System.out.println(execute);				
		} else {
			request.setAttribute("pw_error", "비밀번호가 틀렸습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("boardReg_type");
			rd.forward(request, response);
			
		}
		
		response.sendRedirect(srd);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
