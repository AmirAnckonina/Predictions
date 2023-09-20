package UI.impl.javaFX.tabBody.results.detailsComponent.entity;

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

    public void setValues(String entityName, String numOfOriginalEntities, String numOfCurrEntities){
        this.name.set(entityName);
        this.numOfEntitiesInstance.set(numOfCurrEntities);
    }

}
