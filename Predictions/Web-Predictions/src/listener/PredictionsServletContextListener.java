package listener;

import common.Constants;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
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
import simulator.manualSetup.manager.impl.ManualSetupManagerImpl;
import simulator.store.api.StoreManager;
import simulator.store.impl.StoreManagerImpl;
import simulator.usersManager.api.UserManager;
import simulator.usersManager.impl.UserManagerImpl;

@WebListener
public class PredictionsServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("My web app is being initialized ... :(");
        ServletContext predictionsServletContext = sce.getServletContext();

        WorldBuilderManager worldBuilderManager = new WorldBuilderManagerImpl();
        ExecutionManager executionManager = new ExecutionManagerImpl();
        InformationManager informationManager = new InformationManagerImpl();
        EstablishmentManager establishmentManager = new EstablishmentManagerImpl();
        ManualSimulationSetupManager manualSimulationSetupManager = new ManualSetupManagerImpl();
        StoreManager storeManager = new StoreManagerImpl();
        UserManager userManager = new UserManagerImpl();
        SimulatorManager simulatorManager =
                new SimulatorManagerImpl(
                        establishmentManager,
                        manualSimulationSetupManager,
                        worldBuilderManager,
                        executionManager,
                        informationManager,
                        storeManager,
                        userManager);

        predictionsServletContext.setAttribute(Constants.WORLD_BUILDER_MANAGER_ATTRIBUTE_NAME, worldBuilderManager);
        predictionsServletContext.setAttribute(Constants.INFO_MANAGER_ATTRIBUTE_NAME, informationManager);
        predictionsServletContext.setAttribute(Constants.ESTABLISHMENT_MANAGER_ATTRIBUTE_NAME, establishmentManager);
        predictionsServletContext.setAttribute(Constants.MANUAL_SETUP_MANAGER_ATTRIBUTE_NAME, manualSimulationSetupManager);
        predictionsServletContext.setAttribute(Constants.EXECUTION_MANAGER_ATTRIBUTE_NAME, executionManager);
        predictionsServletContext.setAttribute(Constants.STORE_MANAGER_ATTRIBUTE_NAME, storeManager);
        predictionsServletContext.setAttribute(Constants.USER_MANAGER_ATTRIBUTE_NAME, userManager);
        predictionsServletContext.setAttribute(Constants.SIMULATOR_MANAGER_ATTRIBUTE_NAME, simulatorManager);
    }

   /* @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        System.out.println("My web app is being destroyed ... :(");
    }*/
}
