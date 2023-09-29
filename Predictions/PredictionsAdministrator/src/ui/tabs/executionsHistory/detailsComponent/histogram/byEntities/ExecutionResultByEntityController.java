package ui.tabs.executionsHistory.detailsComponent.histogram.byEntities;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class ExecutionResultByEntityController {

    @FXML
    private ListView<Label> entitiesListLV;

    public void setList(List<String> entities){
        for(String entity:entities){
            entitiesListLV.getItems().add(new Label(entity));
        }
    }

    public void cleanList(){
        entitiesListLV.getItems().clear();
    }

}
