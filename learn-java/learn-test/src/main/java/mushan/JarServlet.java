package mushan;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Created by mazhibin on 2017/5/23 0023.
 */
@WebServlet(name = "jarServlet", urlPatterns = "/zz")
public class JarServlet extends HttpServlet {
    public JarServlet(){
        System.out.println("JarServlet");
    }
}
