package ui.tabs.management.details.subbodyobjects.entity.property;

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

    @FXML
    private void initialize() {
        propertyName.textProperty().bind(name);
        type.textProperty().bind(propertyType);
        initType.textProperty().bind(initializationType);
        rangeFrom.textProperty().bind(from);
        rangeTo.textProperty().bind(to);
    }

    public void setValues(String name, String propertyType, String initializationType, String from, String to){
        this.name.set(name);
        this.propertyType.set(propertyType);
        this.initializationType.set(initializationType);
        this.from.set(from);
        this.to.set(to);
    }
}
