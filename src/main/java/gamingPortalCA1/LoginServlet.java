package gamingPortalCA1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {


//handles login form credentials and submissions
protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	
	//this line sets the response type to html
	response.setContentType("text/html");
	
	//this line prepares the output stream to send html content to the browser
	PrintWriter out = response.getWriter();
	
	//this getters retrieve the user credentials from the login form
	String tag = request.getParameter("gamerTag");
	String pass = request.getParameter("password");
	
	//this loads the MySQL data 
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
	//this line connects the login page to the database
	Connection con = DriverManager.getConnection("jdbc:mysql//localhost:3306/gaming_portal", "root", "root");
	
	//this ps prepares an sql query to ensure credentials match
	PreparedStatement ps = con.prepareStatement("SELECT * FROM players WHERE gamer_tag = ? AND password = ?");
	ps.setString(1, tag);
	ps.setString(2,pass);
	
	//this line executes the query
	ResultSet rs = ps.executeQuery();
	
	//this if statement creates a session for the user if the credentials are valid
	if(rs.next()) {
		HttpSession session = request.getSession();
		session.setAttribute("gamerTag", tag);
		session.setAttribute("credits", rs.getInt("credits"));
		session.setAttribute("id", rs.getInt("id"));
		response.sendRedirect("dashboard");
	} else {
		out.println("<p>Invalid login. <a href='login.html'> Try again</a></p>");
	}
		con.close();
	
	} catch(Exception e) {
		out.println("<p>Error: " + e + "</p>");
	}
	}
	
}