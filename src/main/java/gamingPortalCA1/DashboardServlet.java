package gamingPortalCA1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DashboardServlet extends HttpServlet {
	//this line handles the get requests to show the dashboard
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		//this line sets the response type to html
		response.setContentType("text/html");
		
		//this line prepares the output stream to send html content to the browser
		PrintWriter out = response.getWriter();
		
		//this line gets the current session 
		HttpSession s = request.getSession(false);
		
		//this line redirects the user to the login page if no session exists
		//I have the login page set as the homepage
		if(s == null || s.getAttribute("gamerTag") == null) {
			response.sendRedirect("login.html");
			return;
		}
		
		//this line retrieves the gamer tag and credits from the session
		String tag = (String)s.getAttribute("gamerTag");
		int credits = (int)s.getAttribute("credits");
		
		//these lines display a personalised greeting and the current credit balance
		out.println("<h2>Welcome, " + tag + "</h2>");
		out.println("<p>Your current credits: <b>" + credits + "</b></p>");
		
		//this block includes the form that handles adding credits
		out.println("<form method = 'post' action = 'credit'>");
		out.println("<input type = 'hidden' name = 'action' value = 'add'/>");
		out.println("Add credits: <input type = 'number' name = 'amount' required>");
		out.println("<input type = 'submit' value = 'add'>");
		out.println("</form>");
		
		//this block includes the form that handles spending credits
		out.println("<form method = 'post' action = 'credit'>");
		out.println("<input type = 'hidden' name = 'action' value = 'spend'/>");
		out.println("Spend credits: <input type = 'number' name = 'amount' required>");
		out.println("<input type = 'submit' value = 'spend'>");
		out.println("</form>");
		
		//the redirect to login functions as my logout link
		out.println("<br><a href = 'login.html'>Logout</a>");
		}
	}
