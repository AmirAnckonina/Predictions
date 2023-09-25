package body.requests;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RequestsTabController {

    @FXML private TextField numOfExecTextField;

    @FXML private RadioButton ticksSecondsRadioButton;

    @FXML private RadioButton userRadioButton;

    @FXML private TextField secondsTextField;

    @FXML private CheckBox secondsCheckBox;

    @FXML private CheckBox ticksCheckBox;

    @FXML private TextField ticksTextField;

    @FXML private ListView<?> templatesListView;

    @FXML private Button sendRequestButton;

    @FXML private TableView<?> requestsTableView;

    public RequestsTabController() {}

    @FXML
    private void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        ticksSecondsRadioButton.setToggleGroup(toggleGroup);
        userRadioButton.setToggleGroup(toggleGroup);
    }

    @FXML
    void onSecondsCheckBoxCheckedChanged() {

    }

    @FXML
    void onTicksCheckBoxCheckedChanged() {

    }

    @FXML
    void onTicksSecondsRadioButtonClicked() {

    }

    @FXML
    void onUserRadioButtonClicked() {

    }

}
