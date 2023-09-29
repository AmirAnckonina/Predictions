package ui.tabs.executionsHistory.detailsComponent;

import javafx.beans.property.SimpleStringProperty;

public class ResultsModel {
    protected SimpleStringProperty simulationID;
    protected SimpleStringProperty numOfTicks;
    protected SimpleStringProperty timeCounter;
    protected SimpleStringProperty status;

    public ResultsModel() {
        this.simulationID = new SimpleStringProperty();
        this.numOfTicks = new SimpleStringProperty();
        this.timeCounter = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
    }
}
