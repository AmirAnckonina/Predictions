package servlets.simulationRequest;


import com.google.gson.Gson;
import dto.SimulationRequestUpdateDto;
import enums.SimulationExecutionStatus;
import enums.SimulationRequestStatus;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simulator.store.api.StoreManager;
import utils.PredictionsServletUtils;

import java.io.IOException;
import java.io.PrintWriter;

import static utils.PredictionsServletUtils.GSON_INSTANCE;

@WebServlet(name = "UpdateSimulationRequest", urlPatterns = "/simulationRequests/updateSimulationRequest")

public class UpdateSimulationRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = request.getParameter("simulationRequestBody");
        SimulationRequestUpdateDto simulationRequestUpdateDto = GSON_INSTANCE.fromJson(requestBody, SimulationRequestUpdateDto.class);
        String requestGuid = simulationRequestUpdateDto.getRequestGuid();
        SimulationRequestStatus simulationRequestStatus = simulationRequestUpdateDto.getSimulationRequestStatus();
        StoreManager storeManager = PredictionsServletUtils.getStoreManager(getServletContext());
        storeManager.setSimulationRequestStatusByGuid(requestGuid, simulationRequestStatus);

        String jsonResp = GSON_INSTANCE.toJson("request updated successfully");
        try (PrintWriter out = response.getWriter()) {
            out.println(jsonResp);
            out.flush();
        }
    }
}
