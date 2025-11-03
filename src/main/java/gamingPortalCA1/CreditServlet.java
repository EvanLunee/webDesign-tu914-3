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

public class CreditServlet extends HttpServlet {
	
	//this line handles post requests to add or spend credits
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		//this line sets response type to html
		response.setContentType("text/html");
		
		//this line prepares the output stream to send html content to the browser
		PrintWriter out = response.getWriter();
		
		//this line gets the current session
		HttpSession s = request.getSession(false);
		
		//this line redirects the user to login should the session not exist
		if(s == null) {
			response.sendRedirect("login.html");
			return;
		}
	
		//this line retrieves the amount of credits from the form and if its add or spend
		String action = request.getParameter("action");
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		//this line retrieves the player ID and their gamer tag
		int id = (int)s.getAttribute("id");
		String tag = (String)s.getAttribute("gamerTag");
		
		//this line loads the mysql jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		//this line connects the application to the database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gaming_portal", "root", "root");
		
		//this line gets the current credit balance from the database
		PreparedStatement ps1 =con.prepareStatement("SELECT credits FROM players WHERE id = ?");
		ps1.setInt(1, id);
		ResultSet rs = ps1.executeQuery();
		rs.next();
		int credits = rs.getInt(1);
		int newCredits = credits;
		
		//this block processes the add or spend action
		if(action.equals("add")) {
			newCredits = credits + amount;
		} else if(action.equals("spend")) {
			if(credits - amount < 0) {
				out.println("<p>Not enough credits to spend!</p>");
				out.println("<a href = 'dashboard>Back</a>");
				con.close();
				return;
			} else {
				newCredits = credits - amount;
			}
		}
		
		//this block updates the players credits in the database
		PreparedStatement ps2 = con.prepareStatement("UPDATE players SET credits = ? WHERE id = ?");
		ps2.setInt(1, newCredits);
		ps2.setInt(2, id);
		ps2.executeUpdate();
		
		//this line updates the session with the new credit balance
		s.setAttribute("credits", newCredits);
		
		//this block displays a message showing new balance
		out.println("<h3>Transaction complete!</h3>");
		out.println("<p>" + tag + ", your new balance is: <b>" + newCredits + "</b> credits.</p>");
		out.println("a href = 'dashboard'>Back to dashboard</a>");
		
		//this line closes the database connection
		con.close();
		
		//this line handles errors with an error message
		} catch(Exception e) {
			out.println("<p>Error: " + e + "</p>");	
		}
		
	}
	
}