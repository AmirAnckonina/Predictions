package servlets.worldBuilder;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simulator.builder.manager.api.WorldBuilderManager;
import utils.PredictionsServletUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static utils.PredictionsServletUtils.GSON_INSTANCE;

@WebServlet(name = "SimulationWorldNamesListServlet", urlPatterns = "/worldBuilder/simulationWorldNamesList")
public class SimulationWorldNamesListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ServletContext sc = getServletContext();
        WorldBuilderManager wbm = PredictionsServletUtils.getWorldBuilderManager(getServletContext());
        List<String> allLoadedSimulationWorldNames = wbm.getAllLoadedSimulationWorldNames();
        String jsonResp = GSON_INSTANCE.toJson(allLoadedSimulationWorldNames);

        try (PrintWriter out = response.getWriter()) {
            out.println(jsonResp);
            out.flush();
        }
    }
}
