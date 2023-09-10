package UI.impl.javaFX.top;
import UI.impl.javaFX.mainScene.PredictionsMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PredictionsTopController {

    private PredictionsMainController mainController;
    private PredictionsTopModule predictionsTopModule;
    private Stage primaryStage;

    @FXML
    private GridPane topOfScene;

    @FXML
    private Button loadSimulationButton;

    @FXML
    private Label loadedFileLabel;

    @FXML
    private Label filePathTextField;

    @FXML
    private Label labelPredictionSimulator;

    @FXML
    private Label labelChooseSkin;

    @FXML
    private ComboBox<?> skinComboBox;

    public void setMainController(PredictionsMainController mainController) {
        this.mainController = mainController;
    }
    public void topModule(PredictionsTopModule predictionsTopModule) {
        this.predictionsTopModule = predictionsTopModule;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    void onLoadSimulationButtonClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select simulation");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Simulation", "XML"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if(selectedFile == null){
            return;
        }

        this.filePathTextField.setText(selectedFile.getAbsolutePath());
        this.predictionsTopModule.onLoadSimulationButtonClicked();
        this.mainController.onLoadSimulationButtonClicked(selectedFile.getAbsolutePath());
    }

    @FXML
    void onSkinSelected(ActionEvent event) {

    }


}
