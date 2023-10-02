package supermarket.store.crud;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import supermarket.store.service.Service;

/**
 * Servlet implementation class DeleteExpense
 */
@WebServlet("/DeleteExpense")
public class DeleteExpense extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteExpense() {
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
			int expenseID = Integer.parseInt(request.getParameter("expenseID"));
			Service.deleteExpense(expenseID);
			response.sendRedirect("store.jsp");
		} else {
			// Invalid CSRF token; handle the error (e.g., return an error page or response)
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // You can choose an appropriate status code
		}

		// TODO Auto-generated method stub

	}

}
