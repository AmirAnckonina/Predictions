package UI.impl.javaFX.tabBody.details;

import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;
import simulator.result.api.SimulationResult;
import simulator.result.manager.api.ResultManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailsModule {

    private SimulatorManager simulatorManager;
    private ResultManager simulatorResultManager;

    DynamicInfoController controller;


    public DetailsModule() {
        this.simulatorManager = new SimulatorManagerImpl();
        this.simulatorResultManager = this.simulatorManager.getSimulatorResultManagerImpl();
    }

    public void runSimulatorUI() {

    }

    public void loadSimulationSession() {
    }

    public void showLoadedSimulationWorldDetails() {

    }

    public void runSimulationSession() {

    }

    public void showHistoricalSimulationResult() {
        List<SimulationResult> simulationResults = this.simulatorResultManager.getSortedHistoricalSimulationsList();
        String newLine;

        for (int i = 0; i < simulationResults.size(); i++) {

            newLine = (i + 1) + ". SimulationUuid:\"" + simulationResults.get(i).getSimulationUuid() + "\"" + ": "
                    + getSimulatorStartingTimeInString(simulationResults.get(i));

            controller.insertNewLineToLeftListView(newLine);
        }

        controller.updateListViewLinesOutput();
        System.out.print("Choose simulation to present: ");
    }

    private String getSimulatorStartingTimeInString(SimulationResult simulationResults){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy | HH.mm.ss");
        String formattedTime = formatter.format(new Date(simulationResults.getSimulationStartingTime()));

        return formattedTime;
    }

    public void exitSimulator() {

    }

    public void setController(DynamicInfoController controller) {

        this.controller = controller;
    }
}
