package supermarket.employee.function;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.mindrot.jbcrypt.BCrypt;
import supermarket.employee.service.*;
import supermarket.employee.model.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_LOGIN_ATTEMPTS = 3; // Maximum allowed login attempts
	private static final long LOCKOUT_DURATION = 5 * 60 * 1000; // Lockout duration in milliseconds (5 minutes)

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int empID = Integer.parseInt(request.getParameter("empID"));
		String enteredPassword = request.getParameter("password");

		// Retrieve the user's lockout information from the session
		HttpSession session = request.getSession();
		Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
		Long lastLoginTime = (Long) session.getAttribute("lastLoginTime");

		// Initialize the lockout information if not already set
		if (loginAttempts == null) {
			loginAttempts = 0;
		}
		if (lastLoginTime == null) {
			lastLoginTime = 0L;
		}

		// Check if the user is currently locked out
		long currentTime = System.currentTimeMillis();
		if (loginAttempts >= MAX_LOGIN_ATTEMPTS && currentTime - lastLoginTime < LOCKOUT_DURATION) {
			// User is locked out, show an error message
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Account locked due to too many failed login attempts. Please try again later.');");
			out.println("location='login.jsp';");
			out.println("</script>");
			return;
		}

		Employee emp = Service.getEmployeeByID(empID);

		if (emp == null) {
			// Handle incorrect ID case
			out.println("<script type=\"text/javascript\">");
			out.println("alert('ID incorrect');");
			out.println("location='login.jsp';");
			out.println("</script>");
		} else {
			String storedHashedPassword = emp.getPassword();

			if (BCrypt.checkpw(enteredPassword, storedHashedPassword)) {
				// Successful login
				session.setAttribute("name", emp.getName());
				session.setAttribute("id", emp.getEmployeeID());
				session.setAttribute("role", emp.getRole());

				// Reset the login attempts and last login time
				session.setAttribute("loginAttempts", 0);
				session.setAttribute("lastLoginTime", 0L);

				Cookie sessionCookie = new Cookie("sessionId", session.getId());
				sessionCookie.setPath("/");
				response.addCookie(sessionCookie);
				response.sendRedirect("home.jsp");
			} else {
				// Failed login attempt
				loginAttempts++;
				session.setAttribute("loginAttempts", loginAttempts);
				session.setAttribute("lastLoginTime", currentTime);

				out.println("<script type=\"text/javascript\">");
				out.println("alert('Password incorrect');");
				out.println("location='login.jsp';");
				out.println("</script>");
			}
		}
	}
}
