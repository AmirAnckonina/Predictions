package servlets.simulationRequest;

import dto.PredictionsEngineResponse;
import dto.orderRequest.NewSimulationRequestDto;
import dto.worldBuilder.SimulationWorldDetailsDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simulator.builder.manager.api.WorldBuilderManager;
import simulator.store.api.StoreManager;
import utils.PredictionsServletUtils;

import java.io.IOException;
import java.io.PrintWriter;

import static utils.PredictionsServletUtils.GSON_INSTANCE;

@WebServlet(name = "NewSimulationRequestServlet", urlPatterns = "/newSimulationRequest")
public class NewSimulationRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String simulationRequestBody = request.getParameter("simulationRequestBody");
        StoreManager storeManager = PredictionsServletUtils.getStoreManager(getServletContext());
        PredictionsEngineResponse addNewRequestResponse = storeManager.addNewSimulationRequest(GSON_INSTANCE.fromJson(simulationRequestBody, NewSimulationRequestDto.class));
        String jsonResp = GSON_INSTANCE.toJson(addNewRequestResponse);

        try (PrintWriter out = response.getWriter()) {
            out.println(jsonResp);
            out.flush();
        }
    }

}
