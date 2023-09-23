package UI.impl.javaFX.tabBody.results.detailsComponent.histogram.byProperty;

import UI.impl.javaFX.tabBody.results.ResultsController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ExecutionResultByPropertyController {

    @FXML
    private ListView<Label> executionDetailsLeftEntitiesListView;
    @FXML
    private ListView<Label> executionDetailsRightEntitiesListView;

    @FXML
    private ListView<Label> executionDetailsPropertiesListView;

    private ResultsController mainController;


    @FXML
    void propertyNameClicked(MouseEvent event) {
        this.executionDetailsRightEntitiesListView.getItems().clear();
        String entityChosen = this.executionDetailsLeftEntitiesListView.
                getSelectionModel().getSelectedItem().getText();
        String propertyChosen = this.executionDetailsPropertiesListView.
                getSelectionModel().getSelectedItem().getText();
        mainController.propertyChosenInHistogramByProperty(propertyChosen, entityChosen);
    }

    @FXML
    void entityNameClickedLeftList(MouseEvent event) {
        try {
            this.executionDetailsPropertiesListView.getItems().clear();
            String entityChosen = this.executionDetailsLeftEntitiesListView.
                    getSelectionModel().getSelectedItem().getText();
            mainController.entityChosenInHistogramByProperty(entityChosen);
        }catch (Exception e){
            //name was not selected
        }
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
