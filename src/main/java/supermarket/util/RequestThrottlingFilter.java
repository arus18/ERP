package supermarket.util;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


@WebFilter("/*")
public class RequestThrottlingFilter implements Filter {
    private static final int MAX_REQUESTS_PER_SECOND = 5;
    private static final long INTERVAL = 1000L; // 1 second
    private ConcurrentHashMap<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ipAddress = request.getRemoteAddr();
        AtomicInteger counter = requestCounts.get(ipAddress);
        long currentTime = System.currentTimeMillis();

        if (counter == null) {
            counter = new AtomicInteger(1);
            requestCounts.put(ipAddress, counter);
        } else {
            int currentCount = counter.get();
            if (currentCount >= MAX_REQUESTS_PER_SECOND) {
                if (currentTime - counter.intValue() * INTERVAL < INTERVAL) {
                    // Request limit exceeded, send an error response.
                    response.getWriter().write("Request limit exceeded. Please try again later.");
                    return;
                } else {
                    // Reset the counter if the last request was more than 1 second ago.
                    counter.set(0);
                }
            }
            counter.incrementAndGet();
        }

        // Continue processing the request.
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed.
    }
}
