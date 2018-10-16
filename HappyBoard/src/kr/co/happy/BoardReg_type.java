package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardReg_type")
public class BoardReg_type extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardReg_type() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("reg_type contect");
		String str_bid = request.getParameter("bid");
		int bid = Util.chk_ZeroInteger(str_bid);
		String str_btype = request.getParameter("btype");
		int btype = Util.chk_ZeroInteger(str_btype);
		String str_r_type = request.getParameter("r_type");
		int r_type = Util.chk_ZeroInteger(str_r_type);		
		
		request.setAttribute("bid", bid);
		request.setAttribute("r_type", r_type);
		request.setAttribute("btype", btype);
		request.setAttribute("target", "password");
		
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
