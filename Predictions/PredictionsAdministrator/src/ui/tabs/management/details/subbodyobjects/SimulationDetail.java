package ui.tabs.management.details.subbodyobjects;

import javafx.beans.property.SimpleStringProperty;

public class SimulationDetail {
    private final SimpleStringProperty simulationDetail;

    public SimulationDetail(String simulationDetail) {
        this.simulationDetail = new SimpleStringProperty(simulationDetail);
    }

    public String getName() {
        return this.simulationDetail.get();
    }

    public void setSimulationDetail(String simulationDetail) {
        this.simulationDetail.set(simulationDetail);
    }

    @Override
    public String toString() {
        return getName();
    }
}
