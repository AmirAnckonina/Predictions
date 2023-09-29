package ui.tabs.management.details;

import dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;
import ui.mainScene.MainController;
import ui.tabs.management.details.subbodyobjects.SimulationDetail;
import ui.tabs.management.details.subbodyobjects.entity.property.EntityPropertyController;
import ui.tabs.management.details.subbodyobjects.environment.EnvironmentController;
import ui.tabs.management.details.subbodyobjects.ruleComponent.MainActionController;
import ui.tabs.management.details.subbodyobjects.simulationTitle;

import java.net.URL;
import java.util.List;
import java.util.Map;

import static ui.common.CommonResourcesPaths.*;


public class DetailsController {
    private int observableLinesIndex = -1;
    private MainController mainController;
    private DetailsModel detailsModel;
    private Stage primaryStage;
    private SimulatorManager simulatorManager;
    private Map<String, BasePropertyDto> propertyDtoMap;
    private Map<String, StringEntityDto> entitiesDtoMap;
    private Map<String, StringRuleDto> ruleMap;
    private Map<String, String> envPropertiesInfo;
    private SimulationWorldDetailsDto simulationDetailsDto;

    @FXML
    private ListView<simulationTitle> rulesDetailsLeftListLV;
    @FXML
    private ListView<simulationTitle> entitiesDetailsLeftListLV;
    @FXML
    private FlowPane rightDetailsFlowPaneListView;
    @FXML
    private ListView<simulationTitle> environmentDetailsLeftListLV;
    @FXML
    private Label terminationDetailsLabel;


    private ObservableList<simulationTitle> environmentListViewLeftLines = FXCollections.observableArrayList();
    private ObservableList<simulationTitle> entitiesListViewLeftLines = FXCollections.observableArrayList();
    private ObservableList<simulationTitle> rulesListViewLeftLines = FXCollections.observableArrayList();
    private ObservableList<SimulationDetail> listViewRightLines = FXCollections.observableArrayList();

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setSimulationDetailsDto(SimulationWorldDetailsDto simulationDetailsDto) {
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
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }

    private void createRuleComponent(String activationTickInterval, String activationProbability,
                                     List<StringActionDto> actions) {

        for(StringActionDto action:actions){
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

    public void setTerminationDto(String terminationInfo) {
        String value = terminationInfo.substring(terminationInfo.indexOf("{") + 1, terminationInfo.indexOf("}"));
        String[] terminationOptions = value.split(",");
        String firstOption = terminationOptions[0].replaceAll("=",": ").replaceAll("null", "-")
                .replaceAll("ticksTermination", "Ticks termination");
        String secondOption = terminationOptions[1].replaceAll("=",": ").replaceAll("null", "-")
                .replaceAll("secondsTermination", "Time termination");
        this.terminationDetailsLabel.textProperty().set(firstOption + "   " + secondOption);

        //ticksTermination=null

        //secondsTermination
    }

    public void reset() {
        environmentDetailsLeftListLV.getItems().clear();
        entitiesDetailsLeftListLV.getItems().clear();
        rulesDetailsLeftListLV.getItems().clear();
        rightDetailsFlowPaneListView.getChildren().clear();
    }
}
