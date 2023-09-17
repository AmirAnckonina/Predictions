package UI.impl.javaFX.tabBody.results;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.top.PredictionsTopModel;
import dto.StringRuleDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;
import simulator.result.api.SimulationResult;

import java.util.Map;

public class ResultsController {

    private PredictionsMainController mainController;
    private PredictionsTopModel predictionsTopModel;
    private Stage primaryStage;
    private SimulatorManager simulatorManager;
    private ResultsModel resultsModel;
    private Map<String, SimulationResult> simulationResultMap;

    @FXML
    private ListView<Label> executionListView;

    @FXML
    private Button reRunButton;

    @FXML
    private ListView<?> executionDetailsListView;

    @FXML
    void reRunButtonClicked(ActionEvent event) {

    }

    @FXML
    void simulationIDListClicked(MouseEvent event) {
        SimulationResult selectedRule = simulationResultMap.get(this.executionListView.getItems().toString());


    }

    public void setMainController(PredictionsMainController mainController) {
        this.mainController = mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setResultsModel(ResultsModel resultsModel) {
        this.resultsModel = resultsModel;
    }
    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
    }


    //List<SimulationResult> simulationResults = this.simulatorResultManager.getSortedHistoricalSimulationsList();
    /*
        private void printHistoricalSimulationList(List<SimulationResult> simulationResults) {
        for (int i = 0; i < simulationResults.size(); i++) {

            System.out.println((i + 1) + ". SimulationUuid:\"" + simulationResults.get(i).getSimulationUuid() + "\"" + ": "
                    + getSimulatorStartingTimeInString(simulationResults.get(i)));
        }
        System.out.print("Choose simulation to present: ");
    }

        private void handleUserSimulationResultChoice(List<SimulationResult> simulationResults) {
        if(simulationResults.size() > 0) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                if (scanner.hasNextInt()) {
                    int selectedIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (selectedIndex >= 1 && selectedIndex <= simulationResults.size()) {
                        this.simulationIndex = selectedIndex - 1;
                        showHowToPresentResultMenu();
                        PresentShowOptions showOption = howToPresentResultMenuChoiceHandler();
                        showOptionPresenter(showOption);
                        break;

                    } else {
                        System.out.println("Invalid index. Please choose a valid index.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid index.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
        }else {
            System.out.println("There is no simulation History\nReturn to main menu\n");
        }
    }
     */
}
