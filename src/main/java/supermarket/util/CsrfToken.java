package supermarket.util;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "CsrfToken", value = "/token")
public class CsrfToken extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String csrfToken = UUID.randomUUID().toString();
        request.getSession().setAttribute("csrfToken", csrfToken);
        // Set the content type to JSON
        response.setContentType("application/json");

        // Create a JSON response containing the CSRF token
        String jsonResponse = "{\"csrfToken\":\"" + csrfToken + "\"}";

        // Write the JSON response to the client
        response.getWriter().write(jsonResponse);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
