package supermarket.sales.function;

import java.io.IOException;
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
 * Servlet implementation class SearchInvoiceByID
 */
@WebServlet("/SearchInvoiceByID")
public class SearchInvoiceByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchInvoiceByID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("invoiceid"));
		Invoice searchResult = Service.searchInvoicesByID(id);
		request.setAttribute("searchInvoiceByIDresults", searchResult);
		RequestDispatcher rd = request.getRequestDispatcher("sales.jsp");
		rd.forward(request, response);
	}

}
