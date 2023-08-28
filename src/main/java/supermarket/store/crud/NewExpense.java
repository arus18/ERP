package supermarket.store.crud;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import supermarket.store.service.Service;

/**
 * Servlet implementation class NewExpense
 */
@WebServlet("/NewExpense")
public class NewExpense extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewExpense() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BigDecimal amount = new BigDecimal(request.getParameter("amount"));
		String recipentName = request.getParameter("recipentName");
		String paymentFor = request.getParameter("paymentFor");
        Service.newExpense(amount, recipentName, paymentFor);
        response.sendRedirect("store.jsp");
	}

}
