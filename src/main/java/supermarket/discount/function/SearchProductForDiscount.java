package supermarket.discount.function;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import supermarket.product.model.Product;
import supermarket.product.service.Service;

/**
 * Servlet implementation class SearchProductForDiscount
 */
@WebServlet("/SearchProductForDiscount")
public class SearchProductForDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProductForDiscount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		ArrayList<Product> searchResult = Service.searchProducts(search);
		request.setAttribute("searchResultStock", searchResult);
		RequestDispatcher rd = request.getRequestDispatcher("selectproductdiscount.jsp");
		rd.forward(request, response);
	}

}
