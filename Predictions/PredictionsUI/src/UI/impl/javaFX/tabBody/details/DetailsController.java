package UI.impl.javaFX.tabBody.details;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.details.subbodyobjects.SimulationDetail;
import UI.impl.javaFX.tabBody.details.subbodyobjects.simulationTitle;
import dto.BasePropertyDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import simulator.mainManager.api.SimulatorManager;

import java.util.Map;


public class DetailsController {
    private int observableLinesIndex = -1;
    private PredictionsMainController mainController;
    private DetailsModel detailsModel;
    private Stage primaryStage;
    private SimulatorManager simulatorManager;
    private Map<String, BasePropertyDto> propertyDtoMap;

    @FXML
    private ListView<SimulationDetail> detailsRightListLV;

    @FXML
    private ListView<simulationTitle> detailsLeftListLV;


    private ObservableList<simulationTitle> listViewLeftLines = FXCollections.observableArrayList();
    private ObservableList<SimulationDetail> listViewRightLines = FXCollections.observableArrayList();

    public void setMainController(PredictionsMainController mainController) {
        this.mainController = mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void loadFileButtonClicked(){
        this.detailsLeftListLV.setItems(this.listViewLeftLines);
    }

    public boolean insertNewLineToLeftListView(String simulationID){
        this.listViewLeftLines.add(new simulationTitle(simulationID));
        detailsLeftListLV.setItems(listViewLeftLines);
        return true;
    }
    public boolean insertNewLineToRightListView(String simulationDetail){
        this.listViewRightLines.add(new SimulationDetail(simulationDetail));
        detailsRightListLV.setItems(listViewRightLines);
        return true;
    }

    public void setPropertyDtoMap(Map<String, BasePropertyDto> propertyDtoList) {
        this.propertyDtoMap = propertyDtoList;
    }

    public void showCurrPropertyDtoList(){

        for (Map.Entry<String, BasePropertyDto> propertyDto : propertyDtoMap.entrySet()) {
            insertNewLineToLeftListView(propertyDto.getKey());
        }
    }

    @FXML
    void listViewLineClicked(MouseEvent event) {
        BasePropertyDto selectedSimulation = propertyDtoMap.get(this.detailsLeftListLV.
                getSelectionModel().getSelectedItem().toString());
        cleanRightListView();

        insertNewLineToRightListView("Name: " + selectedSimulation.getName());
        insertNewLineToRightListView("Type: " + selectedSimulation.getPropertyType());
        insertNewLineToRightListView("Value: " + selectedSimulation.getValue());
        insertNewLineToRightListView("Range: from " + selectedSimulation.getFrom() + "to " + selectedSimulation.getTo());
    }

    private void cleanRightListView() {
        listViewRightLines.clear();
        detailsRightListLV.setItems(listViewRightLines);
    }

    private void cleanLeftListView() {
        listViewLeftLines.clear();
        detailsLeftListLV.setItems(listViewLeftLines);
    }

    public void setDetailsModel(DetailsModel detailsModel) {
        this.detailsModel = detailsModel;
    }

    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
    }

}
