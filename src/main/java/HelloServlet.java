import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by vasya on 29/08/17.
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    public void doGet (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.getWriter().print("Hello, world!!!");
    }
}
