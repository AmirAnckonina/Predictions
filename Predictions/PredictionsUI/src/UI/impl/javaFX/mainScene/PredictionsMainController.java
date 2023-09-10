package UI.impl.javaFX.mainScene;

import UI.impl.javaFX.model.PredictionsMainModel;
import UI.impl.javaFX.tabBody.details.DetailsController;
import UI.impl.javaFX.tabBody.details.DetailsModule;
import UI.impl.javaFX.top.PredictionsTopController;
import UI.impl.javaFX.top.PredictionsTopModule;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;

public class PredictionsMainController {

    private PredictionsMainModel predictionsMainModel;
    private Stage primaryStage;

    @FXML
    private DetailsController detailsComponentController;
    private DetailsModule detailsModule;

    @FXML
    private PredictionsTopController topComponentController;
    private PredictionsTopModule topModule;


    @FXML
    public void initialize() {
        if (topComponentController != null && detailsComponentController != null) {

            topComponentController.setMainController(this);
            topModule.setController(topComponentController);
            topComponentController.topModule(topModule);
            topComponentController.setPrimaryStage(primaryStage);


            detailsComponentController.setMainController(this);
            this.detailsModule.setController(detailsComponentController);
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
