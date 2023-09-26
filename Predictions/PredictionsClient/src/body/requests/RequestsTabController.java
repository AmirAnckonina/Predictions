package body.requests;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
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
    @FXML private ListView<Label> templatesListView;
    @FXML private Button sendRequestButton;
    @FXML private TableView<?> requestsTableView;

    private SimpleStringProperty numOfExecPropertyAsString;
    private SimpleStringProperty ticksPropertyAsString;
    private SimpleStringProperty secondsPropertyAsString;
    private SimpleBooleanProperty ticksCheckBoxProperty;
    private SimpleBooleanProperty secondsCheckBoxProperty;
    private SimpleBooleanProperty userRadioButtonProperty;
    private SimpleBooleanProperty ticksSecondsRadioButtonProperty;

    public RequestsTabController() {
        this.numOfExecPropertyAsString = new SimpleStringProperty();
        this.ticksPropertyAsString = new SimpleStringProperty();
        this.secondsPropertyAsString = new SimpleStringProperty();
        this.ticksCheckBoxProperty = new SimpleBooleanProperty(false);
        this.secondsCheckBoxProperty = new SimpleBooleanProperty(false);
        this.ticksSecondsRadioButtonProperty = new SimpleBooleanProperty(false);
        this.userRadioButtonProperty = new SimpleBooleanProperty(true);

    }

    @FXML
    private void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        ticksSecondsRadioButton.setToggleGroup(toggleGroup);
        userRadioButton.setToggleGroup(toggleGroup);
        this.ticksTextField.textProperty().bindBidirectional(this.ticksPropertyAsString);
        this.secondsTextField.textProperty().bindBidirectional(this.secondsPropertyAsString);
        this.numOfExecTextField.textProperty().bindBidirectional(this.numOfExecPropertyAsString);
        this.ticksCheckBox.selectedProperty().bindBidirectional(this.ticksCheckBoxProperty);
        this.secondsCheckBox.selectedProperty().bindBidirectional(this.secondsCheckBoxProperty);
        this.sendRequestButton.setDisable(true);
        onUserRadioButtonClicked();
    }

    @FXML
    void onSecondsCheckBoxCheckedChanged() {
        boolean checked = this.secondsCheckBoxProperty.get();
        if (!checked) {
            this.secondsTextField.clear();
            this.secondsTextField.setDisable(true);
        } else {
            this.secondsTextField.setDisable(false);
        }

    }

    @FXML
    void onTicksCheckBoxCheckedChanged() {
        boolean checked = this.ticksCheckBoxProperty.get();
        if (!checked) {
            this.ticksTextField.clear();
            this.ticksTextField.setDisable(true);
        } else {
            this.ticksTextField.setDisable(false);
        }
    }

    @FXML
    void onTicksSecondsRadioButtonClicked() {
        this.sendRequestButton.setDisable(false);
        enableTicksSecondsElements();

    }

    @FXML
    void onUserRadioButtonClicked() {
        this.sendRequestButton.setDisable(false);
        disableTicksSecondsElements();
    }

    @FXML
    void simulationInListViewClicked() {
        //String guid = this.templatesListView.getSelectionModel().getSelectedItem().getText();
    }

    @FXML
    void onSendReqBtnClicked() {
        // Should Impl here the request to the relevant servlet.
    }
    private void disableTicksSecondsElements() {
        this.ticksCheckBox.setDisable(true);
        this.secondsCheckBox.setDisable(true);
        this.ticksTextField.setDisable(true);
        this.secondsTextField.setDisable(true);
    }

    private void enableTicksSecondsElements() {
        this.ticksCheckBox.setDisable(false);
        this.secondsCheckBox.setDisable(false);
        this.ticksTextField.setDisable(false);
        this.secondsTextField.setDisable(false);
    }

}
