package body.details.subbodyobjects.ruleComponent.actionsComponent.generic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GenericActionController extends GenericModel {

    @FXML
    private Label actionValue;

    @FXML
    private void initialize() {
        actionValue.textProperty().bind(value);
    }

    public void setValues(String value){
        this.value.set(value);
    }

}
