package UI.impl.javaFX.tabBody.results.detailsComponent.entityPopulationGraph;

import dto.EntityPopulationOvertimeDto;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class EntityPopulationGraphController {

    @FXML private LineChart<?, ?> entityPopulationLineChart;

    @FXML
    private void initialize() {

    }

    public void createEntityPopulationLineChart(EntityPopulationOvertimeDto entityPopulationOvertimeDto) {

        entityPopulationOvertimeDto
                .getEntityPopulationOvertimeMap()
                .forEach((tickNo, tickEntityPopulationMap) -> {

                    XYChart.Series series = new XYChart.Series();
                    series.setName("Tick " + tickNo);
                    tickEntityPopulationMap.forEach((entityName, populationInTick) -> {
                        series.getData().add(new XYChart.Data(tickNo, populationInTick));
                    });

                    this.entityPopulationLineChart.getData().add(series);
                });
    }

}
