package supermarket.product.crud;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import supermarket.product.service.ProductUpdate;
import supermarket.product.service.Service;

/**
 * Servlet implementation class NewProduct
 */
@WebServlet("/NewProduct")
@MultipartConfig
public class NewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the CSRF token from the request
        String requestToken = request.getParameter("csrfToken");

// Retrieve the CSRF token from the session
        String sessionToken = (String) request.getSession().getAttribute("csrfToken");

        if (requestToken != null && requestToken.equals(sessionToken)) {
            // CSRF token is valid; process the request
            String productName = request.getParameter("name");
            BigDecimal price =  new BigDecimal(request.getParameter("price"));
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            InputStream inputStream = null;
            Part filePart = request.getPart("image");
            if (filePart != null) {
                inputStream = filePart.getInputStream();
            }
            Service.newProduct(productName, price, description, inputStream, category);
            response.sendRedirect("product.jsp");
        } else {
            // Invalid CSRF token; handle the error (e.g., return an error page or response)
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // You can choose an appropriate status code
        }

}
}
