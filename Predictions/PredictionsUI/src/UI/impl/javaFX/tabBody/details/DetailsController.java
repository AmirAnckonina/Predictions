package UI.impl.javaFX.tabBody.details;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.details.subbodyobjects.SimulationDetail;
import UI.impl.javaFX.tabBody.details.subbodyobjects.environment.EnvironmentController;
import UI.impl.javaFX.tabBody.details.subbodyobjects.simulationTitle;
import dto.BasePropertyDto;
import dto.SimulationDetailsDto;
import dto.StringRule;
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
import java.util.Map;

import static UI.impl.javaFX.common.CommonResourcesPaths.ENV_DETAILS_FXML_RESOURCE;


public class DetailsController {
    private int observableLinesIndex = -1;
    private PredictionsMainController mainController;
    private DetailsModel detailsModel;
    private Stage primaryStage;
    private SimulatorManager simulatorManager;
    private Map<String, BasePropertyDto> propertyDtoMap;
    private Map<String, String> entitiesDtoMap;
    private Map<String, StringRule> ruleMap;
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
    public void updateNewComponentToRightListView(String name, String type, String value, String from, String to){
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

    public void setPropertyDtoMap(Map<String, BasePropertyDto> propertyDtoList) {
        this.propertyDtoMap = propertyDtoList;
    }

    public void showCurrPropertyDtoList(){
        cleanLeftListsView();
        environmentDetailsLeftListLV.getItems().clear();
        for (Map.Entry<String, BasePropertyDto> propertyDto : propertyDtoMap.entrySet()) {
            insertNewLineToLeftEnvironmentListView(propertyDto.getKey());
        }
        for(Map.Entry<String,String> entityName: entitiesDtoMap.entrySet()){
            insertNewLineToLeftEntitiesListView(entityName.getKey());
        }
        for(Map.Entry<String,StringRule> rule: ruleMap.entrySet()){
            insertNewLineToLeftRulesListView(rule.getKey());
        }
        simulationDetailsDto.getRulesActions();

    }

    @FXML
    void environmentListViewLineClicked(MouseEvent event) {
        BasePropertyDto selectedSimulation = propertyDtoMap.get(this.environmentDetailsLeftListLV.
                getSelectionModel().getSelectedItem().toString());
        cleanRightListView();

        try {
            updateNewComponentToRightListView(selectedSimulation.getName(), selectedSimulation.getPropertyType(),
                    selectedSimulation.getValue(), selectedSimulation.getFrom(), selectedSimulation.getTo());
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace(System.out);
        }
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

    private void createRuleComponent(ActionType actionType) {

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
