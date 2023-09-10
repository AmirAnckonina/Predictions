package UI.impl.javaFX.tabBody.results;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.top.PredictionsTopModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ResultsController {

    private PredictionsMainController mainController;
    private PredictionsTopModule predictionsTopModule;
    private Stage primaryStage;

    @FXML
    private ListView<?> executionListView;

    @FXML
    private Button reRunButton;

    @FXML
    private ListView<?> executionDetailsListView;

    @FXML
    void reRunButtonClicked(ActionEvent event) {

    }

    public void setMainController(PredictionsMainController mainController) {
        this.mainController = mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
