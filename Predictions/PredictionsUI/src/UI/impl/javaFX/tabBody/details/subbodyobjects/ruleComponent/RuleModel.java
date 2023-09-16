package UI.impl.javaFX.tabBody.details.subbodyobjects.ruleComponent;

import javafx.beans.property.SimpleStringProperty;

public class RuleModel {
    protected SimpleStringProperty name;
    protected SimpleStringProperty main;
    protected SimpleStringProperty secondary;
    protected SimpleStringProperty type;


    public RuleModel() {
        this.name = new SimpleStringProperty();
        this.main = new SimpleStringProperty();
        this.secondary = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
    }
}
