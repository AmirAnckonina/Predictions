package servlets.worldBuilder;

import dto.EnvironmentPropertiesDto;
import dto.SimulationWorldDetailsDto;
import dto.TerminationInfoDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simulator.builder.manager.api.WorldBuilderManager;
import simulator.builder.utils.exception.WorldBuilderManagerException;
import simulator.definition.entity.EntityDefinition;
import simulator.definition.rule.Rule;
import simulator.execution.instance.property.api.PropertyInstance;
import utils.PredictionsServletUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.PredictionsServletUtils.GSON_INSTANCE;

@WebServlet(name = "LoadSimulationWorldFileServlet", urlPatterns = "/worldBuilder/loadSimulationWorldFile")
public class LoadSimulationWorldFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonResp;
        SimulationWorldDetailsDto simulationWorldDetailsDto;

        try {
            response.setContentType("application/json");
            WorldBuilderManager worldBuilderManager = PredictionsServletUtils.getWorldBuilderManager(getServletContext());
            simulationWorldDetailsDto = worldBuilderManager.buildSimulationWorld(PredictionsServletUtils.getInputStreamBody(request));
            jsonResp = GSON_INSTANCE.toJson(simulationWorldDetailsDto);

        } catch (WorldBuilderManagerException e) {
            jsonResp = GSON_INSTANCE.toJson(e.toString());
            response.sendError(400, e.getMessage());

        }
        try (PrintWriter out = response.getWriter()) {
            out.println(jsonResp);
            out.flush();
        }
    }
}