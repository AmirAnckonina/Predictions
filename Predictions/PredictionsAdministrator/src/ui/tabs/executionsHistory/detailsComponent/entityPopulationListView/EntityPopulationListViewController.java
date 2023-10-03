package ui.tabs.executionsHistory.detailsComponent.entityPopulationListView;

import dto.simulationInfo.EntityPopulationOvertimeDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class EntityPopulationListViewController {

    @FXML
    private ListView<Label> entPopListView;

    public void initEntityPopulationListView(EntityPopulationOvertimeDto entityPopulationOvertimeDto) {

        entPopListView.getItems().clear();
        final int[] entityCun = {0};
        entityPopulationOvertimeDto
                .getEntityPopulationOvertimeMap()
                .forEach((entityName, tickEntityPopulationMap) -> {
                    tickEntityPopulationMap.forEach((tickNo, populationInTick) -> {
                        if (tickNo != 0) {
                            Label label = new Label(entityName + ": tick: " + tickNo + " population: " + populationInTick);
                            this.entPopListView.getItems().add((tickNo * entityCun[0]) + tickNo - 1, label);
                        }
                    });
                    entityCun[0]++;
                });
    }

}
