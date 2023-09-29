package ui.tabs.executionsHistory.detailsComponent.histogram.byProperty;

import dto.PropertiesAvgConsistencyDto;
import dto.PropertiesConsistencyDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import ui.tabs.executionsHistory.ExecutionsHistoryController;
import ui.tabs.executionsHistory.detailsComponent.histogram.ExecutionHistogramController;

import java.util.List;

public class ExecutionResultByPropertyController implements ExecutionHistogramController {

    @FXML
    private ListView<Label> executionDetailsLeftEntitiesListView;
    @FXML
    private ListView<Label> executionDetailsRightEntitiesListView;

    @FXML
    private ListView<Label> executionDetailsPropertiesListView;

    private ExecutionsHistoryController mainController;


    @Override
    @FXML
    public void propertyNameClicked(MouseEvent event) {
        this.executionDetailsRightEntitiesListView.getItems().clear();
        String entityChosen = this.executionDetailsLeftEntitiesListView.
                getSelectionModel().getSelectedItem().getText();
        String propertyChosen = this.executionDetailsPropertiesListView.
                getSelectionModel().getSelectedItem().getText();
        mainController.propertyChosenInHistogramByProperty(propertyChosen, entityChosen);
    }

    @Override
    @FXML
    public void entityNameClickedLeftList(MouseEvent event) {
        try {
            this.executionDetailsPropertiesListView.getItems().clear();
            String entityChosen = this.executionDetailsLeftEntitiesListView.
                    getSelectionModel().getSelectedItem().getText();
            mainController.entityChosenInHistogramByProperty(entityChosen);
        }catch (Exception e){
            //name was not selected
        }
    }

    @Override
    public void setMainController(ExecutionsHistoryController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void setLeftEntitiesList(List<String> entities){
        for(String entity:entities){
            executionDetailsLeftEntitiesListView.getItems().add(new Label(entity));
        }
    }

    @Override
    public void setRightEntitiesList(List<String> entities){
        for(String entity:entities){
            executionDetailsRightEntitiesListView.getItems().add(new Label(entity));
        }
    }

    @Override
    public void clearRightEntityList(){
        executionDetailsRightEntitiesListView.getItems().clear();
    }
    @Override
    public void clearLeftEntityList(){
        executionDetailsLeftEntitiesListView.getItems().clear();
    }

    @Override
    public void setPropertiesConsistencyDto(PropertiesConsistencyDto propertiesConsistencyDto) {

    }

    @Override
    public void setPropertiesAvgConsistencyDto(PropertiesAvgConsistencyDto propertiesAvgConsistencyDto) {

    }

    @Override
    public void setPropertiesList(List<String> properties){
        for(String entity:properties){
            executionDetailsPropertiesListView.getItems().add(new Label(entity));
        }
    }

    @Override
    public void clearPropertyList(){
        executionDetailsPropertiesListView.getItems().clear();
    }

}
