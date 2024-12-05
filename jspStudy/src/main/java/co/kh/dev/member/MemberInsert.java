package co.kh.dev.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "memberInsert", urlPatterns = { "/memberInsert" })
public class MemberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberInsert() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.전송된 값을 가져온다.
		request.setCharacterEncoding("UTF-8");

		// 2.정보 가져오기
		String ID = request.getParameter("ID");
		String PW = request.getParameter("PW");
		String NAME = request.getParameter("NAME");
		String PHONE = request.getParameter("PHONE");

		// 3.DB 테이블(visit)에 전달 및 저장(프로토콜 : 약속)
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean successFlag = false;
		String url = "jdbc:oracle:thin:@127.0.0.1:1521/xe";
		String VISIT_INSERT = "INSERT INTO MEMBER VALUES(member_seq.NEXTVAL, ?, ?, ?, ?,SYSDATE)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "webuser", "123456");
			pstmt = con.prepareStatement(VISIT_INSERT);
			pstmt.setString(1, ID);
			pstmt.setString(2, PW);
			pstmt.setString(3, NAME);
			pstmt.setString(4, PHONE);
			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true) : (false);
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
		}

		if (successFlag == true) {
			System.out.println("입력성공");
			response.sendRedirect("memberList");
		} else {
			System.out.println("입력실패");
		}

	}

}
