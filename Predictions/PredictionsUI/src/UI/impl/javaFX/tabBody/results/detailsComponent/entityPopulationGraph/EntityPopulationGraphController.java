package UI.impl.javaFX.tabBody.results.detailsComponent.entityPopulationGraph;

import dto.EntityPopulationOvertimeDto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Map;

public class EntityPopulationGraphController {

    @FXML private LineChart<Number, Number> entityPopulationLineChart;
    private Stage primaryStage;

    @FXML
    private void initialize() {
        //NumberAxis xAxis = new NumberAxis();
       // NumberAxis yAxis = new NumberAxis();
        //xAxis.setLabel("Tick");
       // yAxis.setLabel("Population");
        //this.entityPopulationLineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        //this.entityPopulationLineChart.getData().
       // this.entityPopulationLineChart = new LineChart<>(xAxis, yAxis);
        // Configure the existing entityPopulationLineChart
       // entityPopulationLineChart.setCreateSymbols(true); // To display data points as symbols
       // entityPopulationLineChart.setLegendVisible(true); // To display the legend
        // Set the axes to the chart
      //  entityPopulationLineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
      //  entityPopulationLineChart.setAnimated(false);
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void initEntityPopulationLineChart(EntityPopulationOvertimeDto entityPopulationOvertimeDto) {

        entityPopulationLineChart.getData().clear();

        entityPopulationOvertimeDto
                .getEntityPopulationOvertimeMap()
                .forEach((entityName, tickEntityPopulationMap) -> {
                    XYChart.Series<Number, Number> series = new XYChart.Series<>();
                    series.setName(entityName);
                    for (Map.Entry<Integer, Integer> entry : tickEntityPopulationMap.entrySet()) {
                        Integer tickNo = entry.getKey();
                        Integer populationInTick = entry.getValue();
                        series.getData().add(new XYChart.Data<>(tickNo, populationInTick));
                        if (tickNo == 2000) {
                            break;
                        }
                    }

                    this.entityPopulationLineChart.getData().add(series);
                });
    }
//           Platform.runLater(() -> {
//                Scene scene  = new Scene(entityPopulationLineChart,800,600);
//                this.primaryStage.setScene(scene);
//                this.primaryStage.show();
//           });
}

