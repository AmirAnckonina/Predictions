package UI.impl.javaFX.tabBody.results.detailsComponent.histogram.byStatistic;

import UI.impl.javaFX.tabBody.results.ResultsController;
import UI.impl.javaFX.tabBody.results.detailsComponent.histogram.ExecutionResultController;
import dto.PropertiesAvgConsistencyDto;
import dto.PropertiesConsistencyDto;
import dto.SimulationDetailsDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import UI.impl.javaFX.tabBody.results.ResultsController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ExecutionResultStatisticByPropertyController implements ExecutionResultController {

    @FXML
    private ListView<Label> executionDetailsLeftEntitiesListView;
    @FXML
    private ListView<Label> executionDetailsRightEntitiesListView;

    @FXML
    private ListView<Label> executionDetailsPropertiesListView;

    private PropertiesConsistencyDto propertiesConsistencyDto;

    private PropertiesAvgConsistencyDto propertiesAvgConsistencyDto;

    private SimulationDetailsDto simulationDetailsDto;

    private ResultsController mainController;


    @Override
    @FXML
    public void propertyNameClicked(MouseEvent event) {
        try {
            this.executionDetailsRightEntitiesListView.getItems().clear();
            String entityChosen = this.executionDetailsLeftEntitiesListView.
                    getSelectionModel().getSelectedItem().getText();
            String propertyChosen = this.executionDetailsPropertiesListView.
                    getSelectionModel().getSelectedItem().getText();
            Double propertiesConsistency = propertiesConsistencyDto.getPropertiesConsistencyMap().get(entityChosen).get(propertyChosen);
            Double propertiesAvgConsistency = propertiesAvgConsistencyDto.getPropertiesConsistencyMap().get(entityChosen).get(propertyChosen);
            List<String> info = new ArrayList<>();

//        DecimalFormat df = new DecimalFormat("#.##");
//        String formatted = df.format(propertiesConsistency.toString());

            info.add(propertyChosen + "'s consistency: " + propertiesConsistency.toString());
            info.add(propertyChosen + "'s avg: " + propertiesAvgConsistency.toString());
            setRightEntitiesList(info);
        }catch (Exception e){
            //properties are missing
        }
    }

    @Override
    @FXML
    public void entityNameClickedLeftList(MouseEvent event) {
        this.executionDetailsPropertiesListView.getItems().clear();
        String entityChosen = this.executionDetailsLeftEntitiesListView.
                getSelectionModel().getSelectedItem().getText();
        mainController.entityChosenInHistogramByProperty(entityChosen);
    }

    @Override
    public void setPropertiesConsistencyDto(PropertiesConsistencyDto propertiesConsistencyDto) {
        this.propertiesConsistencyDto = propertiesConsistencyDto;
    }

    @Override
    public void setPropertiesAvgConsistencyDto(PropertiesAvgConsistencyDto propertiesAvgConsistencyDto) {
        this.propertiesAvgConsistencyDto = propertiesAvgConsistencyDto;
    }


    @Override
    public void setMainController(ResultsController mainController) {
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



