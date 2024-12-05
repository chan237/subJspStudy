package co.kh.dev.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "memberList", urlPatterns = { "/memberList" })
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberList() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		String VISIT_INSERT = "select * from MEMBER order by no desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webuser", "123456");
			pstmt = con.prepareStatement(VISIT_INSERT);
			rs = pstmt.executeQuery();

			// 3. 출력하기
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head><title>회원목록</title></head>");
			out.println("<body>");
			while (rs.next()) {
				int no = rs.getInt("no");
				String id = rs.getString("ID");
				String pw = rs.getString("PW");
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				java.sql.Date regdate = rs.getDate("regdate");
				out.println("<table align=center width=500 border=1>");
				out.println("<tr>");
				out.println("<th width=400>번호</th>");
				out.println("<td width=100 align=center>" + no + "</td>");
				out.println("<th width=500>아이디</th>");
				out.println("<td width=300 align=center>" + id + "</td>");
				out.println("<th width=800>비밀번호</th>");
				out.println("<td width=600 align=center>" + pw + "</td>");
				out.println("<th width=400>이름</th>");
				out.println("<td width=600 align=center>" + name + "</td>");
				out.println("<th width=1000>핸드폰 번호</th>");
				out.println("<td width=1000 align=center>" + phone + "</td>");
				out.println("<th width=800>가입 날짜</th>");
				out.println("<td width=800 align=center>" + regdate + "</td>");
				out.println("</tr>");
				out.println("</table>");
			}
			out.println("<p align=center><a href=/jspStudy/member/writeMember.html>회원가입</a></p>");
			out.println("</body>");
			out.println("</html>");
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
