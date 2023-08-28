package supermarket.sales.crud;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import supermarket.sales.service.*;
/**
 * Servlet implementation class DeleteInvoiceFromSales
 */
@WebServlet("/DeleteInvoiceFromSales")
public class DeleteInvoiceFromSales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInvoiceFromSales() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int invoiceID = Integer.parseInt(request.getParameter("invoiceID"));
		Service.deleteInvoice(invoiceID);
		response.sendRedirect("sales.jsp");
	}

}
