package servlets.worldBuilder;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EnvironmentPropertiesDefinition", urlPatterns = "/worldBuilder/environmentPropertiesDefinition")
public class EnvironmentPropertiesDefinition extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {


    }
}
