package servlets.simulationInfo;

import dto.orderRequest.SimulationOrderRequestDetailsDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simulator.information.manager.api.InformationManager;
import simulator.mainManager.api.SimulatorManager;
import simulator.store.api.StoreManager;
import utils.PredictionsServletUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static utils.PredictionsServletUtils.GSON_INSTANCE;
import static utils.PredictionsServletUtils.getSimulatorManager;

@WebServlet(name = "SimulationGuidListServlet", urlPatterns = "/simulationInfo/simulationGuidList")
public class SimulationGuidListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        InformationManager infoManager = PredictionsServletUtils.getInfoManager(getServletContext());
        List<String> allCurrentRequestsList = infoManager.getAllSimulationsGuid();
        String jsonResp = GSON_INSTANCE.toJson(allCurrentRequestsList);

        try (PrintWriter out = response.getWriter()) {
            out.println(jsonResp);
            out.flush();
        }
    }

}
