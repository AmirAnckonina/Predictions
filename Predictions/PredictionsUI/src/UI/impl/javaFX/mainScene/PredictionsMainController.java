package UI.impl.javaFX.mainScene;

import UI.impl.javaFX.tabBody.details.DetailsController;
import UI.impl.javaFX.tabBody.details.DetailsModel;
import UI.impl.javaFX.tabBody.results.ResultsController;
import UI.impl.javaFX.tabBody.results.ResultsModel;
import UI.impl.javaFX.top.TopController;
import UI.impl.javaFX.top.PredictionsTopModel;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;

public class PredictionsMainController {


    private SimulatorManager simulatorManager;
    private Stage primaryStage;

    @FXML
    private TopController topComponentController;
    private PredictionsTopModel topModule;

    @FXML
    private DetailsController detailsComponentController;
    private DetailsModel detailsModel;


    @FXML
    private ResultsController resultsComponentController;
    private ResultsModel resultsModel;

    public PredictionsMainController() {
        this.simulatorManager = new SimulatorManagerImpl();
    }

    @FXML
    public void initialize() {
        if (topComponentController != null && detailsComponentController != null) {
            topModule = new PredictionsTopModel();
            resultsModel = new ResultsModel();
            detailsModel = new DetailsModel();

            topComponentController.setMainController(this);
            topComponentController.setModel(topModule);
            topModule.setController(topComponentController);

            detailsComponentController.setMainController(this);
            detailsComponentController.setDetailsModel(detailsModel);
            detailsModel.setController(detailsComponentController);

            resultsComponentController.setMainController(this);
            resultsComponentController.setResultsModel(resultsModel);
            resultsModel.setResultsController(resultsComponentController);
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
