package servlets.simulationInfo;

import dto.SimulationRequestUpdateDto;
import dto.orderRequest.SimulationOrderRequestDetailsDto;
import dto.simulationInfo.SimulationDocumentInfoDto;
import dto.simulationInfo.SimulationInfoRequestDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simulator.information.manager.api.InformationManager;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.mainManager.api.SimulatorManager;
import simulator.store.api.StoreManager;
import utils.PredictionsServletUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static utils.PredictionsServletUtils.GSON_INSTANCE;

@WebServlet(name = "SimulationInfoServlet", urlPatterns = "/simulationInfo/simulationInfo")
public class SimulationInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String requestBody = request.getParameter("simulationRequestBody");
        SimulationInfoRequestDto simulationInfoRequestDto = GSON_INSTANCE.fromJson(requestBody, SimulationInfoRequestDto.class);
        InformationManager informationManager = PredictionsServletUtils.getInfoManager(getServletContext());
        SimulationDocument simulationDocument = informationManager.getSimulationDocumentByGuid(simulationInfoRequestDto.getSimulationGuid());
        SimulationDocumentInfoDto simulationDocumentInfoDto = simulationDocument.getInitialSimulationDocumentInfoDto();
        String jsonResp = GSON_INSTANCE.toJson(simulationDocumentInfoDto);

        try (PrintWriter out = response.getWriter()) {
            out.println(jsonResp);
            out.flush();
        }
    }

}
