package kr.co.happy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;     
    
    public BoardListServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String str_btype = request.getParameter("btype");
		String str_page = request.getParameter("page");
		String title_tmp = "";
		
		int btype = 0;
		if(str_btype != null) {
			btype = Util.chk_ZeroInteger(str_btype);
		}
		int page = 1;
		if(str_page != null) {
			page = Util.chk_OneInteger(str_page);
		}
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardDTO> arrDTO = dao.getBoardList(btype, page);
		int pageCnt = dao.getPageCnt(btype);
		
		if(btype == 0) {
			title_tmp = "공지";
		} else if(btype == 1) {
			title_tmp = "자유";
		} else {
			title_tmp = "문의";
		}
		
		request.setAttribute("title", title_tmp);
		request.setAttribute("btype", btype);
		request.setAttribute("data", arrDTO);
		request.setAttribute("target", "boardList");
		request.setAttribute("pageCnt", pageCnt);
		
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
