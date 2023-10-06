package body.details;

import body.details.subbodyobjects.SimulationDetail;
import body.details.subbodyobjects.entity.property.EntityPropertyController;
import body.details.subbodyobjects.environment.EnvironmentController;
import body.details.subbodyobjects.ruleComponent.MainActionController;
import body.details.subbodyobjects.simulationTitle;
import dto.worldBuilder.SimulationWorldDetailsDto;
import dto.worldBuilder.SimulationWorldNamesDto;
import dto.worldBuilder.simulationComponents.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.PredictionsMainController;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import utils.HttpClientUtil;
import utils.SimulationWorldListRefresher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static common.CommonResourcesPaths.*;
import static utils.Constants.*;


public class DetailsController {
    private int observableLinesIndex = -1;
    private PredictionsMainController mainController;
    private Stage primaryStage;
    private Map<String, BasePropertyDto> propertyDtoMap;
    private Map<String, StringEntityDto> entitiesDtoMap;
    private Map<String, StringRuleDto> ruleMap;
    private TimerTask simulationWorldListRefresher;
    private Timer detailsTimer;

    @FXML private ListView<simulationTitle> rulesDetailsLeftListLV;
    @FXML private ListView<simulationTitle> entitiesDetailsLeftListLV;
    @FXML private FlowPane rightDetailsFlowPaneListView;
    @FXML private ListView<simulationTitle> environmentDetailsLeftListLV;
    @FXML private ListView<String> avaSimListView;
    @FXML private Label terminationDetailsLabel;
    @FXML private Button newRequestButton;
    @FXML private ComboBox<String> avaSimComboBox;

    private ObservableList<simulationTitle> environmentListViewLeftLines = FXCollections.observableArrayList();
    private ObservableList<simulationTitle> entitiesListViewLeftLines = FXCollections.observableArrayList();
    private ObservableList<simulationTitle> rulesListViewLeftLines = FXCollections.observableArrayList();
    private ObservableList<SimulationDetail> listViewRightLines = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
    }
    public void setMainController(PredictionsMainController mainController) {
        this.mainController = mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setEntitiesDtoMap(Map<String, StringEntityDto> entitiesDtoMap) {
        this.entitiesDtoMap = entitiesDtoMap;
    }

    public void setRuleMap(Map<String, StringRuleDto> ruleMap) {
        this.ruleMap = ruleMap;
    }
    public boolean insertNewLineToLeftEnvironmentListView(String simulationID) {
        this.environmentListViewLeftLines.add(new simulationTitle(simulationID));
        environmentDetailsLeftListLV.setItems(environmentListViewLeftLines);
        return true;
    }
    public boolean insertNewLineToLeftEntitiesListView(String simulationID){
        this.entitiesListViewLeftLines.add(new simulationTitle(simulationID));
        entitiesDetailsLeftListLV.setItems(entitiesListViewLeftLines);
        return true;
    }
    public boolean insertNewLineToLeftRulesListView(String simulationID){
        this.rulesListViewLeftLines.add(new simulationTitle(simulationID));
        rulesDetailsLeftListLV.setItems(rulesListViewLeftLines);
        return true;
    }
    public void updateNewEnvironmentDetailsComponentToRightListView(String name, String type, String value, String from, String to){
        if(name == null){
            name = "";
        }
        if (type == null){
            type = "";
        }
        if (value == null){
            value = "";
        }
        if(from == null){
            from = "";
        }
        createEnvironmentComponent(name, type, value, from, to);
    }
    private void updateNewEntityDetailsComponentToRightListView(StringPropertyDto selectedEntity) {
        createPropertyEntityComponent(selectedEntity);
    }

    private void updateNewRuleDetailsComponentToRightListView(StringRuleDto selectedRule) {
        String activationTickInterval = selectedRule.getActivationTickInterval();
        String activationProbability = selectedRule.getActivationProbability();
        List<StringActionDto> stringActionDtoList = selectedRule.getActions();

        createRuleComponent(activationTickInterval, activationProbability, stringActionDtoList);

    }

    public void setPropertyDtoMap(Map<String, BasePropertyDto> propertyDtoList) {
        this.propertyDtoMap = propertyDtoList;
    }

    public void showCurrPropertyDtoList(){
        cleanLeftListsView();
        environmentDetailsLeftListLV.getItems().clear();
        for (Map.Entry<String, BasePropertyDto> propertyDto:propertyDtoMap.entrySet()) {
            insertNewLineToLeftEnvironmentListView(propertyDto.getKey());
        }
        for(Map.Entry<String,StringEntityDto> entityName:entitiesDtoMap.entrySet()){
            insertNewLineToLeftEntitiesListView(entityName.getKey());
        }
        for(Map.Entry<String, StringRuleDto> rule:ruleMap.entrySet()){
            insertNewLineToLeftRulesListView(rule.getKey());
        }
    }

    @FXML
    void avaSimListViewClicked(MouseEvent event) {

    }

    @FXML
    void avaSimEntryComboBoxClicked() {
        reset();
        getSimulationWorldDetailsProcedure();
    }

    @FXML
    void onAvaSimComboBoxShowing() {
        this.avaSimComboBox.getItems().clear();
        ObservableList<String> items = FXCollections.observableArrayList();
        this.avaSimListView.getItems().forEach(item -> items.add(item));
        this.avaSimComboBox.setItems(items);
    }


    @FXML
    void environmentListViewLineClicked(MouseEvent event) {
        BasePropertyDto selectedEnvironmentVariable = propertyDtoMap.get(this.environmentDetailsLeftListLV.
                getSelectionModel().getSelectedItem().toString());
        cleanRightListView();
        updateNewEnvironmentDetailsComponentToRightListView(selectedEnvironmentVariable.getName(), selectedEnvironmentVariable.getPropertyType(),
                selectedEnvironmentVariable.getValue(), selectedEnvironmentVariable.getFrom(), selectedEnvironmentVariable.getTo());
    }

    @FXML
    void entitiesListViewLineClicked(MouseEvent event) {
        StringEntityDto selectedEntity = entitiesDtoMap.get(this.entitiesDetailsLeftListLV.
                getSelectionModel().getSelectedItem().toString());
        cleanRightListView();
        Map<String, StringPropertyDto> propertyDtoMap = selectedEntity.getPropertyDtoMap();

        for(StringPropertyDto entity:propertyDtoMap.values()){
            updateNewEntityDetailsComponentToRightListView(entity);
        }
    }

    @FXML
    void rulesListViewLineClicked(MouseEvent event) {
        StringRuleDto selectedRule = ruleMap.get(this.rulesDetailsLeftListLV.
                getSelectionModel().getSelectedItem().toString());
        cleanRightListView();
        updateNewRuleDetailsComponentToRightListView(selectedRule);
    }


    @FXML
    void onNewRequestButtonClicked() {
        try {
            this.mainController.moveFromDetailsToRequests();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    private void cleanRightListView() {
        listViewRightLines.clear();
        rightDetailsFlowPaneListView.getChildren().clear();
        rightDetailsFlowPaneListView.getChildren().removeAll();
    }

    private void cleanLeftListsView() {
        environmentListViewLeftLines.clear();
        entitiesListViewLeftLines.clear();
        rulesListViewLeftLines.clear();
        rightDetailsFlowPaneListView.getChildren().clear();
        rightDetailsFlowPaneListView.getChildren().removeAll();
    }


    private void createEnvironmentComponent(String name, String type, String value, String from, String to) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_DETAILS_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            EnvironmentController controller = loader.getController();
            controller.setValues(name, type, value, from, to);
            rightDetailsFlowPaneListView.getChildren().add(gpComponent);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }

    private void createPropertyEntityComponent(StringPropertyDto selectedProperty) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENT_PROP_DETAILS_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            EntityPropertyController controller = loader.getController();
            controller.setValues(selectedProperty.getPropertyName(), selectedProperty.getPropertyType(),
                    selectedProperty.getPropertyInitializationType(), selectedProperty.getPropertyRangeFrom(),
                    selectedProperty.getPropertyRangeTo());
            rightDetailsFlowPaneListView.getChildren().add(gpComponent);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void createRuleComponent(String activationTickInterval, String activationProbability,
                                     List<StringActionDto> actions) {

        for (StringActionDto action : actions){
            try
            {
                FXMLLoader loader = new FXMLLoader();
                URL fxmlUrl = getClass().getResource(RULE_MAIN_FXML_RESOURCE);
                loader.setLocation(fxmlUrl);
                GridPane gpComponent = loader.load();

                MainActionController controller = loader.getController();
                controller.setValues(activationTickInterval, activationProbability, action);
                rightDetailsFlowPaneListView.getChildren().add(gpComponent);
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace(System.out);
            }
        }
    }

    public void reset() {
        environmentDetailsLeftListLV.getItems().clear();
        entitiesDetailsLeftListLV.getItems().clear();
        rulesDetailsLeftListLV.getItems().clear();
        rightDetailsFlowPaneListView.getChildren().clear();
    }

    public void getSimulationWorldDetailsProcedure() {

    String selectedSimulationWorldName = avaSimComboBox.getValue();
        String finalUrl =
                HttpUrl
                        .parse(GET_SIMULATION_WORLD_DETAILS_ENDPOINT)
                        .newBuilder()
                        .addQueryParameter(SIMULATION_WORLD_NAME_PARAM_KEY, selectedSimulationWorldName)
                        .build()
                        .toString();

        HttpClientUtil.runAsync(finalUrl, new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    SimulationWorldDetailsDto simulationWorldDetailsDto = GSON_INSTANCE.fromJson(responseBody, SimulationWorldDetailsDto.class);
                    updateDetailsComponentUI(simulationWorldDetailsDto);

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

    private void updateDetailsComponentUI(SimulationWorldDetailsDto simulationWorldDetailsDto) {
        Platform.runLater(() -> {
            setPropertyDtoMap(simulationWorldDetailsDto.getEnvironmentPropertiesDto().getPropertiesMap());
            setRuleMap(simulationWorldDetailsDto.getRuleMap());
            setEntitiesDtoMap(simulationWorldDetailsDto.getEntityDtoMap());
            showCurrPropertyDtoList();
        });

    }

    public void setActive() {
        startSimulationWorldListRefresher();
    }

    public void setInactive() {
        if (this.simulationWorldListRefresher != null && this.detailsTimer != null) {
            this.simulationWorldListRefresher.cancel();
            this.detailsTimer.cancel();
        }
    }

    private void startSimulationWorldListRefresher() {
        this.simulationWorldListRefresher = new SimulationWorldListRefresher(this::updateSimulationWorldListViewUI);
        this.detailsTimer = new Timer();
        this.detailsTimer.schedule(simulationWorldListRefresher, SIMULATION_WORLD_LIST_REFRESH_RATE, SIMULATION_WORLD_LIST_REFRESH_RATE);
    }

    private void updateSimulationWorldListViewUI(SimulationWorldNamesDto simulationWorldNamesDto) {
        Platform.runLater(() -> {
            ObservableList<String> simulationWorldNamesList = avaSimListView.getItems();
            simulationWorldNamesList.clear();
            simulationWorldNamesList.addAll(simulationWorldNamesDto.getSimulationWorldNames());
        });
    }
}
