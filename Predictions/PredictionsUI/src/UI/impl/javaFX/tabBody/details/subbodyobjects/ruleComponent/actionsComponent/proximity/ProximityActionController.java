package UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.proximity;

import UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent.actionsComponent.model.ProximityModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProximityActionController extends ProximityModel {

    @FXML
    private Label sourceEntity;

    @FXML
    private Label destinationEntity;

    @FXML
    private Label environmentDepth;

    @FXML
    private Label numOfActions;

    @FXML
    private void initialize() {
        sourceEntity.textProperty().bind(sourceEntityName);
        destinationEntity.textProperty().bind(destinationEntityName);
        environmentDepth.textProperty().bind(depth.asString());
        numOfActions.textProperty().bind(numberOfActions.asString());
    }

    public void setValues(SimpleStringProperty sourceEntityName, SimpleStringProperty destinationEntityName,
                          SimpleIntegerProperty depth, SimpleIntegerProperty numberOfActions){
        this.sourceEntityName = sourceEntityName;
        this.destinationEntityName = destinationEntityName;
        this.depth = depth;
        this.numberOfActions = numberOfActions;
    }
}
