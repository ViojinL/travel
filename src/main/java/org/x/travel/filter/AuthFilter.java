package org.x.travel.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.x.travel.entity.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private static final List<String> EXCLUDE = Arrays.asList(
            "/login",
            "/login.jsp",
            "/css/",
            "/images",
            "/js/",
            "/upload_form.jsp",
            "/webjars"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (isExcluded(path)) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = req.getSession(false);
        User user = session != null ? (User) session.getAttribute("user") : null;
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no-op
    }

    @Override
    public void destroy() {
        // no-op
    }

    private boolean isExcluded(String path) {
        if (path.startsWith("/resources") || path.startsWith("/uploads")) {
            return true;
        }
        if (path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")) {
            return true;
        }
        for (String exclude : EXCLUDE) {
            if (path.equals(exclude) || path.startsWith(exclude)) {
                return true;
            }
        }
        return false;
    }
}
