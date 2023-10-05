package utils;

import dto.worldBuilder.SimulationWorldNamesDto;
import javafx.beans.property.SimpleBooleanProperty;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.TimerTask;
import java.util.function.Consumer;

import static utils.Constants.GSON_INSTANCE;
import static utils.Constants.GET_SIMULATION_WORLD_NAMES_LIST_ENDPOINT;

public class SimulationWorldListRefresher extends TimerTask {

    private final Consumer<SimulationWorldNamesDto> simulationWorldNamesDtoConsumer;
    private final SimpleBooleanProperty nodeInListViewIsSelected;

    public SimulationWorldListRefresher(SimpleBooleanProperty simulationNodeInListViewIsSelected, Consumer<SimulationWorldNamesDto> simulationWorldNamesDtoConsumer) {
       this.nodeInListViewIsSelected = simulationNodeInListViewIsSelected;
        this.simulationWorldNamesDtoConsumer = simulationWorldNamesDtoConsumer;
    }

    @Override
    public void run() {
        if (nodeInListViewIsSelected.get()) {
            return;
        }

        HttpClientUtil.runAsync(GET_SIMULATION_WORLD_NAMES_LIST_ENDPOINT, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                try {
                    SimulationWorldNamesDto simulationWorldNamesDto = GSON_INSTANCE.fromJson(json, SimulationWorldNamesDto.class);
                    simulationWorldNamesDtoConsumer.accept(simulationWorldNamesDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
