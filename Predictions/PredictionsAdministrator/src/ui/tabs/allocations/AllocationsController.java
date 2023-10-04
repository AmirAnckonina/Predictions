package ui.tabs.allocations;

import com.google.gson.reflect.TypeToken;
import dto.SimulationRequestUpdateDto;
import dto.orderRequest.SimulationOrderRequestDetailsDto;
import dto.worldBuilder.SimulationWorldNamesDto;
import enums.SimulationRequestStatus;
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
import ui.mainScene.MainController;
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


    @FXML private TextField secondsTextField;
    @FXML private ListView<String> templatesListView;
    @FXML private Button sendRequestButton;
    @FXML private TableView<SimulationOrderRequestDetailsDto> requestsTableView;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, String> requestIdCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, String> simulationTemplateCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, Integer> numOfExecCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, String> requestStatusCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, Integer> runningCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, Integer> doneCol;
    @FXML private TableColumn<SimulationOrderRequestDetailsDto, Void> approvementCol;

    private SimpleStringProperty numOfExecPropertyAsString;
    private SimpleBooleanProperty ticksSecondsRadioButtonProperty;
    private MainController mainController;
    private SimulatorManager simulatorManager;
    private Stage primaryStage;
    private TimerTask avaSimListRefresher;
    private Timer simListTimer;
    private TimerTask requestsTableRefresher;
    private Timer requestsTableTimer;

    public AllocationsController() {
        this.numOfExecPropertyAsString = new SimpleStringProperty();
        this.ticksSecondsRadioButtonProperty = new SimpleBooleanProperty(false);

    }

    @FXML
    private void initialize() {
        initRequestsTableView();
    }

    private void initRequestsTableView() {
        requestIdCol.setCellValueFactory(new PropertyValueFactory<>("requestGuid"));
        simulationTemplateCol.setCellValueFactory(new PropertyValueFactory<>("simulationWorldName"));
        numOfExecCol.setCellValueFactory(new PropertyValueFactory<>("numOfExecutionLeft"));
        requestStatusCol.setCellValueFactory(new PropertyValueFactory<>("simulationRequestStatus"));
        runningCol.setCellValueFactory(new PropertyValueFactory<>("running"));
        doneCol.setCellValueFactory(new PropertyValueFactory<>("done"));
        approvementCol.setCellFactory(column -> new TableCell<SimulationOrderRequestDetailsDto, Void>() {
            private final HBox buttonBox = new HBox();
            private final Button yesBtn = new Button("Yes");
            private final Button noBtn = new Button("No");

            {
                // Define actions for Button 1
                yesBtn.setOnAction(event -> {
                    SimulationOrderRequestDetailsDto request = getTableView().getItems().get(getIndex());
                    handleYesButtonAction(request); // Call your method with the request
                });

                // Define actions for Button 2
                noBtn.setOnAction(event -> {
                    SimulationOrderRequestDetailsDto request = getTableView().getItems().get(getIndex());
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

    private void handleNoButtonAction(SimulationOrderRequestDetailsDto request) {
        sendNewSimulationResponseProcedure(builSimulationRequestUpdateDto(request, SimulationRequestStatus.REJECTED));
    }

    private void handleYesButtonAction(SimulationOrderRequestDetailsDto request) {
        sendNewSimulationResponseProcedure(builSimulationRequestUpdateDto(request, SimulationRequestStatus.APPROVED));
    }

    @FXML
    void simulationInListViewClicked() {
        //String guid = this.templatesListView.getSelectionModel().getSelectedItem().getText();
    }

    private void sendNewSimulationResponseProcedure(SimulationRequestUpdateDto newSimulationRequestDto) {
        String selectedSimulationWorldName = templatesListView.getSelectionModel().getSelectedItem();
        String finalUrl =
                HttpUrl
                        .parse(POST_UPDATE_SIMULATION_REQUEST_ENDPOINT)
                        .newBuilder()
                        .addQueryParameter(POST_NEW_SIMULATION_REQUEST_PARAM_KEY, GSON_INSTANCE.toJson(newSimulationRequestDto))
                        .build()
                        .toString();

        HttpClientUtil.runAsync(finalUrl, new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Type requestDetailsListType = new TypeToken<ArrayList<SimulationOrderRequestDetailsDto>>(){}.getType();
                    ArrayList<SimulationOrderRequestDetailsDto> simulationRequestDetailsDtoList = GSON_INSTANCE.fromJson(responseBody, requestDetailsListType);
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

    private void updateRequestsTableUI(List<SimulationOrderRequestDetailsDto> simulationRequestDetailsDtoList) {
        Platform.runLater(() -> {
            ObservableList<SimulationOrderRequestDetailsDto> requestsList = this.requestsTableView.getItems();
            requestsList.clear();
            requestsList.addAll(simulationRequestDetailsDtoList);
        });
    }


    private SimulationRequestUpdateDto builSimulationRequestUpdateDto(SimulationOrderRequestDetailsDto simulationRequestDetailsDto
            , SimulationRequestStatus simulationRequestStatus) {
        try {
            return new SimulationRequestUpdateDto(simulationRequestDetailsDto.getRequestGuid(), simulationRequestStatus);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        throw new PredictionsUIComponentException("Failed to build simulation request update dto");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
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
