package supermarket.distributor.crud;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import supermarket.distributor.service.*;

/**
 * Servlet implementation class DeleteDistributor
 */
@WebServlet("/DeleteDistributor")
public class DeleteDistributor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDistributor() {
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
		} else {
			// Invalid CSRF token; handle the error (e.g., return an error page or response)
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // You can choose an appropriate status code
		}

		HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        if(session.getAttribute("role").equals("Admin")) {
        	int distributorID = Integer.parseInt(request.getParameter("distID"));
    		Service.deleteDistributor(distributorID);
    		response.sendRedirect("distributor.jsp");
        }else {
        	out.println("<script type=\"text/javascript\">");
			out.println("alert('you dont have permissions');");
			out.println("location='distributor.jsp';");
			out.println("</script>");
        }
        
	}

}
