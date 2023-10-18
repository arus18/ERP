package supermarket.employee.function;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import supermarket.employee.openid.IdTokenVerifierAndParser;

import java.io.IOException;

@WebServlet(urlPatterns = {"/openid-login"})
public class OpenIdLogin extends HttpServlet {
    @Override
    protected void doPost (HttpServletRequest req,
                           HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");

        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();
            System.out.println("User name: " + name);
            System.out.println("User email: " + email);

            HttpSession session = req.getSession(true);
            session.setAttribute("userName", name);
            /*req.getServletContext()
                    .getRequestDispatcher("/welcome-page.jsp").forward(req, resp);*/

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
