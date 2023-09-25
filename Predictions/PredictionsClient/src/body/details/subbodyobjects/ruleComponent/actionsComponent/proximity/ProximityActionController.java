package body.details.subbodyobjects.ruleComponent.actionsComponent.proximity;

import body.details.subbodyobjects.ruleComponent.actionsComponent.model.ProximityModel;
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
        environmentDepth.textProperty().bind(depth);
        numOfActions.textProperty().bind(numberOfActions);
    }

    public void setValues(String sourceEntityName, String destinationEntityName,
                          String depth, String numberOfActions){
        this.sourceEntityName.set(sourceEntityName);
        this.destinationEntityName.set(destinationEntityName);
        this.depth.set(depth);
        this.numberOfActions.set(numberOfActions);
    }
}
