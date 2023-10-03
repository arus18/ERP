package supermarket.employee.openid;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import java.io.IOException;

@WebServlet(name = "OpenIdServlet", value = "/OpenIdServlet")
public class OpenIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            String idToken = request.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();

            HttpSession session = request.getSession(true);
            session.setAttribute("userName", name);
            response.sendRedirect("home.jsp");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
