import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by mazhibin on 16/4/26
 */
@WebServlet(name = "MyServlet你好",urlPatterns = {"/my"},initParams = {@WebInitParam(name = "name", value = "mushan")})
public class MyServlet implements Servlet {

    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String servletName = servletConfig.getServletName();
        servletResponse.setContentType("text/html");

        PrintWriter writer = servletResponse.getWriter();

        servletResponse.setCharacterEncoding("utf-8");
        writer.print(servletName + "23");

        Enumeration<String> attributeNames = servletRequest.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            System.out.println(attributeNames.nextElement());
        }

        System.out.println(servletRequest.getContentLength());
        System.out.println(servletRequest.getContentType());
        System.out.println(servletRequest.getProtocol());
        System.out.println(servletRequest.getParameterMap());
        System.out.println(servletRequest.getCharacterEncoding());

        Enumeration<String> parameterNames = servletRequest.getParameterNames();
        while(parameterNames.hasMoreElements()){
            System.out.println(parameterNames.nextElement());
        }

        System.out.println(servletConfig.getInitParameter("name"));

        RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/index.jsp");
        dispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public String getServletInfo() {
        return "My Servlet!";
    }

    @Override
    public void destroy() {

    }
}
