package utils;


import com.google.gson.reflect.TypeToken;
import dto.orderRequest.SimulationOrderRequestDetailsDto;
import javafx.beans.property.SimpleBooleanProperty;
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
    private final Consumer<List<SimulationOrderRequestDetailsDto>> simulationRequestsListConsumer;


    public SimulationRequestsListRefresher(Consumer<List<SimulationOrderRequestDetailsDto>> simulationRequestsListConsumer) {
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
                    Type requestDetailsListType = new TypeToken<ArrayList<SimulationOrderRequestDetailsDto>>(){}.getType();
                    ArrayList<SimulationOrderRequestDetailsDto> simulationOrderRequestDetailsDtoList = GSON_INSTANCE.fromJson(responseBody, requestDetailsListType);
                    simulationRequestsListConsumer.accept(simulationOrderRequestDetailsDtoList);

                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request GET_SIMULATION_REQUESTS_LIST_ENDPOINT...:(");
                }
            }
        });

    }
}
