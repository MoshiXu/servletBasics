package com.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;

public class connectToDB {
		
	public boolean check(String name,String password) {

		String n,p;
		
		//connect to DB
		try {
			
			//DriverManager.registerDriver(new org.sqlite.JDBC());
					
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","XDB","xuer37xdd5");
					
			Statement st=con.createStatement();
			String query="select * from users where username='"+name+"'&& password='"+password+"' ";
			ResultSet rs=st.executeQuery(query);
					
			if(rs.next()) {
				RequestDispatcher rd=
				rd.forward(request, response);
			}else {
				rd=req.getRequestDispatcher("/homt.html");
				rd.include(req,resp);
				out.println("Invalid");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(n.equals("admin")&& p.equals("admin")) {
			
			
			rd=req.getRequestDispatcher("wel");
			rd.forward(req, resp);
		}
		
		return false;
	}

}
