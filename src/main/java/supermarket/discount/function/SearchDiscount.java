package supermarket.discount.function;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import supermarket.discount.service.*;
import supermarket.discount.model.*;

/**
 * Servlet implementation class SearchDiscount
 */
@WebServlet("/SearchDiscount")
public class SearchDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDiscount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		ArrayList<Discount> searchResult = Service.getDiscountsByProductName(search);
		request.setAttribute("searchDiscount", searchResult);
		RequestDispatcher rd = request.getRequestDispatcher("discount.jsp");
		rd.forward(request, response);
	}

}
