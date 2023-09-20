package UI.impl.javaFX.tabBody.details;

import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;
import simulator.result.api.SimulationResult;
import simulator.result.manager.api.ResultManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailsModel {
    private SimulatorManager simulatorManager;
    private ResultManager simulatorResultManager;
    private DetailsController mainController;
    private Stage primaryStage;

    DetailsController controller;


    public DetailsModel() {
    }

    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
//        this.simulatorResultManager = this.simulatorManager.getSimulatorResultManagerImpl();
    }

    private String getSimulatorStartingTimeInString(SimulationResult simulationResults){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy | HH.mm.ss");
        String formattedTime = formatter.format(new Date(simulationResults.getSimulationStartingTime()));

        return formattedTime;
    }

    public void exitSimulator() {

    }

    public void setController(DetailsController mainController) {
        this.mainController = mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
