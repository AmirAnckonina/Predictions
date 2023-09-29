package ui.tabs.executionsHistory.detailsComponent.entity;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EntityComponentController extends EntityModule {

    @FXML
    private Label entityNameLbl;

    @FXML
    private Label entityCountLbl;

    @FXML
    public void initialize(){
        entityNameLbl.textProperty().bind(name);
        entityCountLbl.textProperty().bind(numOfEntitiesInstance);
    }

    public void setValues(String entityName, String numOfCurrEntities, String numOfOriginalEntities){
        this.name.set(entityName);
        this.numOfEntitiesInstance.set(numOfCurrEntities);
    }

}
