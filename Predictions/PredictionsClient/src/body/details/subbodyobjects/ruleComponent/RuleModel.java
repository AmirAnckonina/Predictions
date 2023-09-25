package body.details.subbodyobjects.ruleComponent;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RuleModel {
    protected SimpleStringProperty main;
    protected SimpleStringProperty secondary;
    protected SimpleStringProperty type;
    protected SimpleStringProperty tikInterv;
    protected SimpleStringProperty prob;

    public RuleModel() {
        this.main = new SimpleStringProperty();
        this.secondary = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.tikInterv = new SimpleStringProperty();
        this.prob = new SimpleStringProperty();
    }
}
