package supermarket.employee.crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import supermarket.employee.service.Service;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve the CSRF token from the request
		String requestToken = request.getParameter("csrfToken");

// Retrieve the CSRF token from the session
		String sessionToken = (String) request.getSession().getAttribute("csrfToken");

		if (requestToken != null && requestToken.equals(sessionToken)) {
			// CSRF token is valid; process the request
			// ...
			HttpSession session=request.getSession();
			PrintWriter out = response.getWriter();
			if(session.getAttribute("role").equals("Admin")) {
				int employeeID = Integer.parseInt(request.getParameter("empID"));
				String name = request.getParameter("name");
				String address =  request.getParameter("address");
				String phoneNumber = request.getParameter("phonenumber");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
				String role = request.getParameter("role");
				String designation = request.getParameter("designation");
				BigDecimal salary = new BigDecimal(request.getParameter("salary"));
				Service.updateEmployee(employeeID,email, password, phoneNumber, dob, address, name, role, designation, salary);
				response.sendRedirect("employee.jsp");
			}else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('you dont have permissions');");
				out.println("location='employee.jsp';");
				out.println("</script>");
			}
		} else {
			// Invalid CSRF token; handle the error (e.g., return an error page or response)
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // You can choose an appropriate status code
		}


		
	}

}
