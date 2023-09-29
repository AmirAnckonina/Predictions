package listener;

import common.Constants;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import simulator.builder.api.interfaces.WorldBuilder;
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

public class PredictionsServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext predictionsServletContext = sce.getServletContext();

        WorldBuilderManager worldBuilderManager = new WorldBuilderManagerImpl();
        InformationManager informationManager = new InformationManagerImpl();
        EstablishmentManager establishmentManager = new EstablishmentManagerImpl();
        ManualSimulationSetupManager manualSimulationSetupManager = new ManualSimulationSetupManagerImpl();
        ExecutionManager executionManager = new ExecutionManagerImpl();
        SimulatorManager simulatorManager =
                new SimulatorManagerImpl(
                        establishmentManager,
                        manualSimulationSetupManager,
                        worldBuilderManager,
                        executionManager,
                        informationManager);

        predictionsServletContext.setAttribute(Constants.WORLD_BUILDER_MANAGER_ATTRIBUTE_NAME, worldBuilderManager);
        predictionsServletContext.setAttribute(Constants.INFO_MANAGER_ATTRIBUTE_NAME, informationManager);
        predictionsServletContext.setAttribute(Constants.ESTABLISHMENT_MANAGER_ATTRIBUTE_NAME, establishmentManager);
        predictionsServletContext.setAttribute(Constants.MANUAL_SETUP_MANAGER_ATTRIBUTE_NAME, manualSimulationSetupManager);
        predictionsServletContext.setAttribute(Constants.EXECUTION_MANAGER_ATTRIBUTE_NAME, executionManager);
        predictionsServletContext.setAttribute(Constants.SIMULATOR_MANAGER_ATTRIBUTE_NAME, simulatorManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        System.out.println("My web app is being destroyed ... :(");
    }
}
