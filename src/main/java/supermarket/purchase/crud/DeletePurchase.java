package supermarket.purchase.crud;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import supermarket.purchase.service.Service;

/**
 * Servlet implementation class DeletePurchase
 */
@WebServlet("/DeletePurchase")
public class DeletePurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePurchase() {
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
			HttpSession session=request.getSession();
			PrintWriter out = response.getWriter();
			if(session.getAttribute("role").equals("Admin")) {
				int purchaseID = Integer.parseInt(request.getParameter("purchaseID"));
				Service.deletePurchase(purchaseID);
				response.sendRedirect("purchase.jsp");
			}else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('you dont have permissions');");
				out.println("location='purchase.jsp';");
				out.println("</script>");
			}
		} else {
			// Invalid CSRF token; handle the error (e.g., return an error page or response)
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // You can choose an appropriate status code
		}


		
	}

}
