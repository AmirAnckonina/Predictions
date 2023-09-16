package UI.impl.javaFX.tabBody.details.subbodyobjects.entity.property;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EntityPropertyController extends EntityPropertyModel {

    @FXML
    private Label propertyName;

    @FXML
    private Label type;

    @FXML
    private Label initType;

    @FXML
    private Label rangeFrom;

    @FXML
    private Label rangeTo;

    private void initialize() {
        propertyName.textProperty().bind(name);
        type.textProperty().bind(propertyType);
        initType.textProperty().bind(initializationType);
        rangeFrom.textProperty().bind(from);
        rangeTo.textProperty().bind(to);
    }

    public void setValues(SimpleStringProperty name, SimpleStringProperty propertyType,
                          SimpleStringProperty depth, SimpleStringProperty from,
                          SimpleStringProperty to){
        this.name = name;
        this.propertyType = propertyType;
        this.initializationType = depth;
        this.from = from;
        this.to = to;
    }
}
