package ui.management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class ManagementController {

    @FXML
    private Label currLoadedFilePathLbl;

    @FXML
    private Button setThreadsCountButton;

    @FXML
    private GridPane availableSimulationDetailsGrid;

    @FXML
    private ListView<Label> availableSimulationListView;

    @FXML
    private GridPane threadPoolManagementGrid;

    @FXML
    private Label waitingValueLabel;

    @FXML
    private Label runningValueLabel;

    @FXML
    private Label runningLabel;

    @FXML
    private Label finishedValueLabel;

    @FXML
    private Button setNumOfThreadBtn;

    @FXML
    private Label setThreadMassageLbl;

    @FXML
    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
    }


    @FXML
    void loadFileButtonClicked(ActionEvent event) {

    }

    @FXML
    void setNumOfThreadBtnClicked(ActionEvent event) {

    }

    @FXML
    void setThreadsCountButtonClicked(ActionEvent event) {

    }
}