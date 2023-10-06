package utils;

import com.google.gson.reflect.TypeToken;
import dto.simulationInfo.SimulationDocumentInfoDto;
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

import static utils.Constants.*;

public class SimulationGuidListRefresher extends TimerTask {
    private final Consumer<List<String>> simulationGuidList;

    public SimulationGuidListRefresher(Consumer<List<String>> simulationGuidList) {
        this.simulationGuidList = simulationGuidList;
    }

    @Override
    public void run() {
        HttpClientUtil.runAsync(GET_SIMULATION_GUID_LIST_ENDPOINT, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Type simulationGuidListType = new TypeToken<ArrayList<String>>(){}.getType();
                    ArrayList<String> SimulationGuidListInfoDtoList = GSON_INSTANCE.fromJson(responseBody, simulationGuidListType);
                    simulationGuidList.accept(SimulationGuidListInfoDtoList);

                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request GET_SIMULATION_REQUESTS_LIST_ENDPOINT...:(");
                }
            }
        });

    }
}
