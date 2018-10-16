package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardModify")
public class BoardModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardModify() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("modify cotect");
		
		String str_r_type = request.getParameter("r_type");
		int r_type = Util.chk_ZeroInteger(str_r_type);	
		String pw = request.getParameter("pw");
		String str_bid = request.getParameter("bid");
		int bid = Util.chk_ZeroInteger(str_bid);
		
		BoardDAO dao = BoardDAO.getInstance();		
		String pw_check = dao.pw_check(bid);
		
		request.setAttribute("pw_error", "");	
		request.setAttribute("r_type", r_type);
		request.setAttribute("bid", bid);

		
		if(pw.equals(pw_check)) {
			BoardDTO dto = dao.getOneBid(bid);
			request.setAttribute("dto", dto);
			request.setAttribute("btype", dto.getBtype());
			request.setAttribute("target", "boardRegmod");			
			
			RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
			rd.forward(request, response);				
		} else {
			request.setAttribute("pw_error", "비밀번호가 틀렸습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("boardReg_type");
			rd.forward(request, response);			
		}
	}

}
