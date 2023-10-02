package supermarket.util;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/*") // Apply the filter to all URL patterns
public class SessionValidationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String loginURL = request.getContextPath() + "/login.jsp";

        if (request.getRequestURI().equals(loginURL)) {
            // Exclude the /login.jsp page from the filter
            filterChain.doFilter(request, response);
        } else {
            // Check if the "sessionId" cookie exists
            String sessionIdFromCookie = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("sessionId".equals(cookie.getName())) {
                        sessionIdFromCookie = cookie.getValue();
                        break;
                    }
                }
            }

            // Check if the "sessionId" cookie matches the session ID in the HttpSession
            HttpSession session = request.getSession(false); // Do not create a new session if it doesn't exist
            if (session != null && sessionIdFromCookie != null && sessionIdFromCookie.equals(session.getId())) {
                // Session ID matches; continue processing the request
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // Invalid session ID; redirect to the login page or handle it as needed
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }

    public void destroy() {
        // Cleanup code if needed
    }
}

