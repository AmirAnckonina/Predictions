package servlets.simulationRequest;

import com.google.gson.Gson;
import dto.SimulationRequestStatus;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "NewSimulationRequestServlet", urlPatterns = "/newSimulationRequest")
public class NewSimulationRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String requestGuid = request.getParameter("requestGuid");
        SimulationRequestStatus simulationRequestStatus = SimulationRequestStatus.valueOf(
                request.getParameter("simulationRequestStatus"));

    }

}
