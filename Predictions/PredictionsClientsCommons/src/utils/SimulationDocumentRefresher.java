package utils;

import com.google.gson.reflect.TypeToken;
import dto.simulationInfo.SimulationDocumentInfoDto;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.TimerTask;
import java.util.function.Consumer;

import static utils.Constants.*;

public class SimulationDocumentRefresher extends TimerTask {
    private final Consumer<SimulationDocumentInfoDto> simulationDocumentInfoDto;
    private String simulationGuid;

    public SimulationDocumentRefresher(Consumer<SimulationDocumentInfoDto> simulationDocumentInfoDto, String simulationGuid) {
        this.simulationDocumentInfoDto = simulationDocumentInfoDto;
        this.simulationGuid = simulationGuid;
    }

    @Override
    public void run() {
        if(simulationGuid == null)return;
        HttpClientUtil.runAsync(GET_SIMULATION_DOCUMENT_INFO_ENDPOINT, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Type requestDetailsListType = new TypeToken<SimulationDocumentInfoDto>(){}.getType();
                    SimulationDocumentInfoDto SimulationDocumentInfoDtoList = GSON_INSTANCE.fromJson(responseBody, requestDetailsListType);
                    simulationDocumentInfoDto.accept(SimulationDocumentInfoDtoList);

                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request GET_SIMULATION_REQUESTS_LIST_ENDPOINT...:(");
                }
            }
        });

    }
}
