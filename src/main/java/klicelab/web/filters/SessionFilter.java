package klicelab.web.filters;


import klicelab.model.Session;
import klicelab.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SessionFilter implements Filter {

    SessionService sessionService;

    public SessionFilter(@Autowired SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession == null) {
            redirectToLogin(servletRequest, servletResponse);
            return;
        }
        Session session = sessionService.get(httpSession.getId());
        if (session == null) {
            redirectToLogin(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    void redirectToLogin(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String backUrl = String.valueOf(httpServletRequest.getRequestURL());
        String query = httpServletRequest.getQueryString();
        if (query != null && !query.isEmpty()) {
            backUrl = backUrl + "?" + query;
        }
        backUrl = org.apache.catalina.util.URLEncoder.DEFAULT.encode(backUrl, "utf8");
        httpServletResponse.sendRedirect("/user/login?backUrl=" + backUrl);
    }

    @Override
    public void destroy() {

    }
}
