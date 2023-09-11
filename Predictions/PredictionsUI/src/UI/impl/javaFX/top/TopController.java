package UI.impl.javaFX.top;
import UI.impl.javaFX.mainScene.PredictionsMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class TopController {

    private PredictionsMainController mainController;
    private PredictionsTopModel predictionsTopModel;
    private Stage primaryStage;

    @FXML
    private Button loadSimulationButton;

    @FXML
    private Button gueueManagmentButton;

    @FXML
    private Label loadSimulationPath;

    @FXML
    private ComboBox<?> chooseSkinCB;

    @FXML
    private Label predictionTitle;

    @FXML
    void gueueManagmentClicked(ActionEvent event) {

    }

    @FXML
    void loadSimulationButtonClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select simulation");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Simulation", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if(selectedFile == null){
            return;
        }

        this.loadSimulationPath.setText(selectedFile.getAbsolutePath());
        this.predictionsTopModel.onLoadSimulationButtonClicked();
        this.mainController.onLoadSimulationButtonClicked(selectedFile.getAbsolutePath());
    }

    @FXML
    void onSkinSelected(ActionEvent event) {
        System.out.println("onSkinSelected");
    }

    public void setMainController(PredictionsMainController mainController) {
        this.mainController = mainController;
    }
    public void setModel(PredictionsTopModel predictionsTopModel) {
        this.predictionsTopModel = predictionsTopModel;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }



}
