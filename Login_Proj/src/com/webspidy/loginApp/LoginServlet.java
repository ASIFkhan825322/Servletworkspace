package com.webspidy.loginApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/register")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
	    String upwd = request.getParameter("password");
		String umobile = request.getParameter("contact");
		
		Connection con=null;
		PreparedStatement pst=null;
		RequestDispatcher dispather = null;
		String sql=("insert into loginpage.student values(?,?,?,?,?)");
         try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pst =con.prepareStatement(sql);
			pst.setInt(1, 5);
			pst.setString(2,"uname");
			pst.setString(3, "upwd");
			pst.setString(4, "uemail");
			pst.setString(5, "umobile");
			
		    int rowCount= pst.executeUpdate();
		    dispather = request.getRequestDispatcher("registration.jsp");
		    if (rowCount > 0) {
		  	request.setAttribute("status", "success");
		   }
         
  
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
         finally {
        	 if(pst!=null) {
        		 try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	 }
        	 if(con!=null) {
        		 try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	 }
         }
         
	}

}
