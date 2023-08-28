package supermarket.employee.function;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import supermarket.employee.service.*;
import supermarket.employee.model.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int empID = Integer.parseInt(request.getParameter("empID"));
		String password = request.getParameter("password");
		Employee emp = Service.getEmployeeByID(empID);
		if(emp == null) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('id incorrect');");
			out.println("location='login.jsp';");
			out.println("</script>");
		}else if(!emp.getPassword().equals(password)) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('password incorrect');");
			out.println("location='login.jsp';");
			out.println("</script>");
		}else if(emp.getPassword().equals(password) && emp.getEmployeeID() == empID) {
			HttpSession session=request.getSession();  
	        session.setAttribute("name",emp.getName());
	        session.setAttribute("id",emp.getEmployeeID());
	        session.setAttribute("role",emp.getRole());
	        response.sendRedirect("home.jsp");
		}
	}

}
