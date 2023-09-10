package UI.impl.javaFX.mainScene;

import UI.impl.javaFX.tabBody.details.DetailsController;
import UI.impl.javaFX.tabBody.details.DetailsModule;
import UI.impl.javaFX.top.PredictionsTopController;
import UI.impl.javaFX.top.PredictionsTopModule;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class PredictionsMainController {

    private Stage primaryStage;

    @FXML
    private DetailsController detailsController;
    private DetailsModule detailsModule;

    @FXML
    private PredictionsTopController topController;
    private PredictionsTopModule topModule;


    @FXML
    public void initialize() {
        if (topController != null && detailsController != null) {

            topController.setMainController(this);
            topModule.setController(topController);
            topController.topModule(topModule);
            topController.setPrimaryStage(primaryStage);


            detailsController.setMainController(this);
            this.detailsModule.setController(detailsController);
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public boolean insertNewLineToLeftListView(String line){
        return detailsController.insertNewLineToLeftListView(line);
    }

    public void onLoadSimulationButtonClicked(String path) {


        // bodyComponentController.onLoadSimulationButtonClicked();
    }
}
