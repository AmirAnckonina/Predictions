package body.requests;

import com.google.gson.reflect.TypeToken;
import dto.PredictionsEngineResponse;
import dto.orderRequest.NewSimulationRequestDto;
import dto.orderRequest.SimulationOrderRequestDetailsDto;
import dto.worldBuilder.SimulationWorldNamesDto;
import enums.TerminationType;
import exception.PredictionsUIComponentException;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import static utils.Constants.GSON_INSTANCE;

public class RequestsTabController {


    @FXML private TextField numOfExecTextField;
    @FXML private RadioButton ticksSecondsRadioButton;
    @FXML private RadioButton userRadioButton;
    @FXML private TextField secondsTextField;
    @FXML private CheckBox secondsCheckBox;
    @FXML private CheckBox ticksCheckBox;
    @FXML private TextField ticksTextField;
    @FXML private ListView<String> templatesListView;
    @FXML private Button sendRequestButton;
    @FXML private Button newExecutionButton;
    @FXML private ComboBox<String> simulationWorldCombobox;
    @FXML private TableView<SimulationOrderRequestDetailsDto> requestsTableView;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, String> requestIdCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, String> simulationTemplateCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, Integer> numOfExecCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, Integer> terminationCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, String> requestStatusCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, Integer> runningCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, Integer> doneCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, Void> newExecCol;

    private SimpleStringProperty simulationWorldNameProperty;
    private SimpleStringProperty numOfExecPropertyAsString;
    private SimpleStringProperty ticksPropertyAsString;
    private SimpleStringProperty secondsPropertyAsString;
    private SimpleBooleanProperty ticksCheckBoxProperty;
    private SimpleBooleanProperty secondsCheckBoxProperty;
    private SimpleBooleanProperty userRadioButtonProperty;
    private SimpleBooleanProperty ticksSecondsRadioButtonProperty;
    private PredictionsMainController mainController;
    private Stage primaryStage;
    private TimerTask avaSimListRefresher;
    private Timer simListTimer;
    private TimerTask requestsTableRefresher;
    private Timer requestsTableTimer;

    public RequestsTabController() {
        this.numOfExecPropertyAsString = new SimpleStringProperty();
        this.ticksPropertyAsString = new SimpleStringProperty();
        this.secondsPropertyAsString = new SimpleStringProperty();
        this.ticksCheckBoxProperty = new SimpleBooleanProperty(false);
        this.secondsCheckBoxProperty = new SimpleBooleanProperty(false);
        this.ticksSecondsRadioButtonProperty = new SimpleBooleanProperty(false);
        this.userRadioButtonProperty = new SimpleBooleanProperty();
        this.simulationWorldNameProperty = new SimpleStringProperty();

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
        this.userRadioButton.selectedProperty().bindBidirectional(this.userRadioButtonProperty);
        this.ticksSecondsRadioButton.selectedProperty().bindBidirectional(this.ticksSecondsRadioButtonProperty);
        this.simulationWorldNameProperty.bind(this.simulationWorldCombobox.valueProperty());
        this.sendRequestButton.setDisable(true);
        onUserRadioButtonClicked();
        initRequestsTableView();
    }

    private SimpleBooleanProperty isRequestEntryInTableViewSelected() {
        return new SimpleBooleanProperty(!this.requestsTableView.getSelectionModel().isEmpty());
    }

    private SimpleBooleanProperty isListViewItemSelected() {
        return new SimpleBooleanProperty(!this.templatesListView.getSelectionModel().isEmpty());
    }
    private void initRequestsTableView() {
        requestIdCol.setCellValueFactory(new PropertyValueFactory<>("requestGuid"));
        simulationTemplateCol.setCellValueFactory(new PropertyValueFactory<>("simulationWorldName"));
        numOfExecCol.setCellValueFactory(new PropertyValueFactory<>("numOfExecutions"));
        terminationCol.setCellValueFactory(new PropertyValueFactory<>("terminationType"));
        requestStatusCol.setCellValueFactory(new PropertyValueFactory<>("simulationRequestStatus"));
        runningCol.setCellValueFactory(new PropertyValueFactory<>("running"));
        doneCol.setCellValueFactory(new PropertyValueFactory<>("done"));
        newExecCol.setCellFactory(param -> new TableCell<SimulationOrderRequestDetailsDto, Void>() {
            private final Button newExecutionButton = new Button("New Execution");

            {
                newExecutionButton.setOnAction(event -> {
                    try {
                        String requestGuid = getTableView().getItems().get(getIndex()).getRequestGuid();
                        newExecutionProcedure(requestGuid);
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);

                } else {
                    setGraphic(newExecutionButton);
                    setText(null);
                }
            }
        });
        requestsTableView.setPlaceholder(new Label("No requests to display"));
    }

    private void newExecutionProcedure(String requestGuid) {
       throw new PredictionsUIComponentException("Not Impl newExecutionProc under RequestTabContoller");
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
        // Impl
    }

    @FXML
    void simWrldEntryComboBoxClicked() {

    }

    @FXML
    void onSimWrldComboBoxShowing() {
        this.simulationWorldCombobox.getItems().clear();
        ObservableList<String> items = FXCollections.observableArrayList();
        this.templatesListView.getItems().forEach(item -> items.add(item));
        this.simulationWorldCombobox.setItems(items);
    }


    @FXML
    void onSendReqBtnClicked() {

        NewSimulationRequestDto newSimulationRequestDto = buildNewSimulationRequestDto();
        //NewSimulationRequestDto newSimulationRequestDto = new NewSimulationRequestDto("FakeCustomerName", "FakeSimulationWorldName", 1, TerminationType.USER, null, null);
        sendNewSimulationRequestProcedure(newSimulationRequestDto);

    }



    private void sendNewSimulationRequestProcedure(NewSimulationRequestDto newSimulationRequestDto) {
        //String selectedSimulationWorldName = templatesListView.getSelectionModel().getSelectedItem();
        String selectedSimulationWorldName = "master" ;
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
                    PredictionsEngineResponse predEngResp = GSON_INSTANCE.fromJson(responseBody, PredictionsEngineResponse.class);

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

    private void updateRequestsTableUI(List<SimulationOrderRequestDetailsDto> simulationOrderRequestDetailsDtoList) {
        Platform.runLater(() -> {
            ObservableList<SimulationOrderRequestDetailsDto> requestsList = this.requestsTableView.getItems();
            requestsList.clear();
            requestsList.addAll(simulationOrderRequestDetailsDtoList);
        });
    }


    private NewSimulationRequestDto buildNewSimulationRequestDto() {

        try {
            String simulationWorldName = this.simulationWorldCombobox.getValue();
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

            return new NewSimulationRequestDto("FakeCustomerName",simulationWorldName, numOfExecution, terminationType, ticks, seconds);

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
    }

    public void setMainController(PredictionsMainController predictionsMainController) {
        this.mainController = predictionsMainController;
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
