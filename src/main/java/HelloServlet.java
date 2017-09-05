import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by vasya on 29/08/17.
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private static final Logger LOGGER =Logger.getLogger(HelloServlet.class.getName());
    public void doGet (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.getWriter().print("Hello, world!!!");
        LOGGER.info("Request was gotten");
    }
}
