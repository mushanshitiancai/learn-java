
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by mazhibin on 16/5/29
 */
public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        System.out.println("[log]"+httpRequest.getRequestURI()+" "+httpRequest.getRequestURL());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
