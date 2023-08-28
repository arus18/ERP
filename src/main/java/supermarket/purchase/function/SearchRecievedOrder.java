package supermarket.purchase.function;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import supermarket.order.model.Order;
import supermarket.purchase.service.Service;

/**
 * Servlet implementation class SearchRecievedOrder
 */
@WebServlet("/SearchRecievedOrder")
public class SearchRecievedOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRecievedOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("orderID"));
		Order searchResult = Service.getRecievedOrderByID(id);
		request.setAttribute("searchRecievedOrder", searchResult);
		RequestDispatcher rd = request.getRequestDispatcher("recievedorders.jsp");
		rd.forward(request, response);
	}

}
