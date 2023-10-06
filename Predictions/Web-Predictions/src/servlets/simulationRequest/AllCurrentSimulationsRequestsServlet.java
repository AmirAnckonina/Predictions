package servlets.simulationRequest;

import dto.PredictionsEngineResponse;
import dto.orderRequest.NewSimulationRequestDto;
import dto.orderRequest.SimulationOrderRequestDetailsDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simulator.store.api.StoreManager;
import simulator.store.orderRequest.api.SimulationOrderRequest;
import utils.PredictionsServletUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static utils.PredictionsServletUtils.GSON_INSTANCE;
import static utils.PredictionsServletUtils.getSimulatorManager;

@WebServlet(name = "AllCurrentSimulationsRequestsServlet", urlPatterns = "/simulationRequests/simulationRequestsList")
public class AllCurrentSimulationsRequestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        StoreManager storeManager = PredictionsServletUtils.getStoreManager(getServletContext());
        List<SimulationOrderRequestDetailsDto> allCurrentRequestsList = storeManager.getAllCurrentRequestsList();
        String jsonResp = GSON_INSTANCE.toJson(allCurrentRequestsList);

        try (PrintWriter out = response.getWriter()) {
            out.println(jsonResp);
            out.flush();
        }
    }

}
