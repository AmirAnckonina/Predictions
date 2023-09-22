package UI.impl.javaFX.tabBody.results.detailsComponent.histogram.byStatistic;

import UI.impl.javaFX.tabBody.results.ResultsController;
import dto.PropertiesConsistencyDto;
import dto.SimulationDetailsDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import UI.impl.javaFX.tabBody.results.ResultsController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ExecutionResultStatisticByPropertyController {

    @FXML
    private ListView<Label> executionDetailsLeftEntitiesListView;
    @FXML
    private ListView<Label> executionDetailsRightEntitiesListView;

    @FXML
    private ListView<Label> executionDetailsPropertiesListView;

    private PropertiesConsistencyDto propertiesConsistencyDto;

    private SimulationDetailsDto

    private ResultsController mainController;


    @FXML
    void propertyNameClicked(MouseEvent event) {
        this.executionDetailsRightEntitiesListView.getItems().clear();
        String entityChosen = this.executionDetailsLeftEntitiesListView.
                getSelectionModel().getSelectedItem().getText();
        String propertyChosen = this.executionDetailsPropertiesListView.
                getSelectionModel().getSelectedItem().getText();
        Double res = propertiesConsistencyDto.getPropertiesConsistencyMap().get(entityChosen).get(propertyChosen);
        List<String> info = new ArrayList<>();

        info.add(propertyChosen + ": " + res.toString());
        info.add(propertyChosen + ": " + res.toString());
    }

    @FXML
    void entityNameClickedLeftList(MouseEvent event) {
        this.executionDetailsPropertiesListView.getItems().clear();
        String entityChosen = this.executionDetailsLeftEntitiesListView.
                getSelectionModel().getSelectedItem().getText();
        mainController.entityChosenInHistogramByProperty(entityChosen);
    }

    public void setPropertiesConsistencyDto(PropertiesConsistencyDto propertiesConsistencyDto) {
        this.propertiesConsistencyDto = propertiesConsistencyDto;
    }

    public void setMainController(ResultsController mainController) {
        this.mainController = mainController;
    }

    public void setLeftEntitiesList(List<String> entities){
        for(String entity:entities){
            executionDetailsLeftEntitiesListView.getItems().add(new Label(entity));
        }
    }

    public void setRightEntitiesList(List<String> entities){
        for(String entity:entities){
            executionDetailsRightEntitiesListView.getItems().add(new Label(entity));
        }
    }

    public void clearRightEntityList(){
        executionDetailsRightEntitiesListView.getItems().clear();
    }
    public void clearLeftEntityList(){
        executionDetailsLeftEntitiesListView.getItems().clear();
    }

    public void setPropertiesList(List<String> properties){
        for(String entity:properties){
            executionDetailsPropertiesListView.getItems().add(new Label(entity));
        }
    }

    public void clearPropertyList(){
        executionDetailsPropertiesListView.getItems().clear();
    }

}



