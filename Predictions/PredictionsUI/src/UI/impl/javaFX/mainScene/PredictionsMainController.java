package UI.impl.javaFX.mainScene;

import UI.impl.javaFX.tabBody.details.DetailsController;
import UI.impl.javaFX.tabBody.details.DetailsModule;
import UI.impl.javaFX.tabBody.results.ResultsController;
import UI.impl.javaFX.top.TopController;
import UI.impl.javaFX.top.PredictionsTopModule;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;

public class PredictionsMainController {


    private SimulatorManager simulatorManager;
    private Stage primaryStage;

    @FXML
    private TopController topComponentController;
    private PredictionsTopModule topModule;

    @FXML
    private DetailsController detailsComponentController;
    private DetailsModule detailsModule;


    @FXML
    private ResultsController resultsComponentController;

    public PredictionsMainController() {
        this.simulatorManager = new SimulatorManagerImpl();
    }

    @FXML
    public void initialize() {
        if (topComponentController != null && detailsComponentController != null) {

            topComponentController.setMainController(this);
            topComponentController.topModule(topModule);
            topComponentController.setPrimaryStage(primaryStage);


            detailsComponentController.setMainController(this);
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public boolean insertNewLineToLeftListView(String line){
        return detailsComponentController.insertNewLineToLeftListView(line);
    }

    public void onLoadSimulationButtonClicked(String path) {

        // bodyComponentController.onLoadSimulationButtonClicked();
    }
}
