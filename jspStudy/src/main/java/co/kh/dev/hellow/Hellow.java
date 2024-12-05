package co.kh.dev.hellow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hellow
 */
@WebServlet(
		name = "hellow2", 
		urlPatterns = { "/hellow2" }, 
		initParams = { 
				@WebInitParam(name = "phone", value = "010-2451-4561")
		})
public class Hellow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String phone = "dsa";
	
       
	
    public Hellow() {
        super();
    }
    
    public void init() {
    	ServletConfig sc = getServletConfig();
    	String phone = sc.getInitParameter("phone");
    	
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Date date = new Date();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>My First Servlet Program kdj </h1>");
        out.println("<br>");
        out.println(date.toString());
        out.println("<br>");
        out.println("<li>전화번호 : " + phone + "</li>");
        out.println("</body>");
        out.println("</html>");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
