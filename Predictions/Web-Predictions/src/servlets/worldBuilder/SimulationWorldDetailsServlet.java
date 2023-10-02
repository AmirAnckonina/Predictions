package servlets.worldBuilder;

import dto.worldBuilder.SimulationWorldDetailsDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simulator.builder.manager.api.WorldBuilderManager;
import utils.PredictionsServletUtils;

import java.io.IOException;
import java.io.PrintWriter;

import static utils.PredictionsServletUtils.GSON_INSTANCE;

@WebServlet(name = "SimulationWorldDetailsServlet", urlPatterns = "/worldBuilder/simulationWorldDetails")
public class SimulationWorldDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        String simulationWorldName = request.getParameter("simulationWorldName");
        WorldBuilderManager worldBuilderManager = PredictionsServletUtils.getWorldBuilderManager(getServletContext());
        SimulationWorldDetailsDto simulationWorldDetailsDto = worldBuilderManager.getSimulationWorldDetailsByName(simulationWorldName);
        String jsonResp = GSON_INSTANCE.toJson(simulationWorldDetailsDto);

        try (PrintWriter out = response.getWriter()) {
            out.println(jsonResp);
            out.flush();
        }
    }
}
