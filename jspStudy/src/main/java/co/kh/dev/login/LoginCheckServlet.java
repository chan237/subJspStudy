package co.kh.dev.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/loginCheck.do")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void prosessRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			//사용자 정보를 읽는다.
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");
    		String pass = request.getParameter("pass");
    		
			//체크하기
    		if(id.equals("admin") && pass.equals("123456")) {
    			HttpSession session = request.getSession();
    			session.setAttribute("id", id);
    			session.setAttribute("pass", pass);
    		}
    		response.sendRedirect("/login.do");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		prosessRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		prosessRequest(request, response);
	}


}
