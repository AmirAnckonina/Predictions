package utils;


import com.google.gson.reflect.TypeToken;
import dto.SimulationRequestDetailsDto;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.function.Consumer;

import static utils.Constants.GET_SIMULATION_REQUESTS_LIST_ENDPOINT;
import static utils.Constants.GSON_INSTANCE;

public class SimulationRequestsListRefresher extends TimerTask {
    private final Consumer<List<SimulationRequestDetailsDto>> simulationRequestsListConsumer;

    public SimulationRequestsListRefresher(Consumer<List<SimulationRequestDetailsDto>> simulationRequestsListConsumer) {
        this.simulationRequestsListConsumer = simulationRequestsListConsumer;
    }

    @Override
    public void run() {
        HttpClientUtil.runAsync(GET_SIMULATION_REQUESTS_LIST_ENDPOINT, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Type requestDetailsListType = new TypeToken<ArrayList<SimulationRequestDetailsDto>>(){}.getType();
                    ArrayList<SimulationRequestDetailsDto> simulationRequestDetailsDtoList = GSON_INSTANCE.fromJson(responseBody, requestDetailsListType);
                    simulationRequestsListConsumer.accept(simulationRequestDetailsDtoList);

                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request GET_SIMULATION_REQUESTS_LIST_ENDPOINT...:(");
                }
            }
        });

    }
}
