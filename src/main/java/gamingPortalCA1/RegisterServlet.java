package gamingPortalCA1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

	//this handles registration submissions
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
		
		//this line tells the browser that the response is html
		response.setContentType("/text.html");
		
		//this line sends html content to the browser in an output stream
		PrintWriter out = response.getWriter();
		
		//this block of strings get the form values, the gamertag and the two password entries
		String tag = request.getParameter("gamerTag");
	    String pass1 = request.getParameter("password");
	    String pass2 = request.getParameter("password2");
	    
	    
	    //this block is an checker that scans the 3 fields to see if any entries are null,
	    //the return at the bottom of the if statement stops processing the details if fields are empty.
	    if(tag == null || pass1 == null || pass2 == null ||
	    		tag.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
	    	    out.println("<p>Please fill in all fields. <a href= 'register.html'>Back</a></p>");
	    	    return; 
	    }
	    
	    //this if statement double checks that both password entries are the same.
	    //an appropriate error message is printed if they aren't the same.
	    //the return takes the user back to reinput their password entries until pass1 = pass2.
	    if(pass1 != pass2) {
	    	out.print("<p>Passwords do not match! <a href='register.html'>Try again</a></p>");
	    	return;
	    }
	    
	    //this method loads the MySql JDBC driver
	    try {
	    	 Class.forName("com.mysql.cj.jdbc.Driver");
	    
	    //this is the line that connects the java to the MySQL database.
    	 Connection con = DriverManager.getConnection(
    			 "jdbc:mysql://localhost:3306/gaming_portal", "root", "root");
    	 
    	 //this prepared an SQL statement to insert a user with 500 credits.
    	 PreparedStatement ps = con.prepareStatement(
    		 	"INSERT INTO users (gamer_tag, password, credits) VALUES (?,?,500)");
    		 	
		 ps.setString(1, tag);
		 ps.setString(2, pass1);
		 
		 //executes the insert query
		 ps.executeUpdate();
		 
		 out.println("<h3>Registration successful!</h3>");
		 out.println("<p>You have been given 500 credits!</p>");
		 out.println("<a href='login.html'>Login</a>");
		 
		 //this line closes the database connection
		 con.close();
		 
		 //this block handles the SQL errors and unexpected errors with an error message when a duplicate tag exists.
	    } catch(SQLException e) {
			 out.println("<p>Error: Gamer tag already exists.</p>");
	    } catch(Exception e) {
	    	out.println("<p>Error: " + e + "</p>");
		}
	   }
	}