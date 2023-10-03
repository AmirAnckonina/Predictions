package ui.tabs.allocations;

import com.google.gson.reflect.TypeToken;
import dto.NewSimulationRequestDto;
import dto.SimulationRequestDetailsDto;
import dto.SimulationWorldNamesDto;
import enums.TerminationType;
import exception.PredictionsUIComponentException;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.PredictionsMainController;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import simulator.mainManager.api.SimulatorManager;
import utils.HttpClientUtil;
import utils.SimulationRequestsListRefresher;
import utils.SimulationWorldListRefresher;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static utils.Constants.*;


public class AllocationsController {


    @FXML private TextField numOfExecTextField;
    @FXML private RadioButton ticksSecondsRadioButton;
    @FXML private RadioButton userRadioButton;
    @FXML private TextField secondsTextField;
    @FXML private CheckBox secondsCheckBox;
    @FXML private CheckBox ticksCheckBox;
    @FXML private TextField ticksTextField;
    @FXML private ListView<String> templatesListView;
    @FXML private Button sendRequestButton;
    @FXML private TableView<SimulationRequestDetailsDto> requestsTableView;
    @FXML private TableColumn<SimulationRequestDetailsDto, String> requestIdCol;
    @FXML private TableColumn<SimulationRequestDetailsDto, String> simulationTemplateCol;
    @FXML private TableColumn<SimulationRequestDetailsDto, Integer> numOfExecCol;
    @FXML private TableColumn<SimulationRequestDetailsDto, String> requestStatusCol;
    @FXML private TableColumn<SimulationRequestDetailsDto, Integer> runningCol;
    @FXML private TableColumn<SimulationRequestDetailsDto, Integer> doneCol;
    @FXML private TableColumn<SimulationRequestDetailsDto, Void> approvementCol;

    private SimpleStringProperty numOfExecPropertyAsString;
    private SimpleStringProperty ticksPropertyAsString;
    private SimpleStringProperty secondsPropertyAsString;
    private SimpleBooleanProperty ticksCheckBoxProperty;
    private SimpleBooleanProperty secondsCheckBoxProperty;
    private SimpleBooleanProperty userRadioButtonProperty;
    private SimpleBooleanProperty ticksSecondsRadioButtonProperty;
    private PredictionsMainController mainController;
    private SimulatorManager simulatorManager;
    private Stage primaryStage;
    private TimerTask avaSimListRefresher;
    private Timer simListTimer;
    private TimerTask requestsTableRefresher;
    private Timer requestsTableTimer;

    public AllocationsController() {
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
        initRequestsTableView();
    }

    private void initRequestsTableView() {
        requestIdCol.setCellValueFactory(new PropertyValueFactory<>("requestGuid"));
        simulationTemplateCol.setCellValueFactory(new PropertyValueFactory<>("simulationWorldName"));
        numOfExecCol.setCellValueFactory(new PropertyValueFactory<>("numOfExecutionLeft"));
        requestStatusCol.setCellValueFactory(new PropertyValueFactory<>("simulationRequestStatus"));
        runningCol.setCellValueFactory(new PropertyValueFactory<>("running"));
        doneCol.setCellValueFactory(new PropertyValueFactory<>("done"));
        approvementCol.setCellFactory(column -> new TableCell<SimulationRequestDetailsDto, Void>() {
            private final HBox buttonBox = new HBox();
            private final Button yesBtn = new Button("Yes");
            private final Button noBtn = new Button("No");

            {
                // Define actions for Button 1
                yesBtn.setOnAction(event -> {
                    SimulationRequestDetailsDto request = getTableView().getItems().get(getIndex());
                    handleYesButtonAction(request); // Call your method with the request
                });

                // Define actions for Button 2
                noBtn.setOnAction(event -> {
                    SimulationRequestDetailsDto request = getTableView().getItems().get(getIndex());
                    handleNoButtonAction(request); // Call your method with the request
                });

                // Add buttons to the HBox
                buttonBox.getChildren().addAll(yesBtn, noBtn);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null); // Clear the cell if it's empty
                } else {
                    // Set the layout (HBox) with both buttons in the cell
                    setGraphic(buttonBox);
                }
            }
        });
        requestsTableView.setPlaceholder(new Label("No requests to display"));
    }

    private void handleNoButtonAction(SimulationRequestDetailsDto request) {
    }

    private void handleYesButtonAction(SimulationRequestDetailsDto request) {

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

        NewSimulationRequestDto newSimulationRequestDto = buildNewSimulationRequestDto();
        sendNewSimulationRequestProcedure(newSimulationRequestDto);

    }

    private void sendNewSimulationRequestProcedure(NewSimulationRequestDto newSimulationRequestDto) {
        String selectedSimulationWorldName = templatesListView.getSelectionModel().getSelectedItem();
        String finalUrl =
                HttpUrl
                        .parse(POST_NEW_SIMULATION_REQUEST_ENDPOINT)
                        .newBuilder()
                        .addQueryParameter(POST_NEW_SIMULATION_REQUEST_PARAM_KEY, GSON_INSTANCE.toJson(newSimulationRequestDto))
                        .build()
                        .toString();

        HttpClientUtil.runAsync(finalUrl, new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Type requestDetailsListType = new TypeToken<ArrayList<SimulationRequestDetailsDto>>(){}.getType();
                    ArrayList<SimulationRequestDetailsDto> simulationRequestDetailsDtoList = GSON_INSTANCE.fromJson(responseBody, requestDetailsListType);
                    updateRequestsTableUI(simulationRequestDetailsDtoList);

                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request getSimulationWorldDetailsProcedure()...:(");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }
        });
    }

    private void updateRequestsTableUI(List<SimulationRequestDetailsDto> simulationRequestDetailsDtoList) {
        Platform.runLater(() -> {
            ObservableList<SimulationRequestDetailsDto> requestsList = this.requestsTableView.getItems();
            requestsList.clear();
            requestsList.addAll(simulationRequestDetailsDtoList);
        });
    }


    private NewSimulationRequestDto buildNewSimulationRequestDto() {

        try {
            String simulationWorldName = this.templatesListView.getSelectionModel().getSelectedItem();
            Integer numOfExecution = Integer.parseInt(this.numOfExecPropertyAsString.get());
            TerminationType terminationType;
            Integer ticks = null;
            Integer seconds = null;

            if (this.userRadioButtonProperty.get()) {
                terminationType = TerminationType.USER;
            } else {

                if (this.ticksCheckBoxProperty.get() && this.secondsCheckBoxProperty.get()) {
                    terminationType = TerminationType.TicksAndSeconds;
                    ticks = Integer.parseInt(this.ticksPropertyAsString.get());
                    seconds = Integer.parseInt(this.secondsPropertyAsString.get());
                } else if (this.ticksCheckBoxProperty.get() && !this.secondsCheckBoxProperty.get()) {
                    terminationType = TerminationType.Ticks;
                    ticks = Integer.parseInt(this.ticksPropertyAsString.get());
                } else if (!this.ticksCheckBoxProperty.get() && this.secondsCheckBoxProperty.get()) {
                    terminationType = TerminationType.Seconds;
                    seconds = Integer.parseInt(this.secondsPropertyAsString.get());
                } else {
                    throw new PredictionsUIComponentException("No termination type was chosen");
                }

            }

            return new NewSimulationRequestDto(simulationWorldName, numOfExecution, terminationType, ticks, seconds);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        throw new PredictionsUIComponentException("Failed to build new simulation request dto");
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

    public void setMainController(PredictionsMainController predictionsMainController) {
        this.mainController = predictionsMainController;
    }

    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setActive() {
        this.startAvailableSimulationListRefresher();
        this.startRequestsListRefresher();
    }

    public void setInactive() {
        if (this.avaSimListRefresher != null && this.simListTimer != null) {
            this.avaSimListRefresher.cancel();
            this.simListTimer.cancel();
        }

        if (this.requestsTableRefresher != null && this.requestsTableTimer != null) {
            this.requestsTableRefresher.cancel();
            this.requestsTableTimer.cancel();
        }
    }

    private void startRequestsListRefresher() {
        this.requestsTableRefresher = new SimulationRequestsListRefresher(this::updateRequestsTableUI);
        this.requestsTableTimer = new Timer();
        this.requestsTableTimer.schedule(requestsTableRefresher, SIMULATION_REQUESTS_LIST_REFRESH_RATE, SIMULATION_REQUESTS_LIST_REFRESH_RATE);
    }

    private void startAvailableSimulationListRefresher() {
        this.avaSimListRefresher = new SimulationWorldListRefresher(this::updateAvailableSimulationTemplatesListUI);
        this.simListTimer = new Timer();
        this.simListTimer.schedule(avaSimListRefresher, SIMULATION_WORLD_LIST_REFRESH_RATE, SIMULATION_WORLD_LIST_REFRESH_RATE);
    }

    private void updateAvailableSimulationTemplatesListUI(SimulationWorldNamesDto simulationWorldNamesDto) {
        Platform.runLater(() -> {
            ObservableList<String> avaSimTemplatesList = this.templatesListView.getItems();
            avaSimTemplatesList.clear();
            avaSimTemplatesList.addAll(simulationWorldNamesDto.getSimulationWorldNames());
        });
    }




}
