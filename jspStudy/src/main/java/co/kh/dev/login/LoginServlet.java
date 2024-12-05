package co.kh.dev.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "loginServlet", urlPatterns = { "/loginServlet.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void prosessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//세션정보 가져오기(세션 객체 있으면 가져오고 없으면 생성)
    	response.setContentType("text/html;charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
    	try {
    		//세션정보가 있으면 아이디와 패스워드를 읽어옴. 없으면 로그인창으로 가게함
    		HttpSession session = request.getSession(false);
        	if(session != null) {
        		System.out.println("세션고유아이디 : "+session.getId());
        		//사용자 정보 ID,PASS
        		String id = (String) session.getAttribute("id");
        		String pass = (String) session.getAttribute("pass");
        		//사용자 정보를 화면에 출력 
                out.println("<html>");
                out.println("<body>");
                out.println("<table border='1' width='300'>");
                out.println("<tr>");
                out.println("<td width='300' align='center'>" + id + " 님 로그인 되었습니다.</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td align='center'>");
                out.println("<a href='#'>회원정보</a>");
                out.println("<a href='Logout'>로그아웃</a>");
                out.println("</td>");
                out.println("</tr>");            
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            	}else{
                out.println("<html>");
                out.println("<body>");
                out.print("<form method='post' action='/jspStudy/LoginCheck.do'>");
                out.println("<table border='1' width='300'>");
                out.println("<tr>");
                out.println("<th width='100'>아이디</th>");
                out.println("<td width='200'>&nbsp;<input type='text' name='id'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th width='100'>비번</th>");
                out.println("<td width='200'>&nbsp;<input type='password' name='pass'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td align='center' colspan='2'>");
                out.println("<input type='button' value='회원가입'>");
                out.println("<input type='submit' value='로그인'>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</form>");
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
        	}
    	}catch(Exception e){
    		System.out.println(e.toString());
    	}finally {
    		if(out != null) {
    			out.close();
    		}
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
