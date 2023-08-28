package supermarket.purchase.function;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import supermarket.purchase.model.Purchase;
import supermarket.purchase.service.Service;

/**
 * Servlet implementation class SearchPurchasesByDate
 */
@WebServlet("/SearchPurchasesByDate")
public class SearchPurchasesByDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPurchasesByDate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		ArrayList<Purchase> searchResult = Service.searchPurchasesByDate(date);
		BigDecimal total = Service.totalPurchasesByDate(date);
		request.setAttribute("searchPurchaseByDate", searchResult);
		request.setAttribute("totalSalesByDate", total);
		RequestDispatcher rd = request.getRequestDispatcher("purchase.jsp");
		rd.forward(request, response);
	}

}
