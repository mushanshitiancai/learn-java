import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mazhibin on 2017/5/17 0017.
 */
@MultipartConfig
@WebServlet(urlPatterns = "/demo/*")
public class DemoServlet extends HttpServlet {
    static {
        System.out.println("DemoServlet");
    }

    public DemoServlet(){
        System.out.println("DemoServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(1);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("1");
    }
}
