package jaejung.springprac.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SessionFilter extends OncePerRequestFilter {
    private final String sessionCreationURI = "/api/redis/session";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
        if(!sessionCreationURI.equals(request.getRequestURI())){
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession httpSession = request.getSession(false);
        System.out.println("-----------------> check01");
        if(httpSession == null){ // if session id doesn't exist
            System.out.println("-----------------> check02");
            response.sendRedirect(sessionCreationURI);
            System.out.println("-----------------> check03");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
