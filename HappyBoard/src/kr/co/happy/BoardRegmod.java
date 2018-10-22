package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardRegmod")
public class BoardRegmod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardRegmod() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("regmod get cotect");
		
		String str_btype = request.getParameter("btype");
		int btype = Util.chk_ZeroInteger(str_btype);

		System.out.println("btype" + btype);
		
		request.setAttribute("btype", btype);
		request.setAttribute("target", "boardRegmod");
		
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("regmod post cotect");
		request.setCharacterEncoding("UTF-8");
		
		String str_r_type = request.getParameter("r_type");		
		int r_type = Util.chk_ZeroInteger(str_r_type);
		String str_btype = request.getParameter("btype");
		int btype = Util.chk_ZeroInteger(str_btype);
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String pw = request.getParameter("pw");
		String str_bid = request.getParameter("bid");
		int bid = Util.chk_ZeroInteger(str_bid);
		String srd = "boardList?btype=";
		int execute;
		
		request.setAttribute("pw_error", "");
		
		System.out.println("rtype" + r_type);
		System.out.println("str_btype" + str_btype);
		System.out.println("btype" + btype);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		//0 == Insert, 1 == update
		if(r_type == 0) {
			System.out.println("인설트");
			execute = dao.setInsert(btype, btitle, bcontent, pw);
			srd += btype;
			System.out.println("excute" + execute);
		} else if(r_type == 1){
			System.out.println("업데이트");
			execute = dao.setUpdate(bid, btitle, bcontent, pw);
			srd += btype;
			System.out.println("excute" + execute);
		}
		
		response.sendRedirect(srd);
		
	}

}
