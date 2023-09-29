package utils;

import com.google.gson.Gson;
import common.Constants;
import jakarta.servlet.ServletContext;
import simulator.builder.manager.api.WorldBuilderManager;
import simulator.builder.manager.impl.WorldBuilderManagerImpl;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.establishment.manager.impl.EstablishmentManagerImpl;
import simulator.execution.manager.api.ExecutionManager;
import simulator.execution.manager.impl.ExecutionManagerImpl;
import simulator.information.manager.api.InformationManager;
import simulator.information.manager.impl.InformationManagerImpl;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;
import simulator.manualSetup.manager.api.ManualSimulationSetupManager;
import simulator.manualSetup.manager.impl.ManualSimulationSetupManagerImpl;

public class PredictionsServletUtils {

    public final static Gson GSON_INSTANCE = new Gson();
    public static SimulatorManager getSimulatorManager(ServletContext servletContext) {
        return (SimulatorManager) servletContext.getAttribute(Constants.SIMULATOR_MANAGER_ATTRIBUTE_NAME);
    }

    public static WorldBuilderManager getWorldBuilderManager(ServletContext servletContext) {
        return (WorldBuilderManager) servletContext.getAttribute(Constants.WORLD_BUILDER_MANAGER_ATTRIBUTE_NAME);
    }

    public static InformationManager getInfoManager(ServletContext servletContext) {
        return (InformationManager) servletContext.getAttribute(Constants.INFO_MANAGER_ATTRIBUTE_NAME);
    }

    public static EstablishmentManager getEstablishmentManager(ServletContext servletContext) {
        return (EstablishmentManager) servletContext.getAttribute(Constants.ESTABLISHMENT_MANAGER_ATTRIBUTE_NAME);
    }

    public static ManualSimulationSetupManager getManualSetupManager(ServletContext servletContext) {
        return (ManualSimulationSetupManager) servletContext.getAttribute(Constants.MANUAL_SETUP_MANAGER_ATTRIBUTE_NAME);
    }

    public static ExecutionManager getExecutionManager(ServletContext servletContext) {
        return (ExecutionManager) servletContext.getAttribute(Constants.EXECUTION_MANAGER_ATTRIBUTE_NAME);
    }

}
