package UI.impl.javaFX.tabBody.results.detailsComponent.histogram.byProperty;

import UI.impl.javaFX.tabBody.results.ResultsController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ExecutionResultByPropertyController {

    @FXML
    private ListView<Label> executionDetailsEntitiesListView;

    @FXML
    private ListView<Label> executionDetailsPropertiesListView;

    private ResultsController mainController;

    @FXML
    void entityNameClicked(MouseEvent event) {
        String entityChosen = this.executionDetailsPropertiesListView.
                getSelectionModel().getSelectedItem().getText();
        mainController.propertyChosenInHistogramByProperty(entityChosen);
    }

    public void setMainController(ResultsController mainController) {
        this.mainController = mainController;
    }

    public void setEntitiesList(List<String> entities){
        for(String entity:entities){
            executionDetailsEntitiesListView.getItems().add(new Label(entity));
        }
    }

    public void clearEntityList(){
        executionDetailsEntitiesListView.getItems().clear();
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
