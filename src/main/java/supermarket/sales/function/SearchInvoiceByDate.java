package supermarket.sales.function;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import supermarket.sales.model.Invoice;
import supermarket.sales.service.Service;

/**
 * Servlet implementation class SearchInvoiceByDate
 */
@WebServlet("/SearchInvoiceByDate")
public class SearchInvoiceByDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchInvoiceByDate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		ArrayList<Invoice> searchResult = Service.searchInvoicesByDate(date);
		BigDecimal total = Service.totalSalesByDate(date);
		request.setAttribute("searchInvoiceByDateresults", searchResult);
		request.setAttribute("totalSalesByDate", total);
		RequestDispatcher rd = request.getRequestDispatcher("sales.jsp");
		rd.forward(request, response);
	}

}
