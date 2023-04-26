package com.webspidy.loginApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uemail = request.getParameter("username");
		String upwd =request.getParameter("password");
		
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		HttpSession session=request.getSession();
		RequestDispatcher dispather = null;
		String qry=("select * from loginpage where  uemail =? and upwd= ?");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pst= con.prepareStatement(qry);
			pst.setString(1, "uemail");
			pst.setString(2, "upwd");
			rs = pst.executeQuery();
			if(rs.next()) {
				session.setAttribute("name", rs.getString("uname"));
				dispather = request.getRequestDispatcher("index.jsp");
			}else{
				request.setAttribute("status", "failed");
				
			}
			dispather.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
	}

}
