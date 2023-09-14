package UI.impl.javaFX.tabBody.details.subbodyobjects.environment;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EnvironmentController extends EnvironmentModel{

    @FXML
    private Label environmentName;

    @FXML
    private Label environmentType;

    @FXML
    private Label environmentValue;

    @FXML
    private Label environmentFromRange;

    @FXML
    private Label environmentToRange;

    @FXML
    private void initialize() {
        environmentName.textProperty().bind(name);
        environmentType.textProperty().bind(type);
        environmentValue.textProperty().bind(value);
        environmentFromRange.textProperty().bind(from);
        environmentToRange.textProperty().bind(to);
    }

    public void setValues(String name, String type, String value,
                          String from,  String to){
        this.name.set(name);
        this.type.set(type);
        this.value.set(value);
        this.from.set(from);
        this.to.set(to);
    }

}
