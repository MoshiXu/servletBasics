package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	/*
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html"); // we want to get the response either in text or in html
		PrintWriter out=resp.getWriter(); // printwriter is used to print the response on to the browser.
		
		String name=req.getParameter("username");
		String age=req.getParameter("age");
		String password=req.getParameter("password");
		
		out.println("welcome user: "+name);
		out.println("your age is  : "+age);
		out.println("please hide the passsword "+password);	
	}
	*/
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); // we want to get the response either in text or in html
		PrintWriter out=resp.getWriter(); // printwriter is used to print the response on to the browser.
		
		String name=req.getParameter("username");
		String age=req.getParameter("age");
		String password=req.getParameter("password");
		
		RequestDispatcher rd;
		
		//connect to DB
		try {		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","XDB","xuer37xdd5");
							
			Statement st=con.createStatement();
			String query="select * from users where username='"+name+"'and passwords='"+password+"' ";
			ResultSet rs=st.executeQuery(query);
							
			if(rs.next()) {
				rd=req.getRequestDispatcher("wel");
				rd.forward(req, resp);

			}else {
				rd=req.getRequestDispatcher("/home.html");
				rd.include(req,resp);
				out.println("Invalid");
			}
					
					
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		/*
		if(name.equals("admin") && password.equals("admin")) {
			
			out.println("You're admin"); out.println("welcome user: "+name);
			out.println("your age is  : "+age);
			out.println("please hide the passsword "+password);
			 
			rd=req.getRequestDispatcher("wel");
			rd.forward(req, resp);
			
		}else {
			rd=req.getRequestDispatcher("/home.html");
			rd.include(req, resp);
			out.println("<h1>You're not admin</h1>");
		}
		
		*/
		
		
	}
	
	

}
