package servlets.simulationRequest;


import enums.SimulationRequestStatus;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simulator.store.api.StoreManager;
import utils.PredictionsServletUtils;

@WebServlet(name = "NewSimulationRequestServlet", urlPatterns = "/simulationRequest/updateSimulationRequest")

public class UpdateSimulationRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String requestGuid = request.getParameter("requestGuid");
        String simulationRequestStatus = request.getParameter("simulationRequestStatus");
        StoreManager storeManager = PredictionsServletUtils.getStoreManager(getServletContext());
        storeManager.setSimulationRequestStatusByGuid(requestGuid, SimulationRequestStatus.valueOf(simulationRequestStatus));
    }
}
