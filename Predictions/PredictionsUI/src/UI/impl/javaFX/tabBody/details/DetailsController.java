package UI.impl.javaFX.tabBody.details;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.details.subbodyobjects.SimulationDetail;
import UI.impl.javaFX.tabBody.details.subbodyobjects.environment.EnvironmentController;
import UI.impl.javaFX.tabBody.details.subbodyobjects.simulationTitle;
import dto.*;
import enums.ActionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;

import java.net.URL;
import java.util.List;
import java.util.Map;

import static UI.impl.javaFX.common.CommonResourcesPaths.ENV_DETAILS_FXML_RESOURCE;


public class DetailsController {
    private int observableLinesIndex = -1;
    private PredictionsMainController mainController;
    private DetailsModel detailsModel;
    private Stage primaryStage;
    private SimulatorManager simulatorManager;
    private Map<String, BasePropertyDto> propertyDtoMap;
    private Map<String, StringEntityDto> entitiesDtoMap;
    private Map<String, StringRuleDto> ruleMap;
    private Map<String, String> envPropertiesInfo;
    private SimulationDetailsDto simulationDetailsDto;

    @FXML
    private ListView<simulationTitle> rulesDetailsLeftListLV;
    @FXML
    private ListView<simulationTitle> entitiesDetailsLeftListLV;
    @FXML
    private ScrollPane rightDetailsScrollPane;
    @FXML
    private ListView<simulationTitle> environmentDetailsLeftListLV;
    @FXML
    private Label terminationDetailsLabel;


    private ObservableList<simulationTitle> environmentListViewLeftLines = FXCollections.observableArrayList();
    private ObservableList<simulationTitle> entitiesListViewLeftLines = FXCollections.observableArrayList();
    private ObservableList<simulationTitle> rulesListViewLeftLines = FXCollections.observableArrayList();
    private ObservableList<SimulationDetail> listViewRightLines = FXCollections.observableArrayList();

    public void setMainController(PredictionsMainController mainController) {
        this.mainController = mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setSimulationDetailsDto(SimulationDetailsDto simulationDetailsDto) {
        this.simulationDetailsDto = simulationDetailsDto;
    }

    public void setEntitiesDtoMap(Map<String, StringEntityDto> entitiesDtoMap) {
        this.entitiesDtoMap = entitiesDtoMap;
    }

    public void setRuleMap(Map<String, StringRuleDto> ruleMap) {
        this.ruleMap = ruleMap;
    }

    public void setEnvPropertiesInfo(Map<String, String> envPropertiesInfo) {
        this.envPropertiesInfo = envPropertiesInfo;
    }

    public void loadFileButtonClicked(){
        this.environmentDetailsLeftListLV.setItems(this.environmentListViewLeftLines);
    }

    public boolean insertNewLineToLeftEnvironmentListView(String simulationID){
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
        createEntityComponent(selectedEntity);
    }

    private void updateNewRuleDetailsComponentToRightListView(StringRuleDto selectedRule) {
        String ruleName = selectedRule.getName();
        String activationTickInterval = selectedRule.getActivationTickInterval();
        String activationProbability = selectedRule.getActivationProbability();
        List<StringActionDto> stringActionDtoList = selectedRule.getActions();

        for(StringActionDto action:stringActionDtoList){
            createRuleComponent(ActionType.valueOf((action.getType()).toUpperCase()), action);
        }
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
    void environmentListViewLineClicked(MouseEvent event) {
        BasePropertyDto selectedEnvironmentVariable = propertyDtoMap.get(this.environmentDetailsLeftListLV.
                getSelectionModel().getSelectedItem().toString());
        cleanRightListView();
        updateNewEnvironmentDetailsComponentToRightListView(selectedEnvironmentVariable.getName(), selectedEnvironmentVariable.getPropertyType(),
                selectedEnvironmentVariable.getValue(), selectedEnvironmentVariable.getFrom(), selectedEnvironmentVariable.getTo());
    }

    @FXML
    void entitiesListViewLineClicked(MouseEvent event) {
        StringEntityDto selectedEntity = entitiesDtoMap.get(this.environmentDetailsLeftListLV.
                getSelectionModel().getSelectedItem().toString());
        cleanRightListView();
        Map<String, StringPropertyDto> propertyDtoMap = selectedEntity.getPropertyDtoMap();

        for(StringPropertyDto entity:propertyDtoMap.values()){
            updateNewEntityDetailsComponentToRightListView(entity);
        }
    }

    @FXML
    void rulesListViewLineClicked(MouseEvent event) {
        StringRuleDto selectedRule = ruleMap.get(this.environmentDetailsLeftListLV.
                getSelectionModel().getSelectedItem().toString());
        cleanRightListView();
        updateNewRuleDetailsComponentToRightListView(selectedRule);
    }

    private void cleanRightListView() {
        listViewRightLines.clear();
    }

    private void cleanLeftListsView() {
        environmentListViewLeftLines.clear();
        entitiesListViewLeftLines.clear();
        rulesListViewLeftLines.clear();
    }

    public void setDetailsModel(DetailsModel detailsModel) {
        this.detailsModel = detailsModel;
    }

    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
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
            rightDetailsScrollPane.setContent(gpComponent);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }

    private void createEntityComponent(StringPropertyDto selectedEntity) {
    }

    private void createRuleComponent(ActionType actionType, StringActionDto action) {

        switch (actionType) {
            case INCREASE:
            case DECREASE:
            case SET:
            case REPLACE:
            case KILL:
                break;
            case CALCULATION:
            case DIVIDE:
            case MULTIPLY:
                break;
            case CONDITION:
                //simple type
                //multiple type
                break;
            case PROXIMITY:
                break;
        }

        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_DETAILS_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            GridPane gpComponent = loader.load();

            EnvironmentController controller = loader.getController();
//            controller.setValues(name, type, value, from, to);
            rightDetailsScrollPane.setContent(gpComponent);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }


    }

}
