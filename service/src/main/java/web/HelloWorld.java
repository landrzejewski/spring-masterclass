package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/sayHello")
public class HelloWorld extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) {
            name = "";
        }
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<title>Hello World</title>");
        writer.println("<body>");
        writer.println("Witaj " + name);
        writer.println("</body>");
        writer.println("</html>");
    }

}
