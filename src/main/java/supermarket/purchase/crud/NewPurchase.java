package supermarket.purchase.crud;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import supermarket.purchase.service.*;

/**
 * Servlet implementation class NewPurchase
 */
@WebServlet("/NewPurchase")
public class NewPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPurchase() {
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
			int orderID = Integer.parseInt(request.getParameter("orderID"));
			int distID = Integer.parseInt(request.getParameter("distID"));
			String distName = request.getParameter("distName");
			BigDecimal amount = new BigDecimal(request.getParameter("amount"));
			Service.newPurchase(distID,orderID,amount,distName);
			response.sendRedirect("recievedorders.jsp");
		} else {
			// Invalid CSRF token; handle the error (e.g., return an error page or response)
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // You can choose an appropriate status code
		}


	}

}
